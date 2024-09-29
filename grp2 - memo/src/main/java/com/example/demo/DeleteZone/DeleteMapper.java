package com.example.demo.DeleteZone;

import org.apache.ibatis.annotations.Delete; // MyBatisの@Deleteアノテーションをインポート
import org.apache.ibatis.annotations.Mapper; // MyBatisの@Mapperアノテーションをインポート

@Mapper // このインターフェースがMyBatisのマッパーであることを示す
public interface DeleteMapper {
    // 指定されたIDに基づいてmeiboテーブルからレコードを削除するSQL文を定義
    @Delete("DELETE FROM meibo WHERE id = #{id}")
    void delete(int id); // 削除メソッドの定義
}
