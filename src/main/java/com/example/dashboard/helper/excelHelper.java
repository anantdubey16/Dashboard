package com.example.dashboard.helper;
import com.example.dashboard.entity.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class excelHelper {

    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    public static List<Product> convertExcelToListOfProduct(InputStream is) {
        List<Product> list = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                Product p = new Product();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                p.setProductId((int) cell.getNumericCellValue());
                            }
                            break;
                        case 1:
                            if (cell.getCellType() == CellType.STRING) {
                                p.setProductName(cell.getStringCellValue());
                            }
                            break;
                        case 2:
                            if (cell.getCellType() == CellType.STRING) {
                                p.setProductDescription(cell.getStringCellValue());
                            }
                            break;
                        case 3:
                            if (cell.getCellType() == CellType.STRING) {
                                p.setProductPrice(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                p.setProductPrice(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
