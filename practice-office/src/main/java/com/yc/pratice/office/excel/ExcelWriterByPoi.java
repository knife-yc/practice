package com.yc.pratice.office.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 过滤器专属导出类
 */
public class ExcelWriterByPoi {
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";
    private static final Logger logger = LoggerFactory.getLogger(ExcelWriterByPoi.class);
    public static final String fileRelativeDir = System.getProperty("static.file.relative.path", ".");
    public static final int DEFAULT_SHEET_ROW_SIZE = Integer.valueOf(System.getProperty("default.sheet.rowCount", "50000"));
    public static final String EXCEL_2003 = "xls";
    public static final String EXCEL_2007 = "xlsx";
    public static final String FILE_TYPE_SPLIT = ".";

    public static File write(String fileName, String sheetName, Class exportModel, List rows) throws Exception {
        File dir = new File(ExcelWriterByPoi.fileRelativeDir);
        File file = new File(dir.getAbsolutePath(), fileName + FILE_TYPE_SPLIT + EXCEL_2007);
        try (OutputStream os = new FileOutputStream(file);
             XSSFWorkbook workBook = new XSSFWorkbook()) {
            ExportExcelHelper.makeDir(dir);
            XSSFSheet sheet = null;
            if (!file.exists()) {
                boolean result = file.createNewFile();
                if (!result) {
                    logger.error("file create failed.fileName:" + fileName);
                }
            }
            sheet = workBook.createSheet(sheetName + "-1");
            sheet.setRowSumsBelow(false);
            Integer sheetNum = 1;
            Field[] headers = ExportExcelHelper.getHeaders(exportModel);
            Field subTable = getSubTable(exportModel);

            ExportExcelHelper.addTitle(headers, sheet, workBook);
            int rowIndex = 0;
            for (int index = 0; index < rows.size(); index++) {
                if (index == sheetNum * DEFAULT_SHEET_ROW_SIZE) {
                    sheet = workBook.createSheet(sheetName + (++sheetNum));
                    sheet.setRowSumsBelow(false);
                    ExportExcelHelper.addTitle(headers, sheet, workBook);
                }
                Object row = rows.get(index);
                rowIndex = ExportExcelHelper.fillContent(
                        rowIndex >= ((sheetNum - 1) * DEFAULT_SHEET_ROW_SIZE) ? rowIndex - ((sheetNum - 1) * DEFAULT_SHEET_ROW_SIZE) : index,
                        sheet,
                        headers,
                        subTable,
                        0,
                        row,
                        workBook);
            }
            ExportExcelHelper.writeAndClose(os, workBook);
            return file;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 获取子表格的配置
     *
     * @param clazz
     * @return
     */
    private static Field getSubTable(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int index = 0; index < fields.length; index++) {
            Field field = fields[index];
            SubTableAnnotation subTableAnnotation = field.getAnnotation(SubTableAnnotation.class);
            if (EmptyUtil.isEmpty(subTableAnnotation)) {
                continue;
            }
            return field;
        }
        return null;
    }

    public static List<Object> readExcel(Class obj, String filePath) {
        List<Object> list = new ArrayList<>();
        Field[] headers = ExportExcelHelper.getHeaders(obj);
        //根据路径生成 FileInputStream字节流
        try (FileInputStream inputStream = new FileInputStream(new File(ExcelWriterByPoi.fileRelativeDir + File.separator + filePath));) {
            // 获取文件输入流
            // 定义一个org.apache.poi.ss.usermodel.Workbook的变量
            Workbook workbook = null;
            // 截取路径名 . 后面的后缀名，判断是xls还是xlsx
            // 如果这个判断不对，就把equals换成 equalsIgnoreCase()
            if (filePath.substring(filePath.lastIndexOf(FILE_TYPE_SPLIT) + 1).equals(EXCEL_2003)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (filePath.substring(filePath.lastIndexOf(FILE_TYPE_SPLIT) + 1).equals(EXCEL_2007)) {
                workbook = new XSSFWorkbook(inputStream);
            }

            // 获取第一张表
            Sheet sheet = workbook.getSheetAt(0);
            // 循环读取每一行
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 循环读取每一个格
                Row row = sheet.getRow(i);
                Object rowObj = obj.newInstance();
                for (int j = 0; j < headers.length; j++) {
                    Field header = headers[j];
                    ExportHeaderAnnotation exportHeaderAnnotation = header.getAnnotation(ExportHeaderAnnotation.class);
                    Cell cell = row.getCell(exportHeaderAnnotation.index());
                    Class type = header.getType();
                    String cellValue = cell.getStringCellValue();
                    header.setAccessible(true);
                    if (type.equals(Date.class)) {
                        header.set(rowObj, DateUtil.stringToDate(cellValue));
                    } else if (type.equals(int.class) || type.equals(Integer.class)) {
                        header.set(rowObj, Integer.valueOf(cellValue));
                    } else if (type.equals(short.class) || type.equals(Short.class)) {
                        header.set(rowObj, Short.valueOf(cellValue));
                    } else if (type.equals(byte.class) || type.equals(Byte.class)) {
                        header.set(rowObj, Byte.valueOf(cellValue));
                    } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
                        header.set(rowObj, Boolean.valueOf(cellValue));
                    } else if (type.equals(float.class) || type.equals(Float.class)) {
                        header.set(rowObj, Float.valueOf(cellValue));
                    } else if (type.equals(double.class) || type.equals(Double.class)) {
                        header.set(rowObj, Double.valueOf(cellValue));
                    } else if (type.equals(long.class) || type.equals(Long.class)) {
                        header.set(rowObj, Long.valueOf(cellValue));
                    } else {
                        header.set(rowObj, cellValue);
                    }
                }
                list.add(rowObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
