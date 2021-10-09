package com.wyq.spring.test;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 罗新
 * @date 2021-09-23 14:31
 **/
public class FileTest {
    @Test
    public void testA() throws IOException {
        String filePath = "/Users/yongqiwang/Downloads/分析机器人模型测试批量导入模版2.xlsx";
        File file = new File(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        HashMap<String, Integer> map = new HashMap<>();
        XSSFSheet sheet = wb.getSheetAt(0);
        Set<String> result = new HashSet<>();
        for (Row row : sheet) {
            for (Cell cell : row) {
//                System.out.println("------------------");
//                System.out.println("第" + row.getRowNum() + "行，第" + cell.getColumnIndex() + "列：" + cell.getStringCellValue());
//                System.out.println("------------------");
                if (map.containsKey(cell.getStringCellValue())) {
                    map.put(cell.getStringCellValue(), map.get(cell.getStringCellValue()) + 1);
                } else {
                    map.put(cell.getStringCellValue(), 1);
                }
                result.add(cell.getStringCellValue());
            }
        }
        System.out.println("总共：" + sheet.getLastRowNum());
        System.out.println("map.size:" + map.size());

        LinkedHashMap<String, Integer> linkedHashMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(50)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (c1, c2) -> c1,
                        LinkedHashMap::new
                ));
        System.out.println(JSON.toJSONString(linkedHashMap, true));
    }

    @Test
    public void testB() {
        String fileName = 1234 + "_" + "123131231321" + "_" + DateUtils.formatDate(new Date(), "yyyyMMdd_HH_mm_ss");
        String fileDir = File.separator + "tmp" + File.separator + fileName;
        System.out.println(fileDir);
    }

    @Test
    public void testC() throws IOException {
        InputStream in = new ClassPathResource("case/teacher.json").getInputStream();
        String json = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
    }
}
