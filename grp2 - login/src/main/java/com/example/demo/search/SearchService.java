package com.example.demo.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.demo.meibo.MeiboForm;

@Service
public class SearchService {

    @Autowired
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
        StringBuilder sql = new StringBuilder("SELECT * FROM meibo WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 社員IDが指定されている場合
        if (id != null && id > 0) { // idの型をLongに変更
            sql.append(" AND id = ?");
            params.add(id);
        }
        // 社員名が指定されている場合
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }

        // 年齢の範囲が指定されている場合
        if (ageStart != null) {
            sql.append(" AND age >= ?");
            params.add(ageStart);
        }
        if (ageEnd != null) {
            sql.append(" AND age <= ?");
            params.add(ageEnd);
        }

        // 開始日の範囲が指定されている場合
        if (sdateStart != null) {
            sql.append(" AND sdate >= ?");
            params.add(java.sql.Date.valueOf(sdateStart));
        }
        if (sdateEnd != null) {
            sql.append(" AND sdate <= ?");
            params.add(java.sql.Date.valueOf(sdateEnd));
        }

        // 終了日の範囲が指定されている場合
        if (edateStart != null) {
            sql.append(" AND edate >= ?");
            params.add(java.sql.Date.valueOf(edateStart));
        }
        if (edateEnd != null) {
            sql.append(" AND edate <= ?");
            params.add(java.sql.Date.valueOf(edateEnd));
        }

        // RowMapperを使用して結果セットをMeiboFormにマッピング
        RowMapper<MeiboForm> rowMapper = (rs, rowNum) -> {
            MeiboForm meibo = new MeiboForm();
            meibo.setId(rs.getLong("id")); // idをLong型に変更
            meibo.setName(rs.getString("name"));
            meibo.setAge(rs.getInt("age"));
            meibo.setSdate(rs.getDate("sdate") != null ? rs.getDate("sdate").toLocalDate() : null);
            meibo.setEdate(rs.getDate("edate") != null ? rs.getDate("edate").toLocalDate() : null);
            meibo.setPassword(rs.getString("password"));
            return meibo;
        };

        // 構築したSQLクエリとパラメータでデータベースをクエリし、結果を返す
        try {
            return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
        } catch (Exception e) {
            // エラーハンドリングの追加
            throw new RuntimeException("検索中にエラーが発生しました", e);
        }
    }

    /**
     * 指定されたIDの社員情報を取得するメソッド
     * 
     * @param id 社員ID
     * @return 指定されたIDの社員情報
     */
    public MeiboForm findById(Long id) { // idの型をLongに変更
        String sql = "SELECT * FROM meibo WHERE id = ?";
        
        RowMapper<MeiboForm> rowMapper = (rs, rowNum) -> {
            MeiboForm meibo = new MeiboForm();
            meibo.setId(rs.getLong("id")); // idをLong型に変更
            meibo.setName(rs.getString("name"));
            meibo.setAge(rs.getInt("age"));
            meibo.setSdate(rs.getDate("sdate") != null ? rs.getDate("sdate").toLocalDate() : null);
            meibo.setEdate(rs.getDate("edate") != null ? rs.getDate("edate").toLocalDate() : null);
            meibo.setPassword(rs.getString("password"));
            return meibo;
        };

        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            // エラーハンドリングの追加
            throw new RuntimeException("指定されたIDの社員情報取得中にエラーが発生しました", e);
        }
    }

    /**
     * 指定された社員情報を更新するメソッド
     * 
     * @param meiboForm 更新する社員情報を含むフォーム
     */
    public void updateEmployee(MeiboForm meiboForm) {
        String sql = "UPDATE meibo SET name = ?, age = ?, sdate = ?, edate = ?, password = ? WHERE id = ?";
        
        try {
            jdbcTemplate.update(sql,
                meiboForm.getName(),
                meiboForm.getAge(),
                meiboForm.getSdate() != null ? java.sql.Date.valueOf(meiboForm.getSdate()) : null,
                meiboForm.getEdate() != null ? java.sql.Date.valueOf(meiboForm.getEdate()) : null,
                meiboForm.getPassword(),
                meiboForm.getId() // ここはLong型で渡す
            );
        } catch (Exception e) {
            // エラーハンドリングの追加
            throw new RuntimeException("社員情報の更新中にエラーが発生しました", e);
        }
    }
}
