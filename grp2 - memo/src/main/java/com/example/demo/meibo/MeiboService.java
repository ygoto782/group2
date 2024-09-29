package com.example.demo.meibo;

import java.util.List; // Listクラスをインポート

import org.springframework.beans.factory.annotation.Autowired; // Springの依存性注入をインポート
import org.springframework.stereotype.Service; // サービス層を示すアノテーションをインポート

// ビジネスロジックを担当するサービスクラス
@Service
public class MeiboService {

    @Autowired // MeiboRepositoryのインスタンスを自動的に注入
    private MeiboRepository meiboRepository;

    // Meiboオブジェクトを保存するメソッド
    public void saveMeibo(Meibo meibo) {
        meiboRepository.save(meibo); // リポジトリを介してデータベースに保存
    }

    // IDによってMeiboを検索するメソッド
    public Meibo findById(Long id) {
        // findByIdメソッドを呼び出し、結果が存在しない場合はnullを返す
        return meiboRepository.findById(id).orElse(null);
    }

    // 名前によってMeiboを検索するメソッド
    public Meibo findByName(String name) {
        return meiboRepository.findByName(name); // 名前に基づいてリポジトリからMeiboを取得
    }

    // 検索条件に基づいてMeiboのリストを返すメソッド
    public List<Meibo> search(Long id, String name, Integer age_start, Integer age_end,
                              String sdate_start, String sdate_end,
                              String edate_start, String edate_end) {
        // 複数の条件を使ってMeiboを検索し、結果をリストとして返す
        return meiboRepository.findByCriteria(id, name, age_start, age_end, sdate_start, sdate_end, edate_start, edate_end);
    }
}
