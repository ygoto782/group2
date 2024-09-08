package com.example.demo.DeleteZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    @Autowired
    private DeleteMapper mapper; // MeiboForm ではなく DeleteMapper を使用

    public void delete(int id) {
        mapper.delete(id);
    }
}
