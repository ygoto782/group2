package com.example.demo.update;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.meibo.MeiboForm;
import com.example.demo.search.SearchService;

@Controller
@SessionAttributes("meibo")
public class UpdateController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        MeiboForm meibo = searchService.findById(id); // Meibo 型に変更
        if (meibo != null) {
            model.addAttribute("meibo", meibo);
            return "update1"; // update1.html に遷移
        } else {
            model.addAttribute("errorMessage", "指定された社員情報は存在しません。");
            return "error"; // エラーページに遷移
        }
    }

    @PostMapping("/updateEmployee")
    public String confirmUpdate(@RequestParam Map<String, String> params, Model model) {
        MeiboForm meibo = new MeiboForm();
        meibo.setId(Integer.parseInt(params.get("id")));
        meibo.setName(params.get("name"));
        meibo.setAge(Integer.parseInt(params.get("age")));
        meibo.setPassword(params.get("password"));
        meibo.setConfirmPassword(params.get("confirmPassword"));
        meibo.setSdate(LocalDate.parse(params.get("sdate")));
        meibo.setEdate(LocalDate.parse(params.get("edate")));

        model.addAttribute("meibo", meibo);

        return "update2"; // 確認画面へ遷移
    }

    @PostMapping("/updateConfirm")
    public String updateConfirm(@ModelAttribute("meibo") MeiboForm meibo, Model model, SessionStatus status) {
        if (meibo.isValid()) {
            // DB更新処理
            searchService.updateEmployee(meibo);
            status.setComplete(); // セッションから削除
            return "update3"; // 更新完了画面へ遷移
        } else {
            model.addAttribute("errorMessage", "入力内容に誤りがあります。");
            return "update2"; // エラーがあれば確認画面へ戻る
        }
    }
}
