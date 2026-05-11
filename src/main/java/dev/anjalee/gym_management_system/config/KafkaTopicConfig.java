package dev.anjalee.gym_management_system.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    public static final String MEMBERSHIP_EXPIRY_EMAIL_TOPIC = "membership-expiry-email-topic";

    @Bean
    public NewTopic membershipExpiryEmailTopic() {
        return new NewTopic(MEMBERSHIP_EXPIRY_EMAIL_TOPIC, 1, (short) 1);
    }
}