package com.example.demo.search;

import java.time.LocalDate; // 日付を扱うためのクラスをインポート
import java.time.format.DateTimeParseException; // 日付解析例外をインポート
import java.util.List; // リストを扱うためのクラスをインポート

import jakarta.servlet.http.HttpSession; // HTTPセッションを扱うためのクラスをインポート

import org.springframework.beans.factory.annotation.Autowired; // 自動的に依存性注入を行うためのアノテーションをインポート
import org.springframework.stereotype.Controller; // コントローラー層を示すアノテーションをインポート
import org.springframework.ui.Model; // モデルを扱うためのクラスをインポート
import org.springframework.web.bind.annotation.GetMapping; // GETリクエストの処理を行うアノテーションをインポート
import org.springframework.web.bind.annotation.RequestParam; // リクエストパラメータを取得するためのアノテーションをインポート

// 検索機能を担当するコントローラー
@Controller
public class SearchController {

    @Autowired // SearchServiceのインスタンスを自動的に注入
    private SearchService searchService;

    // 検索ページのGETリクエストを処理するメソッド
    @GetMapping("/search")
    public String getSearch(
            @RequestParam(name = "id", defaultValue = "0") Long id, // IDパラメータ（デフォルトは0）
            @RequestParam(name = "name", required = false) String name, // 名前（オプション）
            @RequestParam(name = "ageStart", required = false) Integer ageStart, // 年齢開始（オプション）
            @RequestParam(name = "ageEnd", required = false) Integer ageEnd, // 年齢終了（オプション）
            @RequestParam(name = "sdateStart", required = false) String sdateStart, // 開始日（オプション）
            @RequestParam(name = "sdateEnd", required = false) String sdateEnd, // 終了日（オプション）
            @RequestParam(name = "edateStart", required = false) String edateStart, // 終了日（オプション）
            @RequestParam(name = "edateEnd", required = false) String edateEnd, // 終了日（オプション）
            HttpSession session, // HTTPセッションを取得
            Model model) { // モデルを使用してビューにデータを渡す

        // ユーザー名をセッションから取得し、モデルに追加
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        // 入力チェックと変換
        String errorMessage = validateInputs(ageStart, ageEnd, sdateStart, sdateEnd, edateStart, edateEnd);

        if (errorMessage != null) {
            model.addAttribute("meiboList", null); // 結果リストをnullに設定
            model.addAttribute("count", 0); // 件数を0に設定
            model.addAttribute("errorMessage", errorMessage); // エラーメッセージをモデルに追加
        } else {
            List<com.example.demo.meibo.MeiboForm> results;
            try {
                // 文字列の日付をLocalDate型に変換
                LocalDate sdateStartLocal = sdateStart != null ? LocalDate.parse(sdateStart) : null;
                LocalDate sdateEndLocal = sdateEnd != null ? LocalDate.parse(sdateEnd) : null;
                LocalDate edateStartLocal = edateStart != null ? LocalDate.parse(edateStart) : null;
                LocalDate edateEndLocal = edateEnd != null ? LocalDate.parse(edateEnd) : null;

                // 検索を実行し、結果を取得
                results = searchService.search(id, name, ageStart, ageEnd, sdateStartLocal, sdateEndLocal, edateStartLocal, edateEndLocal);
                model.addAttribute("meiboList", results); // 結果をモデルに追加
                model.addAttribute("count", results.size()); // 件数を設定
                model.addAttribute("errorMessage", null); // エラーメッセージは無し
            } catch (Exception e) {
                model.addAttribute("meiboList", null); // 結果リストをnullに設定
                model.addAttribute("count", 0); // 件数を0に設定
                model.addAttribute("errorMessage", "検索処理中にエラーが発生しました。"); // エラーメッセージを設定
            }
        }
        return "search1"; // 検索結果を表示するテンプレートを返す
    }

    // 入力チェックを行うメソッド
    private String validateInputs(Integer ageStart, Integer ageEnd, String sdateStart, String sdateEnd, String edateStart, String edateEnd) {
        // 年齢の範囲チェック
        if (ageStart != null && ageEnd != null && ageStart > ageEnd) {
            return "年齢の範囲が無効です。"; // 年齢の範囲が不正な場合のエラーメッセージ
        }
        // 開始日と終了日の範囲チェック
        try {
            if (sdateStart != null && sdateEnd != null && LocalDate.parse(sdateStart).isAfter(LocalDate.parse(sdateEnd))) {
                return "開始日が終了日を上回っています。"; // 開始日が終了日を上回っている場合のエラーメッセージ
            }
            if (edateStart != null && edateEnd != null && LocalDate.parse(edateStart).isAfter(LocalDate.parse(edateEnd))) {
                return "終了日が終了日を上回っています。"; // 終了日が終了日を上回っている場合のエラーメッセージ
            }
        } catch (DateTimeParseException e) {
            return "日付の形式が無効です。"; // 日付の形式が不正な場合のエラーメッセージ
        }
        return null; // 問題がなければnullを返す
    }
}
