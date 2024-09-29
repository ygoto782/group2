package com.example.demo.meibo;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // このクラスはデータベースのエンティティ（テーブル）を表します
public class Meibo {
    @Id // 主キーを指定します
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDは自動生成されます
    private Long id; // 社員ID

    private String name;  // 社員名（usernameに相当）
    private int age; // 年齢
    private String password; // パスワード
    private LocalDate sdate; // 開始日
    private LocalDate edate; // 終了日

    // GetterとSetterメソッド
    public Long getId() {
        return id; // IDを取得します
    }

    public void setId(Long id) {
        this.id = id; // IDを設定します
    }

    public String getName() {
        return name; // 社員名を取得します
    }

    public void setName(String name) {
        this.name = name; // 社員名を設定します
    }

    public int getAge() {
        return age; // 年齢を取得します
    }

    public void setAge(int age) {
        this.age = age; // 年齢を設定します
    }

    public String getPassword() {
        return password; // パスワードを取得します
    }

    public void setPassword(String password) {
        this.password = password; // パスワードを設定します
    }

    public LocalDate getSdate() {
        return sdate; // 開始日を取得します
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate; // 開始日を設定します
    }

    public LocalDate getEdate() {
        return edate; // 終了日を取得します
    }

    public void setEdate(LocalDate edate) {
        this.edate = edate; // 終了日を設定します
    }
}
