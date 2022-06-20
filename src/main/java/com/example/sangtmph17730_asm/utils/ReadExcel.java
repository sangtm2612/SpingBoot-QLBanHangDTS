package com.example.sangtmph17730_asm.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.entities.Product;
import com.example.sangtmph17730_asm.repository.CategoryRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;


public class ReadExcel {
    public static final int COLUMN_INDEX_CATEGORY_NAME = 1;
    public static final int COLUMN_INDEX_PRODUCT_NAME = 3;
    public static final int COLUMN_INDEX_PRODUCT_IMG = 4;
    public static final int COLUMN_INDEX_PRODUCT_PRICE = 5;


    @Autowired
    static HttpSession session;

    public static List<Product> readExcel(CategoryRepository categoryRepository ,String excelFilePath) throws IOException {
        List<Product> products = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Product product = new Product();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_CATEGORY_NAME:
                    String nameCate = getCellValue(cell) + "";
                    System.out.println(nameCate);
                	Category c = categoryRepository.findByName(nameCate);
                	product.setCategory(c);
                    break;
                case COLUMN_INDEX_PRODUCT_NAME:
                	product.setName((String) getCellValue(cell));
                    break;
                    case COLUMN_INDEX_PRODUCT_IMG:
                        product.setImage((String) getCellValue(cell));
                        break;
                case COLUMN_INDEX_PRODUCT_PRICE:
                	product.setPrice(Integer.parseInt(getCellValue(cell) + ""));
                    break;
                default:
                    break;
                }
            }
            product.setCreatedDate(new Date());
            product.setAvailable(1);
            products.add(product);
        }

        workbook.close();
        inputStream.close();

        return products;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue() + "";
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }

        return cellValue;
    }
}