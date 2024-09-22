package com.example.demo.login;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.meibo.Meibo;
import com.example.demo.meibo.MeiboService;

@Controller
public class LoginController {

    @Autowired
    private MeiboService meiboService;

    // ログインページを表示
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ログイン処理
    @PostMapping("/login")
    public ModelAndView login(@RequestParam Long id, @RequestParam String password, HttpSession session) {
        Meibo user = meiboService.findById(id); // IDで検索
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("userId", user.getId()); // セッションにユーザーIDを保存
            session.setAttribute("userName", user.getName()); // セッションにユーザー名を保存
            return new ModelAndView("redirect:/main1");
        } else {
            return new ModelAndView("login", "error", "無効なIDまたはパスワードです");
        }
    }
}
