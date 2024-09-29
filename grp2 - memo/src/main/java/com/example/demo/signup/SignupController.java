package com.example.demo.signup;

import org.springframework.beans.factory.annotation.Autowired; // 自動的に依存性注入を行うためのアノテーションをインポート
import org.springframework.stereotype.Controller; // コントローラーを示すアノテーションをインポート
import org.springframework.ui.Model; // モデルを操作するためのクラスをインポート
import org.springframework.web.bind.annotation.ModelAttribute; // モデル属性をバインディングするためのアノテーションをインポート
import org.springframework.web.bind.annotation.PostMapping; // POSTリクエストを処理するためのアノテーションをインポート

import com.example.demo.meibo.Meibo; // Meiboエンティティをインポート
import com.example.demo.meibo.MeiboForm; // MeiboFormクラスをインポート
import com.example.demo.meibo.MeiboService; // MeiboServiceをインポート

@Controller
public class SignupController {

    @Autowired // MeiboServiceのインスタンスを自動的に注入
    private MeiboService meiboService;

    /**
     * ユーザー登録の確認を行うメソッド
     * 
     * @param form 入力されたユーザー情報を含むフォーム
     * @param model モデルオブジェクト
     * @return 確認ページへの遷移
     */
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
        // パスワードは表示しない
        model.addAttribute("password", form.getPassword());
        // パスワード確認も表示しない
        model.addAttribute("confirmPassword", form.getConfirmPassword());

        // フォームのデータを保持して次のページに渡す
        model.addAttribute("form", form);

        return "signup2"; // 確認ページに遷移
    }

    /**
     * ユーザー登録を行うメソッド
     * 
     * @param form 入力されたユーザー情報を含むフォーム
     * @param model モデルオブジェクト
     * @return 完了ページへの遷移
     */
    @PostMapping("/signup3")
    public String registerUser(@ModelAttribute MeiboForm form, Model model) {
        // Meibo エンティティを作成して保存
        Meibo meibo = new Meibo();
        meibo.setName(form.getName()); // 名前を設定
        meibo.setAge(form.getAge()); // 年齢を設定
        meibo.setPassword(form.getPassword()); // パスワードを設定
        meiboService.saveMeibo(meibo); // データベースに保存

        // 完了ページにメッセージを渡す
        model.addAttribute("message", "社員情報が登録されました。");

        return "signup3"; // 完了ページへの遷移
    }
}
