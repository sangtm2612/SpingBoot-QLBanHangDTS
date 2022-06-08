package com.example.sangtmph17730_asm.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.example.sangtmph17730_asm.entities.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportUtil {
	public static final int COLUMN_INDEX_CATE_ID        = 0;

    public static final int COLUMN_INDEX_CATE_NAME       = 1;

	public static final int COLUMN_INDEX_PRODUCT_ID         = 2;

    public static final int COLUMN_INDEX_PRODUCT_NAME      = 3;

    public static final int COLUMN_INDEX_PRODUCT_IMAGE      = 4;

    public static final int COLUMN_INDEX_PRODUCT_PRICE    = 5;

    public static final int COLUMN_INDEX_PRODUCT_CREATEDATE      = 6;

    public static final int COLUMN_INDEX_PRODUCT_AVAILABLE      = 7;
    private static CellStyle cellStyleFormatNumber = null;
     
 
    public static void writeExcel(List<Product> products, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
 
        // Create sheet
        Sheet sheet = workbook.createSheet("ProductManager"); // Create sheet with sheet name
 
        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (Product p : products) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            write(p, row);
            rowIndex++;
        }
         
        // Write footer
//        writeFooter(sheet, rowIndex);
 
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
 
 
    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_CATE_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ID_CATEGORY");
        
        cell = row.createCell(COLUMN_INDEX_CATE_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CATEGORY");

        cell = row.createCell(COLUMN_INDEX_PRODUCT_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ID_PRODUCT");
 
        cell = row.createCell(COLUMN_INDEX_PRODUCT_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("PRODUCT");
 
        cell = row.createCell(COLUMN_INDEX_PRODUCT_CREATEDATE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CREATE_DATE");
 
        cell = row.createCell(COLUMN_INDEX_PRODUCT_IMAGE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("IMAGE");

        cell = row.createCell(COLUMN_INDEX_PRODUCT_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("PRICE");

        cell = row.createCell(COLUMN_INDEX_PRODUCT_AVAILABLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("AVAILABLE");
    }
 
    // Write data
    private static void write(Product p, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        
        Cell cell = row.createCell(COLUMN_INDEX_CATE_ID);
        cell.setCellValue(p.getCategory().getId());
         
        cell = row.createCell(COLUMN_INDEX_CATE_NAME);
        cell.setCellValue(p.getCategory().getName());
 
        cell = row.createCell(COLUMN_INDEX_PRODUCT_ID);
        cell.setCellValue(p.getId());
 
        cell = row.createCell(COLUMN_INDEX_PRODUCT_CREATEDATE);
        cell.setCellValue(p.getCreatedDate() + "");

        cell = row.createCell(COLUMN_INDEX_PRODUCT_IMAGE);
        cell.setCellValue(p.getImage());

        cell = row.createCell(COLUMN_INDEX_PRODUCT_PRICE);
        cell.setCellValue(p.getPrice());

        cell = row.createCell(COLUMN_INDEX_PRODUCT_AVAILABLE);
        cell.setCellValue(p.getAvailable());
        cell.setCellStyle(cellStyleFormatNumber);

    }
 
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
     
    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_PRODUCT_PRICE, CellType.FORMULA);
        cell.setCellFormula("SUM(E2:E6)");
    }
     
    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
