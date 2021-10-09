package com.wyq.spring.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @date 2021-10-09 14:25
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("c", 9);
        map.put("p", 2);
        map.put("b", 5);
        map.put("l", 1);
        map.put("f", 4);
        //value正序排列-Map.Entry.comparingByValue()
        //value倒序排列-Map.Entry.comparingByValue(Comparator.reverseOrder())
        LinkedHashMap<String, Integer> linkedHashMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (c1, c2) -> c1,
                        LinkedHashMap::new
                ));
        System.out.println(linkedHashMap);
    }

    @Test
    public void testA() {
        String s = "531212=24489404, 531213=24489405, 531214=24489406, 531215=24489407, 584897=24489403, " +
                "534209=24489439, 534210=24489440, 534211=24489441, 534212=24489442, 554139=24489429, " +
                "554140=24489430, 534175=24489418, 531216=24489408, 531217=24489409, 531218=24489410, " +
                "531219=24489411, 531220=24489412, 534292=24489436, 531221=24489413, 534293=24489437, " +
                "534184=24489427, 534185=24489428, 534176=24489419, 534177=24489420, 534178=24489421, " +
                "534179=24489422, 534180=24489423, 534181=24489424, 534182=24489425, 534183=24489426, " +
                "554104=24489414, 554105=24489415, 554106=24489416, 554107=24489417, 534203=24489431, " +
                "534204=24489432, 534205=24489433, 534269=24489435, 534206=24489434, 534207=24489438";

        List<Long> keys = new ArrayList<>();
        List<Long> values = new ArrayList<>();
        for (String s1 : s.split(",")) {
            String[] split = s1.split("=");
            keys.add(Long.valueOf(split[0].trim()));
            values.add(Long.valueOf(split[1].trim()));
        }
        Collections.sort(keys);

        System.out.println(keys.size());
        System.out.println(JSON.toJSONString(keys));
        System.out.println(JSON.toJSONString(values));
        System.out.println(values.size());
    }
}
