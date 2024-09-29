package com.example.demo.DeleteZone;

import org.springframework.beans.factory.annotation.Autowired; // Springの依存注入アノテーション
import org.springframework.stereotype.Service; // サービス層を示すアノテーション

@Service // このクラスがサービス層のコンポーネントであることを示す
public class DeleteService {
    @Autowired
    private DeleteMapper mapper; // DeleteMapperを依存注入

    // 指定されたIDに基づいてレコードを削除するメソッド
    public void delete(int id) {
        mapper.delete(id); // DeleteMapperのdeleteメソッドを呼び出して削除処理を実行
    }
}
