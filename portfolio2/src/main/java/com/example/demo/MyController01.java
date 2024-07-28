package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController01 {

    @GetMapping("/first")
    public String first() {
        return "こんにちは！";
    }
}
