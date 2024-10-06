package com.example.demo.search;

import java.time.LocalDate; // 日付を扱うためのクラスをインポート
import java.util.ArrayList; // ArrayListを使用するためのインポート
import java.util.List; // リストを扱うためのクラスをインポート

import org.springframework.beans.factory.annotation.Autowired; // 自動的に依存性注入を行うためのアノテーションをインポート
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate; // JDBCを使ったデータベース操作を行うためのクラスをインポート
import org.springframework.jdbc.core.RowMapper; // 結果セットをオブジェクトにマッピングするためのクラスをインポート
import org.springframework.stereotype.Service; // サービス層を示すアノテーションをインポート

import com.example.demo.meibo.MeiboForm; // MeiboFormクラスをインポート

@Service
public class SearchService {

    @Autowired // JdbcTemplateのインスタンスを自動的に注入
    private JdbcTemplate jdbcTemplate;

    /**
     * 社員情報を検索するメソッド
     * `MeiboForm` のリストとして返します。
     * 
     * @param id 社員ID（0の場合は条件に含まれません）
     * @param name 社員名（nullまたは空文字の場合は条件に含まれません）
     * @param ageStart 年齢の開始範囲（nullの場合は条件に含まれません）
     * @param ageEnd 年齢の終了範囲（nullの場合は条件に含まれません）
     * @param sdateStart 開始日の開始範囲（nullの場合は条件に含まれません）
     * @param sdateEnd 開始日の終了範囲（nullの場合は条件に含まれません）
     * @param edateStart 終了日の開始範囲（nullの場合は条件に含まれません）
     * @param edateEnd 終了日の終了範囲（nullの場合は条件に含まれません）
     * @return 検索結果の社員情報リスト
     */
    public List<com.example.demo.meibo.MeiboForm> search(Long id, String name, Integer ageStart, Integer ageEnd, LocalDate sdateStart, LocalDate sdateEnd, LocalDate edateStart, LocalDate edateEnd) {
        StringBuilder sql = new StringBuilder("SELECT * FROM meibo WHERE 1=1"); // SQLクエリの初期化
        List<Object> params = new ArrayList<>(); // クエリパラメータを格納するリスト

        // 社員IDが指定されている場合
        if (id != null && id > 0) {
            sql.append(" AND id = ?"); // SQLに条件を追加
            params.add(id); // パラメータにIDを追加
        }
        // 社員名が指定されている場合
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?"); // SQLに条件を追加
            params.add("%" + name + "%"); // 部分一致検索用にワイルドカードを追加
        }

        // 年齢の範囲が指定されている場合
        if (ageStart != null) {
            sql.append(" AND age >= ?"); // SQLに条件を追加
            params.add(ageStart); // パラメータに年齢開始を追加
        }
        if (ageEnd != null) {
            sql.append(" AND age <= ?"); // SQLに条件を追加
            params.add(ageEnd); // パラメータに年齢終了を追加
        }

        // 開始日の範囲が指定されている場合
        if (sdateStart != null) {
            sql.append(" AND sdate >= ?"); // SQLに条件を追加
            params.add(java.sql.Date.valueOf(sdateStart)); // パラメータに開始日を追加
        }
        if (sdateEnd != null) {
            sql.append(" AND sdate <= ?"); // SQLに条件を追加
            params.add(java.sql.Date.valueOf(sdateEnd)); // パラメータに終了日を追加
        }

        // 終了日の範囲が指定されている場合
        if (edateStart != null) {
            sql.append(" AND edate >= ?"); // SQLに条件を追加
            params.add(java.sql.Date.valueOf(edateStart)); // パラメータに終了日を追加
        }
        if (edateEnd != null) {
            sql.append(" AND edate <= ?"); // SQLに条件を追加
            params.add(java.sql.Date.valueOf(edateEnd)); // パラメータに終了日を追加
        }

        // RowMapperを使用して結果セットをMeiboFormにマッピング
        RowMapper<MeiboForm> rowMapper = (rs, rowNum) -> {
            MeiboForm meibo = new MeiboForm(); // MeiboFormのインスタンスを生成
            meibo.setId(rs.getLong("id")); // IDを設定
            meibo.setName(rs.getString("name")); // 名前を設定
            meibo.setAge(rs.getInt("age")); // 年齢を設定
            meibo.setSdate(rs.getDate("sdate") != null ? rs.getDate("sdate").toLocalDate() : null); // 開始日を設定
            meibo.setEdate(rs.getDate("edate") != null ? rs.getDate("edate").toLocalDate() : null); // 終了日を設定
            meibo.setPassword(rs.getString("password")); // パスワードを設定
            return meibo; // MeiboFormを返す
        };

        // 構築したSQLクエリとパラメータでデータベースをクエリし、結果を返す
        try {
            return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray()); // クエリを実行
        } catch (DataAccessException e) {
            // エラーハンドリングの追加
            throw new RuntimeException("検索中にエラーが発生しました", e); // エラーをスロー
        }
    }

    /**
     * 指定されたIDの社員情報を取得するメソッド
     * 
     * @param id 社員ID
     * @return 指定されたIDの社員情報
     */
    public MeiboForm findById(Long id) {
        String sql = "SELECT * FROM meibo WHERE id = ?"; // SQLクエリ

        RowMapper<MeiboForm> rowMapper = (rs, rowNum) -> {
            MeiboForm meibo = new MeiboForm(); // MeiboFormのインスタンスを生成
            meibo.setId(rs.getLong("id")); // IDを設定
            meibo.setName(rs.getString("name")); // 名前を設定
            meibo.setAge(rs.getInt("age")); // 年齢を設定
            meibo.setSdate(rs.getDate("sdate") != null ? rs.getDate("sdate").toLocalDate() : null); // 開始日を設定
            meibo.setEdate(rs.getDate("edate") != null ? rs.getDate("edate").toLocalDate() : null); // 終了日を設定
            meibo.setPassword(rs.getString("password")); // パスワードを設定
            return meibo; // MeiboFormを返す
        };

        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id); // クエリを実行し、1件を取得
        } catch (DataAccessException e) {
            // エラーハンドリングの追加
            throw new RuntimeException("指定されたIDの社員情報取得中にエラーが発生しました", e); // エラーをスロー
        }
    }

    /**
     * 指定された社員情報を更新するメソッド
     * 
     * @param meiboForm 更新する社員情報を含むフォーム
     */
    public void updateEmployee(MeiboForm meiboForm) {
        String sql = "UPDATE meibo SET name = ?, age = ?, sdate = ?, edate = ?, password = ? WHERE id = ?"; // 更新クエリ
        
        try {
            jdbcTemplate.update(sql,
                meiboForm.getName(), // 名前
                meiboForm.getAge(), // 年齢
                meiboForm.getSdate() != null ? java.sql.Date.valueOf(meiboForm.getSdate()) : null, // 開始日
                meiboForm.getEdate() != null ? java.sql.Date.valueOf(meiboForm.getEdate()) : null, // 終了日
                meiboForm.getPassword(), // パスワード
                meiboForm.getId() // ID
            );
        } catch (DataAccessException e) {
            // エラーハンドリングの追加
            throw new RuntimeException("社員情報の更新中にエラーが発生しました", e); // エラーをスロー
        }
    }
}
