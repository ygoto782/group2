package com.example.demo.meibo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeiboService {

    @Autowired
    private MeiboRepository meiboRepository;

    public void saveMeibo(Meibo meibo) {
        meiboRepository.save(meibo);
    }

    public Meibo findById(Long id) {
        return meiboRepository.findById(id).orElse(null);
    }

    public Meibo findByName(String name) {
        return meiboRepository.findByName(name);
    }

    public List<Meibo> search(Long id, String name, Integer age_start, Integer age_end,
                              String sdate_start, String sdate_end,
                              String edate_start, String edate_end) {
        // 検索ロジックを実装し、条件に合致するリストを返す
        return meiboRepository.findByCriteria(id, name, age_start, age_end, sdate_start, sdate_end, edate_start, edate_end);
    }
}
