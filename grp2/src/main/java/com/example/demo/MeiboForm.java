package com.example.demo;

import java.time.LocalDate;
import java.util.Objects;

public class MeiboForm {

    private int id; // 社員ID
    private String name; // 社員の名前
    private int age; // 年齢
    private LocalDate sdate; // 開始日
    private LocalDate edate; // 終了日
    private String password; // パスワード
    private Integer ageStart;
    private Integer ageEnd;
    private LocalDate sdateStart;
    private LocalDate sdateEnd;
    private LocalDate edateStart;
    private LocalDate edateEnd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Integer getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(Integer ageStart) {
        this.ageStart = ageStart;
    }

    public Integer getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(Integer ageEnd) {
        this.ageEnd = ageEnd;
    }

    public LocalDate getSdateStart() {
        return sdateStart;
    }

    public void setSdateStart(LocalDate sdateStart) {
        this.sdateStart = sdateStart;
    }

    public LocalDate getSdateEnd() {
        return sdateEnd;
    }

    public void setSdateEnd(LocalDate sdateEnd) {
        this.sdateEnd = sdateEnd;
    }

    public LocalDate getEdateStart() {
        return edateStart;
    }

    public void setEdateStart(LocalDate edateStart) {
        this.edateStart = edateStart;
    }

    public LocalDate getEdateEnd() {
        return edateEnd;
    }

    public void setEdateEnd(LocalDate edateEnd) {
        this.edateEnd = edateEnd;
    }

    // バリデーションメソッド: フォームのフィールドが有効かどうかをチェックする
    public boolean isValid() {
        return isNameValid() && isPasswordValid() && isAgeValid() && isDateValid();
    }

    // 名前のバリデーション: 名前が null でなく、空でなく、長さが 100 文字以下であるかをチェック
    private boolean isNameValid() {
        return name != null && !name.isEmpty() && name.length() <= 100;
    }

    // パスワードのバリデーション: パスワードが null でなく、長さが 8 文字以上 20 文字以下であるかをチェック
    private boolean isPasswordValid() {
        return password != null && password.length() >= 8 && password.length() <= 20;
    }

    // 年齢のバリデーション: 年齢が 0 以上であるかをチェック
    private boolean isAgeValid() {
        return age >= 0;
    }

    // 日付のバリデーション: 開始日が終了日より前であり、両方が null でないことをチェック
    private boolean isDateValid() {
        return sdate != null && edate != null && !sdate.isAfter(edate);
    }

    // オブジェクトの等価性を判断するメソッド: 他のオブジェクトと比較し、すべてのフィールドが一致するかを確認
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeiboForm meibo = (MeiboForm) o;
        return id == meibo.id &&
                age == meibo.age &&
                Objects.equals(name, meibo.name) &&
                Objects.equals(sdate, meibo.sdate) &&
                Objects.equals(edate, meibo.edate) &&
                Objects.equals(password, meibo.password);
    }

    // オブジェクトのハッシュコードを生成するメソッド: オブジェクトのハッシュコードを計算し、一意に識別できるようにする
    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sdate, edate, password);
    }

    // オブジェクトを文字列に変換するメソッド: オブジェクトの内容を文字列として表現する
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
