package com.example.demo.DeleteZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {
    @Autowired
    private DeleteService service; // MeiboForm ではなく DeleteService を使用

    @RequestMapping("/delete1")
    public String delete1() {
        return "delete1";
    }
    
    @PostMapping("/delete")
    public String delete(Model m, @RequestParam("id") String id) {
        int numId = Integer.parseInt(id);
        service.delete(numId); // DeleteService を使って削除を実行
        m.addAttribute("msg", "削除が正常に完了しました");
        return "delete2";
    }
}
