package com.example.demo.controller;

import java.util.List; // Listクラスをインポート

import jakarta.servlet.http.HttpSession; // HTTPセッションを扱うためのクラスをインポート

import org.springframework.beans.factory.annotation.Autowired; // Springの依存性注入をインポート
import org.springframework.stereotype.Controller; // コントローラー層を示すアノテーションをインポート
import org.springframework.ui.Model; // モデルオブジェクトを扱うためのクラスをインポート
import org.springframework.web.bind.annotation.GetMapping; // GETリクエストの処理を行うアノテーションをインポート
import org.springframework.web.bind.annotation.ModelAttribute; // モデル属性をバインドするためのアノテーションをインポート
import org.springframework.web.bind.annotation.PostMapping; // POSTリクエストの処理を行うアノテーションをインポート
import org.springframework.web.bind.annotation.RequestParam; // リクエストパラメータを扱うためのアノテーションをインポート

import com.example.demo.meibo.Meibo; // Meiboエンティティをインポート
import com.example.demo.meibo.MeiboForm; // MeiboFormフォームオブジェクトをインポート
import com.example.demo.meibo.MeiboService; // MeiboServiceをインポート

// コントローラークラス
@Controller
public class PageController {

    @Autowired // MeiboServiceのインスタンスを自動的に注入
    private MeiboService meiboService;

    // メイン画面に遷移するためのメソッド
    @GetMapping("/main1")
    public String main1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "main1"; // メイン画面を返す
    }

    // 検索画面に遷移するためのメソッド
    @GetMapping("/search1")
    public String search1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "search1"; // 検索画面を返す
    }

    // サインアップ画面に遷移するためのメソッド
    @GetMapping("/signup1")
    public String signup1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "signup1"; // サインアップ画面を返す
    }

    // 更新画面に遷移するためのメソッド
    @GetMapping("/update1")
    public String update1(@RequestParam(required = false) Long id, HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        Long userId = (Long) session.getAttribute("userId"); // セッションからユーザーIDを取得

        // 検索画面からの遷移の場合
        if (id != null) {
            Meibo meibo = meiboService.findById(id); // IDに基づいてMeiboを取得
            if (meibo == null) {
                return "error"; // 適切なエラーページに遷移
            }
            model.addAttribute("meibo", meibo); // モデルにMeiboオブジェクトを追加
        } 
        // ログイン画面からの遷移の場合
        else if (userId != null) {
            Meibo meibo = meiboService.findById(userId); // ユーザーIDに基づいてMeiboを取得
            if (meibo == null) {
                return "error"; // 適切なエラーページに遷移
            }
            model.addAttribute("meibo", meibo); // モデルにMeiboオブジェクトを追加
        } else {
            return "error"; // 適切なエラーページに遷移
        }

        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "update1"; // 更新画面に遷移
    }

    // 更新内容を確認するためのメソッド
    @PostMapping("/update1")
    public String update1Submit(HttpSession session, Model model, @ModelAttribute MeiboForm meiboForm) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得

        Meibo meibo = new Meibo(); // 新しいMeiboオブジェクトを作成
        meibo.setId(meiboForm.getId()); // フォームからIDを設定
        meibo.setName(meiboForm.getName()); // フォームから名前を設定
        meibo.setAge(meiboForm.getAge()); // フォームから年齢を設定
        meibo.setSdate(meiboForm.getSdate()); // フォームから開始日を設定
        meibo.setEdate(meiboForm.getEdate()); // フォームから終了日を設定
        meibo.setPassword(meiboForm.getPassword()); // フォームからパスワードを設定

        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        model.addAttribute("meibo", meibo); // モデルにMeiboオブジェクトを追加

        return "update2"; // 確認画面へ遷移
    }

    // 更新を確定するためのメソッド
    @PostMapping("/updateConfirm")
    public String updateConfirm(@ModelAttribute Meibo meibo, HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得

        meiboService.saveMeibo(meibo); // Meiboオブジェクトを保存

        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "update3"; // 更新完了画面へ遷移
    }

    // 更新完了画面に遷移するためのメソッド
    @GetMapping("/update3")
    public String update3(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "update3"; // 更新完了画面を返す
    }

    // 削除画面に遷移するためのメソッド
    @GetMapping("/delete1")
    public String delete1(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        model.addAttribute("userName", userName); // モデルにユーザー名を追加
        return "delete1"; // 削除画面を返す
    }

    // 検索結果を表示するためのメソッド
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

        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        model.addAttribute("userName", userName); // モデルにユーザー名を追加

        // 検索条件に基づいてMeiboのリストを取得
        List<Meibo> meiboList = meiboService.search(id, name, age_start, age_end, sdate_start, sdate_end, edate_start, edate_end);
        model.addAttribute("meiboList", meiboList); // モデルに検索結果を追加
        model.addAttribute("count", meiboList.size()); // 件数を追加

        return "search1"; // 検索結果を表示するテンプレートを返す
    }
}
