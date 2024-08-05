package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

	@GetMapping("search1")
	public String getSearch(MeiboForm form, Model model) {
		model.addAttribute("meiboForm", form);
		return "search1";
	}
}
