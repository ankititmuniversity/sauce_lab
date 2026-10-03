package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelOperation {

    public static Workbook workbook;
    public static Sheet sheet;
    public static Row row;
    public static Cell cell;
    public static FileInputStream fis;
    public static FileOutputStream fout;
    public static int getRowCount(String path,String sheetName) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return lastRowNum;
    }
     public static int getCellCount(String path,String sheetName, int rowNum) throws IOException {
         fis = new FileInputStream(path);
         workbook = new XSSFWorkbook(fis);
         sheet = workbook.getSheet(sheetName);
         row = sheet.getRow(rowNum);
         int lastCellNum = row.getLastCellNum();
         workbook.close();
         fis.close();
         return lastCellNum;
     }
     public static String getCellValue(String path, String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        String data;
        DataFormatter formatter = new DataFormatter();
        try{
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
           data = "";
        }
        workbook.close();
        fis.close();
        return data;
     }
     public static void setCellValue(String path,String sheetName, int rowNum, int colNum, String data) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        if(workbook.getSheet(sheetName) != null){
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            cell.setCellValue(data);
        }else {
            workbook.createSheet(sheetName);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            cell.setCellValue(data);
            fout = new FileOutputStream(path);
            workbook.write(fout);
            workbook.close();
            fis.close();
            fout.close();
        }
     }
}

