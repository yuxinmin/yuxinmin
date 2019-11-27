package com.yxinmin.zs.controller;

import com.yxinmin.zs.entity.Share;
import com.yxinmin.zs.entity.User;
import com.yxinmin.zs.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Autowired
    private ShareService shareService;


    @PostMapping
    public boolean addShare(String noteId, HttpSession session){
        User user = (User) session.getAttribute("user");
        return shareService.addShare(noteId,user.getName());
    }

    @GetMapping
    public List<Share> shareList(String title){
        return shareService.shareList(title);
    }
}
