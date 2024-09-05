
package com.example.demo.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.meibo.Meibo;

@Service
public class MeiboService {

    @Autowired
    private MeiboRepository meiboRepository;

    public void saveMeibo(Meibo meibo) {

        meiboRepository.save(meibo);
    }
}