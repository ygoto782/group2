package com.example.demo.login;

import jakarta.servlet.http.HttpSession; // HTTPセッションを扱うためのクラスをインポート

import org.springframework.beans.factory.annotation.Autowired; // 自動的に依存性注入を行うためのアノテーションをインポート
import org.springframework.stereotype.Controller; // コントローラー層を示すアノテーションをインポート
import org.springframework.web.bind.annotation.GetMapping; // GETリクエストの処理を行うアノテーションをインポート
import org.springframework.web.bind.annotation.PostMapping; // POSTリクエストの処理を行うアノテーションをインポート
import org.springframework.web.bind.annotation.RequestParam; // リクエストパラメータを取得するためのアノテーションをインポート
import org.springframework.web.servlet.ModelAndView; // モデルとビューを同時に扱うためのクラスをインポート

import com.example.demo.meibo.Meibo; // Meiboエンティティをインポート
import com.example.demo.meibo.MeiboService; // MeiboServiceをインポート

// ログイン処理を担当するコントローラー
@Controller
public class LoginController {

    @Autowired // MeiboServiceのインスタンスを自動的に注入
    private MeiboService meiboService;

    // ログインページを表示するメソッド
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // loginテンプレートを返す
    }

    // ログイン処理を行うメソッド
    @PostMapping("/login")
    public ModelAndView login(@RequestParam Long id, @RequestParam String password, HttpSession session) {
        Meibo user = meiboService.findById(id); // IDを使ってユーザーを検索
        // ユーザーが存在し、パスワードが一致する場合
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("userId", user.getId()); // セッションにユーザーIDを保存
            session.setAttribute("userName", user.getName()); // セッションにユーザー名を保存
            return new ModelAndView("redirect:/main1"); // メインページへリダイレクト
        } else {
            // 無効なIDまたはパスワードの場合
            return new ModelAndView("login", "error", "無効なIDまたはパスワードです"); // エラーメッセージを表示
        }
    }
}
