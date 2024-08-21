package com.example.demo.search;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.MeiboForm;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String getSearch(
            @RequestParam(name = "id", defaultValue = "0") int id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "ageStart", required = false) Integer ageStart,
            @RequestParam(name = "ageEnd", required = false) Integer ageEnd,
            @RequestParam(name = "sdateStart", required = false) String sdateStart,
            @RequestParam(name = "sdateEnd", required = false) String sdateEnd,
            @RequestParam(name = "edateStart", required = false) String edateStart,
            @RequestParam(name = "edateEnd", required = false) String edateEnd,
            Model model) {

        // 入力チェックと変換
        String errorMessage = validateInputs(ageStart, ageEnd, sdateStart, sdateEnd, edateStart, edateEnd);

        if (errorMessage != null) {
            model.addAttribute("meiboList", null);
            model.addAttribute("count", 0);
            model.addAttribute("errorMessage", errorMessage);
        } else {
            List<MeiboForm> results;
            try {
                LocalDate sdateStartLocal = sdateStart != null ? LocalDate.parse(sdateStart) : null;
                LocalDate sdateEndLocal = sdateEnd != null ? LocalDate.parse(sdateEnd) : null;
                LocalDate edateStartLocal = edateStart != null ? LocalDate.parse(edateStart) : null;
                LocalDate edateEndLocal = edateEnd != null ? LocalDate.parse(edateEnd) : null;

                results = searchService.search(id, name, ageStart, ageEnd, sdateStartLocal, sdateEndLocal, edateStartLocal, edateEndLocal);
                model.addAttribute("meiboList", results);
                model.addAttribute("count", results.size());
                model.addAttribute("errorMessage", null); // エラーメッセージは無し
            } catch (Exception e) {
                model.addAttribute("meiboList", null);
                model.addAttribute("count", 0);
                model.addAttribute("errorMessage", "検索処理中にエラーが発生しました。");
            }
        }
        return "search1";
    }

    private String validateInputs(Integer ageStart, Integer ageEnd, String sdateStart, String sdateEnd, String edateStart, String edateEnd) {
        // 年齢の範囲チェック
        if (ageStart != null && ageEnd != null && ageStart > ageEnd) {
            return "年齢の範囲が無効です。";
        }
        // 開始日と終了日の範囲チェック
        try {
            if (sdateStart != null && sdateEnd != null && LocalDate.parse(sdateStart).isAfter(LocalDate.parse(sdateEnd))) {
                return "開始日が終了日を上回っています。";
            }
            if (edateStart != null && edateEnd != null && LocalDate.parse(edateStart).isAfter(LocalDate.parse(edateEnd))) {
                return "終了日が終了日を上回っています。";
            }
        } catch (DateTimeParseException e) {
            return "日付の形式が無効です。";
        }
        return null;
    }
}
