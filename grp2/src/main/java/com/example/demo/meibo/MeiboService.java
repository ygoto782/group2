
package com.example.demo.meibo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeiboService {

    @Autowired
    private MeiboRepository meiboRepository;

    public void saveMeibo(Meibo meibo) {

        meiboRepository.save(meibo);
    }
}