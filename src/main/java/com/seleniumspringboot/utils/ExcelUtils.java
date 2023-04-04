package com.seleniumspringboot.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@Component
public class ExcelUtils {

    private static final String filePath = "SampleExcel.xlsx";

    public static String readExcel(int rowNum, int colNum) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            String cellData = cell.getStringCellValue();
            workbook.close();
            file.close();
            return cellData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeExcel(int rowNum, int colNum, String data) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.createCell(colNum);
            cell.setCellValue(data);
            file.close();
            FileOutputStream out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
