package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.meibo.MeiboService;

@Controller
public class YourController {

    private final MeiboService meiboService;

    YourController(MeiboService meiboService) {
        this.meiboService = meiboService;
    }

    @GetMapping("/somePage")
    public ModelAndView somePage(HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        ModelAndView modelAndView = new ModelAndView("somePage"); // somePageはThymeleafのテンプレート名
        modelAndView.addObject("userName", userName);
        return modelAndView;
    }

	public MeiboService getMeiboService() {
		return meiboService;
	}
}
