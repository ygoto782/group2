package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.search.SearchService;

@Controller
public class UpdateController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        MeiboForm meibo = searchService.findById(id);
        if (meibo != null) {
            model.addAttribute("meibo", meibo);
            return "update"; // update.html に遷移
        } else {
            model.addAttribute("errorMessage", "指定された社員情報は存在しません。");
            return "error"; // エラーページに遷移
        }
    }
}
