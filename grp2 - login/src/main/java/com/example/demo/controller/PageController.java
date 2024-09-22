package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.meibo.Meibo;
import com.example.demo.meibo.MeiboForm;
import com.example.demo.meibo.MeiboService;

@Controller
public class PageController {

    @Autowired
    private MeiboService meiboService;

    @GetMapping("/main1")
    public String main1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
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

    @GetMapping("/update1")
    public String update1(@RequestParam(required = false) Long id, HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        Long userId = (Long) session.getAttribute("userId");

        // 検索画面からの遷移の場合
        if (id != null) {
            Meibo meibo = meiboService.findById(id);
            if (meibo == null) {
                return "error"; // 適切なエラーページに遷移
            }
            model.addAttribute("meibo", meibo);
        } 
        // ログイン画面からの遷移の場合
        else if (userId != null) {
            Meibo meibo = meiboService.findById(userId);
            if (meibo == null) {
                return "error"; // 適切なエラーページに遷移
            }
            model.addAttribute("meibo", meibo);
        } else {
            return "error"; // 適切なエラーページに遷移
        }

        model.addAttribute("userName", userName);
        return "update1"; // 更新画面に遷移
    }


    @PostMapping("/update1")
    public String update1Submit(HttpSession session, Model model, @ModelAttribute MeiboForm meiboForm) {
        String userName = (String) session.getAttribute("userName");

        Meibo meibo = new Meibo();
        meibo.setId(meiboForm.getId());
        meibo.setName(meiboForm.getName());
        meibo.setAge(meiboForm.getAge());
        meibo.setSdate(meiboForm.getSdate());
        meibo.setEdate(meiboForm.getEdate());
        meibo.setPassword(meiboForm.getPassword());

        model.addAttribute("userName", userName);
        model.addAttribute("meibo", meibo);

        return "update2"; // 確認画面へ遷移
    }

    @PostMapping("/updateConfirm")
    public String updateConfirm(@ModelAttribute Meibo meibo, HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");

        meiboService.saveMeibo(meibo);

        model.addAttribute("userName", userName);
        return "update3"; // 更新完了画面へ遷移
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

    @GetMapping("/searchPage")
    public String searchPage(HttpSession session, Model model,
                             @RequestParam(required = false) Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer age_start,
                             @RequestParam(required = false) Integer age_end,
                             @RequestParam(required = false) String sdate_start,
                             @RequestParam(required = false) String sdate_end,
                             @RequestParam(required = false) String edate_start,
                             @RequestParam(required = false) String edate_end) {

        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        List<Meibo> meiboList = meiboService.search(id, name, age_start, age_end, sdate_start, sdate_end, edate_start, edate_end);
        model.addAttribute("meiboList", meiboList);
        model.addAttribute("count", meiboList.size()); // 件数を追加

        return "search1"; // 検索結果を表示するテンプレートを返す
    }
}
