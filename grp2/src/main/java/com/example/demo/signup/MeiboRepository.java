
package com.example.demo.signup;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.meibo.Meibo;

public interface MeiboRepository extends JpaRepository<Meibo, Long> {
}