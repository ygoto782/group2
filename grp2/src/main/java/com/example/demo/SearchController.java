package com.example.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "id", defaultValue = "") String idStr,
            @RequestParam(value = "age_start", defaultValue = "") String ageStartStr,
            @RequestParam(value = "age_end", defaultValue = "") String ageEndStr,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "sdate_start", defaultValue = "") String sdateStartStr,
            @RequestParam(value = "sdate_end", defaultValue = "") String sdateEndStr,
            @RequestParam(value = "edate_start", defaultValue = "") String edateStartStr,
            @RequestParam(value = "edate_end", defaultValue = "") String edateEndStr,
            Model model) {

        // バリデーション
        StringBuilder errors = new StringBuilder();
        Integer id = parseId(idStr, errors);
        Integer ageStart = parseInteger(ageStartStr, errors, "年齢");
        Integer ageEnd = parseInteger(ageEndStr, errors, "年齢");

        validateAgeRange(ageStart, ageEnd, errors);

        LocalDate sdateStart = parseDate(sdateStartStr, errors, "開始日");
        LocalDate sdateEnd = parseDate(sdateEndStr, errors, "開始日");
        LocalDate edateStart = parseDate(edateStartStr, errors, "終了日");
        LocalDate edateEnd = parseDate(edateEndStr, errors, "終了日");

        validateDateRange(sdateStart, sdateEnd, errors, "開始日");
        validateDateRange(edateStart, edateEnd, errors, "終了日");

        if (errors.length() > 0) {
            model.addAttribute("error", errors.toString());
            return "search";
        }

        List<Meibo> meiboList = searchService.search(id != null ? id : 0, 
                                                     ageStart != null ? ageStart : 0, 
                                                     ageEnd != null ? ageEnd : 0, 
                                                     name, 
                                                     sdateStart, 
                                                     sdateEnd, 
                                                     edateStart, 
                                                     edateEnd);
        model.addAttribute("meiboList", meiboList);
        model.addAttribute("count", meiboList.size());

        return "search";
    }

    private Integer parseId(String idStr, StringBuilder errors) {
        try {
            return idStr.isEmpty() ? null : Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            errors.append("社員IDは数値のみ許可されます。<br>");
            return null;
        }
    }

    private Integer parseInteger(String valueStr, StringBuilder errors, String fieldName) {
        try {
            return valueStr.isEmpty() ? null : Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            errors.append(fieldName + "は数値のみ許可されます。<br>");
            return null;
        }
    }

    private LocalDate parseDate(String dateStr, StringBuilder errors, String fieldName) {
        try {
            return dateStr.isEmpty() ? null : LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            errors.append(fieldName + "の形式はyyyy/MM/ddでなければなりません。<br>");
            return null;
        }
    }

    private void validateAgeRange(Integer ageStart, Integer ageEnd, StringBuilder errors) {
        if (ageStart != null && ageEnd != null && ageStart > ageEnd) {
            errors.append("年齢の範囲が正しくありません。<br>");
        }
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate, StringBuilder errors, String fieldName) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            errors.append(fieldName + "の範囲が正しくありません。<br>");
        }
    }
}
