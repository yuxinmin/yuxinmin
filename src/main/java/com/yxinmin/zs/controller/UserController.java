package com.yxinmin.zs.controller;

import com.yxinmin.zs.entity.Result;
import com.yxinmin.zs.entity.User;
import com.yxinmin.zs.service.UserService;
import com.yxinmin.zs.util.Md5Util;
import com.yxinmin.zs.util.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/reg.do")
    @ResponseBody
    public Object register(User user){
        Result result = new Result();
        UserError ue=service.register(user);
        switch (ue){
            case USERNAME_NULL:
                result.setSuccess(false);
                result.setMsg("用户名不能为空");
                break;
            case PASSWORD_NULL:
                result.setSuccess(false);
                result.setMsg("密码不能为空");
                break;
            case NICLNAEM_REPEAT:
                result.setSuccess(false);
                result.setMsg("昵称已经存在");
                break;
            case NAEM_REPEAT:
                result.setSuccess(false);
                result.setMsg("用户名已经存在");
                break;
            case NICLNAEM_NULL:
                result.setSuccess(false);
                result.setMsg("昵称不能为空");
                break;
        }
        return result;
    }


    @RequestMapping("/login.do")
    @ResponseBody
    public Object login(User user, HttpSession session) {
        Result result = new Result();
        UserError ue=service.login(user);
        switch (ue){
            case USERNAME_NULL:
                result.setSuccess(false);
                result.setMsg("用户名不能为空");
                break;
            case PASSWORD_NULL:
                result.setSuccess(false);
                result.setMsg("密码不能为空");
                break;
            case USERNAME_OR_PASSWORD_ERROR:
                result.setSuccess(false);
                result.setMsg("用户名或密码错误");
                break;
        }
        User user1=service.findByName(user.getName());

        result.setValue(user1);
        session.setAttribute("user",user1);
        return result;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public Object update(User user, String oldpwd,HttpSession session) {
        Result result = new Result();
        UserError ue=service.login(user);
        User byId = service.findById(user.getId());
        if(!byId.getPassword().equals(Md5Util.md5(oldpwd))){
            result.setSuccess(false);
            result.setMsg("原密码错误");
            return result;
        }else {
            service.updatePassword(user);
            return result;
        }


    }


    @RequestMapping("/exit.do")
    @ResponseBody
    public Object exit(HttpSession session){
        Result result = new Result();
        session.invalidate();
        return result;
    }



    @RequestMapping("/clearName")
    @ResponseBody
    public Object clearName(String name){

        return service.clearName(name);
    }
}
