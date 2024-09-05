package com.example.demo.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.meibo.Meibo;
import com.example.demo.meibo.MeiboForm;

@Controller
public class SignupController {

    @Autowired
    private MeiboService meiboService;

    @PostMapping("/signup2")
    public String confirmSignup(@ModelAttribute MeiboForm form, Model model) {
        // パスワード確認が一致しない場合はエラーメッセージを設定
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("errorMessage", "パスワードが一致しません。");
            return "signup1"; // 入力ページに戻る
        }

        // データを確認用のページに渡す
        model.addAttribute("name", form.getName());
        model.addAttribute("age", form.getAge());
        model.addAttribute("password", form.getPassword()); // パスワードは表示しない
        model.addAttribute("confirmPassword", form.getConfirmPassword()); // パスワード確認も表示しない

        // フォームのデータを保持して次のページに渡す
        model.addAttribute("form", form);

        return "signup2"; // 確認ページに遷移
    }

    @PostMapping("/signup3")
    public String registerUser(@ModelAttribute MeiboForm form, Model model) {
        // Meibo エンティティを作成して保存
        Meibo meibo = new Meibo();
        meibo.setName(form.getName());
        meibo.setAge(form.getAge());
        meibo.setPassword(form.getPassword());
        meiboService.saveMeibo(meibo);

        // 完了ページにメッセージを渡す
        model.addAttribute("message", "社員情報が登録されました。");

        return "signup3"; // 完了ページへの遷移
    }
}
