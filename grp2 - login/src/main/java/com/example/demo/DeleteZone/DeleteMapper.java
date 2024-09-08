package com.example.demo.DeleteZone;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeleteMapper {
    @Delete("DELETE FROM meibo WHERE id = #{id}")
    void delete(int id);
}
