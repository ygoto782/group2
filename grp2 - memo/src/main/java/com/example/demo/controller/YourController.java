package com.example.demo.controller;

import jakarta.servlet.http.HttpSession; // HTTPセッションを扱うためのクラスをインポート

import org.springframework.stereotype.Controller; // コントローラー層を示すアノテーションをインポート
import org.springframework.web.bind.annotation.GetMapping; // GETリクエストの処理を行うアノテーションをインポート
import org.springframework.web.servlet.ModelAndView; // モデルとビューを同時に扱うためのクラスをインポート

import com.example.demo.meibo.MeiboService; // MeiboServiceをインポート

// コントローラークラス
@Controller
public class YourController {

    private final MeiboService meiboService; // MeiboServiceのインスタンスを保持するフィールド

    // コンストラクタによる依存性注入
    YourController(MeiboService meiboService) {
        this.meiboService = meiboService; // 渡されたMeiboServiceをフィールドに設定
    }

    // GETリクエストに対するハンドラメソッド
    @GetMapping("/somePage")
    public ModelAndView somePage(HttpSession session) {
        String userName = (String) session.getAttribute("userName"); // セッションからユーザー名を取得
        ModelAndView modelAndView = new ModelAndView("somePage"); // somePageはThymeleafのテンプレート名
        modelAndView.addObject("userName", userName); // モデルにユーザー名を追加
        return modelAndView; // モデルとビューを返す
    }

    // MeiboServiceのゲッターメソッド
    public MeiboService getMeiboService() {
        return meiboService; // MeiboServiceのインスタンスを返す
    }
}
