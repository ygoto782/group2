package com.example.demo.meibo;

import java.time.LocalDate;
import java.util.Objects;

public class MeiboForm {

    private Long id; // 社員IDをLong型に変更
    private String name; // 社員の名前
    private int age; // 年齢
    private LocalDate sdate; // 開始日
    private LocalDate edate; // 終了日
    private String password; // パスワード
    private String confirmPassword; // パスワード確認

    public MeiboForm() {}

    // コンストラクタ
    public MeiboForm(Long id, String name, String password) { // 引数の型を変更
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // ゲッターとセッター
    public Long getId() { // 戻り値の型を変更
        return id;
    }

    public void setId(Long id) { // 引数の型を変更
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getSdate() {
        return sdate;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public LocalDate getEdate() {
        return edate;
    }

    public void setEdate(LocalDate edate) {
        this.edate = edate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // バリデーションメソッド
    public boolean isValid() {
        return isNameValid() && isPasswordValid() && isPasswordMatch() && isAgeValid() && isDateValid();
    }

    private boolean isNameValid() {
        return name != null && !name.isEmpty() && name.length() <= 100;
    }

    private boolean isPasswordValid() {
        return password != null && password.length() >= 8 && password.length() <= 20;
    }

    private boolean isPasswordMatch() {
        return password != null && password.equals(confirmPassword);
    }

    private boolean isAgeValid() {
        return age >= 0;
    }

    private boolean isDateValid() {
        return sdate != null && edate != null && !sdate.isAfter(edate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeiboForm meibo = (MeiboForm) o;
        return age == meibo.age &&
                Objects.equals(id, meibo.id) && // idをLong型に変更
                Objects.equals(name, meibo.name) &&
                Objects.equals(sdate, meibo.sdate) &&
                Objects.equals(edate, meibo.edate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sdate, edate); // idをLong型に変更
    }

    @Override
    public String toString() {
        return "MeiboForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sdate=" + sdate +
                ", edate=" + edate +
                '}';
    }
}
