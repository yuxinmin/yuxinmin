package com.yxinmin.zs.dao;

import com.yxinmin.zs.entity.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void delete(String id);
    void update(User user);
    User findById(String id);
    List<User> findAll();
    User findByName(String name);
    User findByNickName(String nickName);
}
