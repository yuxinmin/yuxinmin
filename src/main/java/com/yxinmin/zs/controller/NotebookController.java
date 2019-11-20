package com.yxinmin.zs.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notebook")
public class NotebookController {
    @GetMapping
    public Object notebookList(){
        System.out.println("列表");
        return null;
    }
    @PostMapping
    public Object addNotebook(){
        System.out.println("添加");
        return null;
    }
    @PutMapping
    public Object updateNotebook(){
        System.out.println("修改");
        return null;
    }
    @DeleteMapping
    public Object deleteNotebook(){
        System.out.println("删除");
        return null;
    }

}
