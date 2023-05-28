package com.yc.pratice.office.excel;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class ExportExcelHelper {
    private static Logger logger = LoggerFactory.getLogger(ExcelWriterByPoi.class);

    public static void addTitle(Field[] headers, XSSFSheet sheet, XSSFWorkbook workbook) {
        // 因为文件列头,不同的列呈现的颜色可能是不一致的，因此每一个列都创建一个自己的列头
        XSSFRow row0 = sheet.createRow(0);
        for (int index = 0; index < headers.length; index++) {
            Field headerField = headers[index];
            ExportHeaderAnnotation exportHeaderAnnotation = headerField.getAnnotation(ExportHeaderAnnotation.class);
            String headerName = exportHeaderAnnotation.headerName();
            sheet.setColumnWidth(index, headerName.getBytes().length * 256);
            XSSFCell cell = row0.createCell(exportHeaderAnnotation.index());
            XSSFCellStyle titleStyle = createXSSFCellStyle(workbook);
            cell.setCellValue(headerName);
            cell.setCellStyle(titleStyle);
        }
    }

    public static XSSFCellStyle createXSSFCellStyle(XSSFWorkbook workBook) {
        XSSFCellStyle titleStyle = workBook.createCellStyle();
        titleStyle.setFont(createFontStyle(workBook));// title行格式
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        return titleStyle;
    }

    public static XSSFFont createFontStyle(XSSFWorkbook workBook) {
        XSSFFont font = workBook.createFont();
        font.setFontName("宋体");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        return font;
    }

    public static int fillContent(int rowIndex, XSSFSheet sheet, Field[] headers, Field subTable, Integer startColumnIndex, Object dataRow, XSSFWorkbook workBook) throws Exception {
        XSSFRow row = sheet.createRow(rowIndex + 1);
        for (Field header : headers) {
            ExportHeaderAnnotation exportHeaderAnnotation = header.getAnnotation(ExportHeaderAnnotation.class);
            if (EmptyUtil.isEmpty(exportHeaderAnnotation)) {
                continue;
            }

            Integer cellIndex = exportHeaderAnnotation.index();
            XSSFCell cell = row.createCell(cellIndex + startColumnIndex);
            try {
                header.setAccessible(true);
                Object column = header.get(dataRow);

                if (EmptyUtil.isEmpty(column)) {
                    cell.setCellValue("");
                } else {
                    if (column instanceof Date) {
                        cell.setCellValue(new HSSFRichTextString(DateUtil.dateToString((Date) column)));
                    } else {
                        Class dataType = exportHeaderAnnotation.dataType();
                        if (dataType.equals(Date.class)) {
                            cell.setCellValue(new HSSFRichTextString(DateUtil.dateToString(new Date((Long) column))));

                        } else {
                            cell.setCellValue(new HSSFRichTextString(column.toString()));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (EmptyUtil.isEmpty(subTable)) {
            return rowIndex + 1;
        } else {
            int subTableStartRowIndex = rowIndex + 1;
            SubTableAnnotation subTableAnnotation = subTable.getAnnotation(SubTableAnnotation.class);
            Class clazz = subTableAnnotation.subModule();
            Field[] subTableHeaders = getHeaders(clazz);
            Integer subTableStartColumn = subTableAnnotation.startColumn();
            addTitle(subTableHeaders, subTableStartRowIndex, subTableStartColumn, sheet, workBook);
            subTableStartRowIndex++;//添加了一行头，行数需要加一
            subTable.setAccessible(true);
            List subTableRows = (List) subTable.get(dataRow);
            for (Object subRow : subTableRows) {
                subTableStartRowIndex = fillContent(subTableStartRowIndex, sheet, subTableHeaders, null, subTableStartColumn, subRow, workBook);
            }
            return subTableStartRowIndex;
        }
    }

    public static void addTitle(Field[] headers, Integer rowIndex, Integer startColumnIndex, XSSFSheet sheet, XSSFWorkbook workbook) {
        // 因为文件列头,不同的列呈现的颜色可能是不一致的，因此每一个列都创建一个自己的列头
        XSSFRow row0 = sheet.createRow(rowIndex + 1);
        for (int index = 0; index < headers.length; index++) {
            Field headerField = headers[index];
            ExportHeaderAnnotation exportHeaderAnnotation = headerField.getAnnotation(ExportHeaderAnnotation.class);
            String headerName = exportHeaderAnnotation.headerName();
            sheet.setColumnWidth(index, headerName.getBytes().length * 512);
            XSSFCell cell = row0.createCell(exportHeaderAnnotation.index() + startColumnIndex);
            XSSFCellStyle titleStyle = createXSSFCellStyle(workbook);
            cell.setCellValue(headerName);
            cell.setCellStyle(titleStyle);
        }
    }

    public static Field[] getHeaders(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        short[] elementIndex = new short[fields.length];
        int headerNum = 0;
        for (int index = 0; index < fields.length; index++) {
            Field field = fields[index];
            ExportHeaderAnnotation exportHeaderAnnotation = field.getAnnotation(ExportHeaderAnnotation.class);
            if (EmptyUtil.isEmpty(exportHeaderAnnotation)) {
                continue;
            }
            elementIndex[index] = 1;
            headerNum++;
        }
        Field[] headers = new Field[headerNum];
        int headerIndex = 0;
        for (int index = 0; index < elementIndex.length; index++) {
            if (elementIndex[index] == 1) {
                headers[headerIndex] = fields[index];
                headerIndex++;
            }
        }
        return headers;
    }

    public static String getRelativePath(String dateStr) {
        return ExcelWriterByPoi.fileRelativeDir + File.separator + dateStr;
    }

    public static void makeDir(File dir) {
        if (!dir.exists()) {
            File parent = dir.getParentFile();
            if (!parent.exists()) {
                makeDir(parent);
            }
        }
        dir.mkdir();
    }

    public static void writeAndClose(OutputStream os, XSSFWorkbook workBook) {
        if (workBook != null && os != null) {
            try {
                workBook.write(os);
            } catch (Exception e) {
                logger.error("workBook write Exception.", e);
            }
        }
    }
}
