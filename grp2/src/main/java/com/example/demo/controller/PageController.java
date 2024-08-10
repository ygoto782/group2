package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/main1")
    public String main1() {
        return "main1";
    }

    @GetMapping("/search1")
    public String search1() {
        return "search1";
    }

    @GetMapping("/signup1")
    public String signup1() {
        return "signup1";
    }

    @GetMapping("/signup2")
    public String signup2() {
        return "signup2";
    }

    @GetMapping("/signup3")
    public String signup3() {
        return "signup3";
    }

    @GetMapping("/update1")
    public String update1() {
        return "update1";
    }

    @GetMapping("/update2")
    public String update2() {
        return "update2";
    }

    @GetMapping("/update3")
    public String update3() {
        return "update3";
    }

    @GetMapping("/delete1")
    public String delete1() {
        return "delete1";
    }

    @GetMapping("/delete2")
    public String delete2() {
        return "delete2";
    }
}
