package com.example.demo.meibo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeiboRepository extends JpaRepository<Meibo, Long> {
    Meibo findByName(String name);

    @Query("SELECT m FROM Meibo m WHERE (:id IS NULL OR m.id = :id) " +
           "AND (:name IS NULL OR m.name LIKE %:name%) " +
           "AND (:age_start IS NULL OR m.age >= :age_start) " +
           "AND (:age_end IS NULL OR m.age <= :age_end) " +
           "AND (:sdate_start IS NULL OR m.sdate >= :sdate_start) " +
           "AND (:sdate_end IS NULL OR m.sdate <= :sdate_end) " +
           "AND (:edate_start IS NULL OR m.edate >= :edate_start) " +
           "AND (:edate_end IS NULL OR m.edate <= :edate_end)")
    List<Meibo> findByCriteria(@Param("id") Long id,
                                @Param("name") String name,
                                @Param("age_start") Integer age_start,
                                @Param("age_end") Integer age_end,
                                @Param("sdate_start") String sdate_start,
                                @Param("sdate_end") String sdate_end,
                                @Param("edate_start") String edate_start,
                                @Param("edate_end") String edate_end);
}
