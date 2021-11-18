/*
 *
 *  * Copyright 2020 byai.com All right reserved. This software is the
 *  * confidential and proprietary information of byai.com ("Confidential
 *  * Information"). You shall not disclose such Confidential Information and shall
 *  * use it only in accordance with the terms of the license agreement you entered
 *  * into with byai.com.
 *
 */

package com.wyq.aliyun.util;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelWriteUtils {
    private final static int MAX_ROWS = 60000;

    public static File writeExcel(String excelPath, List<String> titleRow, List<List<String>> data) throws Exception {
        Workbook wb = null;
        File file = new File(excelPath);
        file.getParentFile().mkdirs();
        boolean endXls = excelPath.endsWith(".xls");
        boolean endXlsx = excelPath.endsWith(".xlsx");
        if (endXls) {
            wb = new HSSFWorkbook();
        } else if (endXlsx) {
            wb = new XSSFWorkbook();
        } else {
            throw new Exception("文件格式不正确，后缀只能为xls或xlsx");
        }
        Map<Integer, Sheet> sheetMap = new LinkedHashMap<>();
        Map<Integer, List<List<String>>> dataMap = new LinkedHashMap<>();
        int dataSize = data.size();
        int sheetNum = (dataSize + MAX_ROWS - 1) / MAX_ROWS;
        for (int i = 1; i < sheetNum + 1; i++) {
            int start = (i - 1) * MAX_ROWS;
            int end = i * MAX_ROWS > dataSize ? dataSize : i * MAX_ROWS;
            //创建sheet对象
            Sheet sheet = wb.createSheet("sheet" + i);
            sheetMap.put(i, sheet);
            dataMap.put(i, data.subList(start, end));
        }
        //创建工作文档对象
        if (!file.exists()) {
            //创建sheet对象
            OutputStream outputStream = new FileOutputStream(excelPath);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }
        CellStyle style = wb.createCellStyle(); // 样式对象
        // 设置单元格的背景颜色为淡蓝色
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);

        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(CellStyle.ALIGN_CENTER);// 水平
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行

        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 280);
        style.setFont(font);
        // 添加一个sheet
        sheetMap.forEach((index, sheet) -> {
            List<List<String>> subData = dataMap.get(index);
            writeSheet(titleRow, subData, endXls, endXlsx, sheet, style);
        });
        //创建文件流
        OutputStream stream = new FileOutputStream(excelPath);
        //写入数据
        wb.write(stream);
        //关闭文件流
        stream.close();

        return file;
    }

    private static void writeSheet(List<String> titleRow, List<List<String>> data, boolean endXls, boolean endXlsx, Sheet sheet, CellStyle style) {
        //添加表头
        Row row = sheet.createRow(0);    //创建第1行
        Cell cell;
        for (int i = 0; i < titleRow.size(); i++) {
            cell = row.createCell(i);
            style.setWrapText(true);
            //富文本编辑框在xls和xlsx文件下的引用类适配
            if (endXls) {
                cell.setCellValue(new HSSFRichTextString(titleRow.get(i)));
            } else if (endXlsx) {
                cell.setCellValue(new XSSFRichTextString(titleRow.get(i)));
            }
            cell.setCellStyle(style); // 样式，居中
            sheet.setColumnWidth(i, 20 * 256);
        }
        row.setHeight((short) 540);

        //循环写入行数据
        int size = data.size();
        for (int i = 0; i < size; i++) {
            row = sheet.createRow(i + 1);
            row.setHeight((short) 500);
            for (int j = 0; j < data.get(i).size(); ++j) {
                row.createCell(j).setCellValue(data.get(i).get(j));
            }
        }
    }


    /**
     * CSV文件生成方法
     *
     * @param head
     * @param dataList
     * @param outPutPathName
     * @return
     */
    public static File exportCSVFile(String outPutPathName, List<String> head, List<List<String>> dataList) {

        File csvFile = null;
        try {
            BufferedWriter csvWtriter = null;
            try {
                csvFile = new File(outPutPathName);
                File parent = csvFile.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                csvFile.createNewFile();

                csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
                // 写入文件头部
                if (!CollectionUtils.isEmpty(head)) {
                    writeRow(head, csvWtriter);
                }

                // 写入文件内容
                for (List<String> row : dataList) {
                    writeRow(row, csvWtriter);
                }
                csvWtriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                csvWtriter.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("导出CSV错误", ex.getCause());
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<String> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
}
