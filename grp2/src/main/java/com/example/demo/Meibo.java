package com.example.demo;

import java.time.LocalDate;
import java.util.Objects;

public class Meibo {
    
    private int id;
    private String name;
    private int age;
    private LocalDate sdate;
    private LocalDate edate;
    private String password;

    // Getters and Setters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meibo meibo = (Meibo) o;
        return id == meibo.id &&
                age == meibo.age &&
                Objects.equals(name, meibo.name) &&
                Objects.equals(sdate, meibo.sdate) &&
                Objects.equals(edate, meibo.edate) &&
                Objects.equals(password, meibo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sdate, edate, password);
    }

    @Override
    public String toString() {
        return "Meibo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sdate=" + sdate +
                ", edate=" + edate +
                ", password='" + password + '\'' +
                '}';
    }
}
