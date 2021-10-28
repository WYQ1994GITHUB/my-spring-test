package com.wyq.spring.test.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AdminApiConfigController {


    @PostMapping("/admin/api/post/test")
    public String migrate(@RequestBody Map<String, Integer> map) {
        return map.toString();
    }

    @GetMapping("/admin/api/get/test")
    public String queryHsfServiceList(@RequestParam("appName") String appName) {
        return appName;
    }
}
