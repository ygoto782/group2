package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MeiboForm> search(int id, Integer ageStart, Integer ageEnd, String name, LocalDate sdateStart, LocalDate sdateEnd, LocalDate edateStart, LocalDate edateEnd) {
        String sql = "SELECT * FROM meibo WHERE 1=1";

        // 動的にクエリを組み立てる
        List<Object> params = new ArrayList<>();
        if (id > 0) {
            sql += " AND id = ?";
            params.add(id);
        }
        if (ageStart != null) {
            sql += " AND age >= ?";
            params.add(ageStart);
        }
        if (ageEnd != null) {
            sql += " AND age <= ?";
            params.add(ageEnd);
        }
        if (name != null && !name.isEmpty()) {
            sql += " AND name LIKE ?";
            params.add("%" + name + "%");
        }
        if (sdateStart != null) {
            sql += " AND sdate >= ?";
            params.add(java.sql.Date.valueOf(sdateStart));
        }
        if (sdateEnd != null) {
            sql += " AND sdate <= ?";
            params.add(java.sql.Date.valueOf(sdateEnd));
        }
        if (edateStart != null) {
            sql += " AND edate >= ?";
            params.add(java.sql.Date.valueOf(edateStart));
        }
        if (edateEnd != null) {
            sql += " AND edate <= ?";
            params.add(java.sql.Date.valueOf(edateEnd));
        }

        // RowMapperのラムダ式実装
        RowMapper<MeiboForm> rowMapper = (rs, rowNum) -> {
            MeiboForm meibo = new MeiboForm();
            meibo.setId(rs.getInt("id"));
            meibo.setName(rs.getString("name"));
            meibo.setAge(rs.getInt("age"));
            meibo.setSdate(rs.getDate("sdate") != null ? rs.getDate("sdate").toLocalDate() : null);
            meibo.setEdate(rs.getDate("edate") != null ? rs.getDate("edate").toLocalDate() : null);
            meibo.setPassword(rs.getString("password"));
            return meibo;
        };

        // queryメソッドを使用 (非推奨のメソッドを避ける)
        return jdbcTemplate.query(sql, rowMapper, params.toArray());
    }
}
