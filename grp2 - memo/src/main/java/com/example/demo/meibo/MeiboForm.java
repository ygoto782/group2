package com.example.demo.meibo;

import java.time.LocalDate; // 日付管理のためのクラス
import java.util.Objects; // オブジェクト操作のためのユーティリティクラス

public class MeiboForm {

    private Long id; // 社員IDをLong型で管理
    private String name; // 社員の名前
    private int age; // 年齢
    private LocalDate sdate; // 開始日（LocalDate型）
    private LocalDate edate; // 終了日（LocalDate型）
    private String password; // パスワード
    private String confirmPassword; // パスワード確認用

    // デフォルトコンストラクタ
    public MeiboForm() {}

    // 引数付きコンストラクタ
    public MeiboForm(Long id, String name, String password) { 
        this.id = id; // 社員IDの初期化
        this.name = name; // 名前の初期化
        this.password = password; // パスワードの初期化
    }

    // ゲッターとセッター
    public Long getId() {
        return id; // 社員IDを返す
    }

    public void setId(Long id) {
        this.id = id; // 社員IDを設定
    }

    public String getName() {
        return name; // 名前を返す
    }

    public void setName(String name) {
        this.name = name; // 名前を設定
    }

    public int getAge() {
        return age; // 年齢を返す
    }

    public void setAge(int age) {
        this.age = age; // 年齢を設定
    }

    public LocalDate getSdate() {
        return sdate; // 開始日を返す
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate; // 開始日を設定
    }

    public LocalDate getEdate() {
        return edate; // 終了日を返す
    }

    public void setEdate(LocalDate edate) {
        this.edate = edate; // 終了日を設定
    }

    public String getPassword() {
        return password; // パスワードを返す
    }

    public void setPassword(String password) {
        this.password = password; // パスワードを設定
    }

    public String getConfirmPassword() {
        return confirmPassword; // 確認用パスワードを返す
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword; // 確認用パスワードを設定
    }

    // フォームデータのバリデーションを行うメソッド
    public boolean isValid() {
        return isNameValid() && isPasswordValid() && isPasswordMatch() && isAgeValid() && isDateValid();
    }

    // 名前のバリデーション
    private boolean isNameValid() {
        return name != null && !name.isEmpty() && name.length() <= 100; // 空でなく、長さが100文字以内
    }

    // パスワードのバリデーション
    private boolean isPasswordValid() {
        return password != null && password.length() >= 8 && password.length() <= 20; // 8文字以上、20文字以下
    }

    // パスワードの一致を確認するメソッド
    private boolean isPasswordMatch() {
        return password != null && password.equals(confirmPassword); // 一致するかどうか
    }

    // 年齢のバリデーション
    private boolean isAgeValid() {
        return age >= 0; // 0以上であること
    }

    // 日付のバリデーション
    private boolean isDateValid() {
        return sdate != null && edate != null && !sdate.isAfter(edate); // 開始日が終了日より前であること
    }

    // equalsメソッドのオーバーライド
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 同じオブジェクト
        if (o == null || getClass() != o.getClass()) return false; // 型チェック
        MeiboForm meibo = (MeiboForm) o; // オブジェクトをMeiboForm型にキャスト
        return age == meibo.age && // 年齢が等しいか
                Objects.equals(id, meibo.id) && // IDが等しいか
                Objects.equals(name, meibo.name) && // 名前が等しいか
                Objects.equals(sdate, meibo.sdate) && // 開始日が等しいか
                Objects.equals(edate, meibo.edate); // 終了日が等しいか
    }

    // hashCodeメソッドのオーバーライド
    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sdate, edate); // ID、名前、年齢、日付でハッシュ値を計算
    }

    // toStringメソッドのオーバーライド
    @Override
    public String toString() {
        return "MeiboForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sdate=" + sdate +
                ", edate=" + edate +
                '}'; // フォームデータの文字列表示
    }
}
