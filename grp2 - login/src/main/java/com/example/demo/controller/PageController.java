package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/main1")
    public String main1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "main1";
    }

    @GetMapping("/search1")
    public String search1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "search1";
    }

    @GetMapping("/signup1")
    public String signup1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "signup1";
    }

    @GetMapping("/signup2")
    public String signup2(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "signup2";
    }

    @GetMapping("/signup3")
    public String signup3(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "signup3";
    }

    @GetMapping("/update1")
    public String update1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "update1";
    }

    @GetMapping("/update2")
    public String update2(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "update2";
    }

    @GetMapping("/update3")
    public String update3(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "update3";
    }

    @GetMapping("/delete1")
    public String delete1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "delete1";
    }

    @GetMapping("/delete2")
    public String delete2(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "delete2";
    }
}
