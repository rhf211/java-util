package com.example.demo.controller;

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
    public String testLook() {
       return lookService.testLook();
    }
}
