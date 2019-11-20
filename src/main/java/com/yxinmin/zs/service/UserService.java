package com.yxinmin.zs.service;

import com.yxinmin.zs.dao.UserDao;
import com.yxinmin.zs.entity.User;
import com.yxinmin.zs.util.Md5Util;
import com.yxinmin.zs.util.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
public class UserService {
    @Autowired
    private UserDao dao;

    @Autowired
    private NotebookService notebookService;
    @Transactional
    public UserError register(User user){
        if(user.getName()==null||user.getName().trim().length()==0){
            return  UserError.USERNAME_NULL;
        }
        if(user.getPassword()==null||user.getPassword().trim().length()==0){
            return  UserError.PASSWORD_NULL;
        }
        User user1=dao.findByName(user.getName());
        if(user1!=null){
            return UserError.NAEM_REPEAT;
        }
        User u = dao.findByNickName(user.getNickName());
        if(u!=null){
            return UserError.NICLNAEM_REPEAT;
        }
        if(user.getNickName()==null||user.getNickName().trim().length()==0){
            return UserError.NICLNAEM_NULL;
        }
        String id = UUID.randomUUID().toString().replace("-","");
        user.setId(id);
        String password=Md5Util.md5(user.getPassword());
        user.setPassword(password);
        dao.add(user);
        notebookService.initSpecialNotbook(user.getId());
        return UserError.SUCCESS;
    }

    @Transactional
    public UserError login(User user){
        if(user.getName()==null||user.getName().trim().length()==0){
            return  UserError.USERNAME_NULL;
        }
        if(user.getPassword()==null||user.getPassword().trim().length()==0){
            return  UserError.PASSWORD_NULL;
        }
        User u = dao.findByName(user.getName());
        if(u==null||!u.getPassword().equals(Md5Util.md5(user.getPassword()))){
            return UserError.USERNAME_OR_PASSWORD_ERROR;
        }
        return UserError.SUCCESS;
    }
    @Transactional
    public User findByName(String name){
        return dao.findByName(name);
    }
    @Transactional
    public User findById(String id){
        return  dao.findById(id);
    }
    @Transactional
    public void updatePassword(User user){
        String password=Md5Util.md5(user.getPassword());
        user.setPassword(password);
        dao.update(user);
    }

    @Transactional
    public boolean clearName(String name){
        return dao.findByName(name)==null;
    }
}
