package dev.anjalee.gym_management_system.reports;

import dev.anjalee.gym_management_system.models.Member;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class ExcelReportService {
    public String generateExpiringMembers(List<Member> members){
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Expiring Members");
        createheaderRow(sheet);
        createMemberRows(sheet, members);
        autoSizeColumns(sheet);
        String filePath=saveWorkbook(workbook);
        closeWorkbook(workbook);
        return filePath;
    }

    private void createheaderRow(Sheet sheet){
        Row headerRow=sheet.createRow(0);
        headerRow.createCell(0).setCellValue("member ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Phone Number");
        headerRow.createCell(4).setCellValue("Membership Start Date");
        headerRow.createCell(5).setCellValue("Membership End Date");
    }

    private void createMemberRows(Sheet sheet, List<Member> members){
        int rowNum=1;
        for(Member member:members){
            Row row=sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(member.getId());
            row.createCell(1).setCellValue(member.getName());
            row.createCell(2).setCellValue(member.getEmail());
            row.createCell(3).setCellValue(member.getPhoneNumber());
            row.createCell(4).setCellValue(member.getMembershipStartDate().toString());
            row.createCell(5).setCellValue(member.getMembershipEndDate().toString());
        }
    }

    private void autoSizeColumns(Sheet sheet){
        for(int columnIndex =0;columnIndex < 6;columnIndex++){
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private String saveWorkbook(Workbook workbook){
        try{
            File reportsDirectory=new File("C:/gym-reports");
            if(!reportsDirectory.exists()){
                reportsDirectory.mkdirs();
            }
            String timestamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filePath="C:/gym-reports/Expiring_Members_" + timestamp + ".xlsx";
            FileOutputStream fileOut=new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            return filePath;
        } catch (IOException exception){
            throw new RuntimeException("Faile to generate Excel report", exception);
        }
    }

    private void closeWorkbook(Workbook workbook){
        try{
            workbook.close();
        } catch (IOException exception){
            throw new RuntimeException("Failed to close the workbook", exception);
        }
    }
}
