package com.example.demo.meibo;

import java.util.List; // Listクラスをインポート

import org.springframework.data.jpa.repository.JpaRepository; // JPAリポジトリのインターフェースをインポート
import org.springframework.data.jpa.repository.Query; // JPQLクエリを使用するためのアノテーションをインポート
import org.springframework.data.repository.query.Param; // クエリパラメータをバインドするためのアノテーションをインポート

// Meiboエンティティに対するデータアクセスを提供するリポジトリインターフェース
public interface MeiboRepository extends JpaRepository<Meibo, Long> {
    
    // 名前でMeiboを検索するメソッド
    Meibo findByName(String name);

    // 複数の条件でMeiboを検索するためのカスタムクエリ
    @Query("SELECT m FROM Meibo m WHERE (:id IS NULL OR m.id = :id) " +
           "AND (:name IS NULL OR m.name LIKE %:name%) " + // 名前の部分一致検索
           "AND (:age_start IS NULL OR m.age >= :age_start) " + // 年齢の下限
           "AND (:age_end IS NULL OR m.age <= :age_end) " + // 年齢の上限
           "AND (:sdate_start IS NULL OR m.sdate >= :sdate_start) " + // 開始日の下限
           "AND (:sdate_end IS NULL OR m.sdate <= :sdate_end) " + // 開始日の上限
           "AND (:edate_start IS NULL OR m.edate >= :edate_start) " + // 終了日の下限
           "AND (:edate_end IS NULL OR m.edate <= :edate_end)") // 終了日の上限
    List<Meibo> findByCriteria(@Param("id") Long id, // IDパラメータ
                                @Param("name") String name, // 名前パラメータ
                                @Param("age_start") Integer age_start, // 年齢下限パラメータ
                                @Param("age_end") Integer age_end, // 年齢上限パラメータ
                                @Param("sdate_start") String sdate_start, // 開始日下限パラメータ
                                @Param("sdate_end") String sdate_end, // 開始日上限パラメータ
                                @Param("edate_start") String edate_start, // 終了日下限パラメータ
                                @Param("edate_end") String edate_end); // 終了日上限パラメータ
}
