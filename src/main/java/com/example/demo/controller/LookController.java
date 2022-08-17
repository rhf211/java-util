package com.example.demo.controller;

import com.example.demo.aop.BusinessLog;
import com.example.demo.service.LookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/look")
public class LookController {
    @Autowired
    private LookService lookService;

    @GetMapping("/test")
    @BusinessLog
    public String testLook() {
       return lookService.testLook();
    }

    @GetMapping("/test1")
    public String testLook1() {
        return lookService.testLook1();
    }
}
