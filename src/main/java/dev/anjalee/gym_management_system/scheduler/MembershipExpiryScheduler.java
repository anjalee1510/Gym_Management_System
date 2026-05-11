package dev.anjalee.gym_management_system.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anjalee.gym_management_system.clients.KafkaProducerClient;
import dev.anjalee.gym_management_system.events.EmailEventDTO;
import dev.anjalee.gym_management_system.models.Member;
import dev.anjalee.gym_management_system.reports.ExcelReportService;
import dev.anjalee.gym_management_system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static dev.anjalee.gym_management_system.config.KafkaTopicConfig.MEMBERSHIP_EXPIRY_EMAIL_TOPIC;

@Component
public class MembershipExpiryScheduler {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ExcelReportService excelReportService;

    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Autowired
    private ObjectMapper objectMapper;

    //@Scheduled(cron = "0 30 9 * * SUN", zone = "Asia/Kolkata") // Runs every Sunday at 9:00 AM
    @Scheduled(fixedRate = 10000)   // For testing purposes, runs every 10 seconds
    public void checkExpiringMemberships() {

        System.out.println("Scheduler started: Checking members expiring within 7 days");

        List<Member> expiringMembers = memberService.getMembersExpiringWithinAWeek();

        if (expiringMembers.isEmpty()) {
            System.out.println("No memberships are expiring within the next 7 days.");
            return;
        }

//        System.out.println("Members expiring within the next 7 days:");
//        for (Member member : expiringMembers) {
//            System.out.println("Member ID: " + member.getId() + ", Name: " + member.getName() +
//                    ", Expiry Date: " + member.getMembershipEndDate());
//        }

        System.out.println("Generating Excel report for expiring memberships...");
        String filePath=excelReportService.generateExpiringMembers(expiringMembers);
        System.out.println("Excel report generated successfully: " + filePath);
        EmailEventDTO emailEventDTO = new EmailEventDTO("",
                "Weekly Expiring Membership Report",
                "Please find attached the list of members whose memberships are expiring soon.",
                filePath
        );
        try {
            String message = objectMapper.writeValueAsString(emailEventDTO);

            kafkaProducerClient.sendMessage(
                    MEMBERSHIP_EXPIRY_EMAIL_TOPIC,
                    message
            );

        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Failed to convert EmailEventDTO to JSON", exception);
        }
    }
}