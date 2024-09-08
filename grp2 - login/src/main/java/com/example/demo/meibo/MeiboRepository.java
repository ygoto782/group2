package com.example.demo.meibo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeiboRepository extends JpaRepository<Meibo, Long> {
    Meibo findByName(String name);
}
