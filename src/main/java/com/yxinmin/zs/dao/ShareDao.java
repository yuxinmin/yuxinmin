package com.yxinmin.zs.dao;

import com.yxinmin.zs.entity.Share;

import java.util.List;

public interface ShareDao {
    void add(Share share);
    List<Share> findLikeTitle(String title);
}
