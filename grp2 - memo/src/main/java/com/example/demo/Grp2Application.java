package com.example.demo;

import org.springframework.boot.SpringApplication; // Springアプリケーションの起動に使用するクラスをインポート
import org.springframework.boot.autoconfigure.SpringBootApplication; // 自動構成を有効にするためのアノテーションをインポート
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration; // セキュリティ自動構成をインポート

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // Spring Bootアプリケーションのエントリーポイントを示し、セキュリティ自動構成を除外
public class Grp2Application {

    /**
     * アプリケーションのエントリーポイント
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(Grp2Application.class, args); // Springアプリケーションを起動
    }
}
