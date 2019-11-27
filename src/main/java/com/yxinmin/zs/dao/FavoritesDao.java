package com.yxinmin.zs.dao;

import com.yxinmin.zs.entity.Favorites;

import java.util.List;

public interface FavoritesDao {
    void add(Favorites favorites);
    List<Favorites> findByNotebookId(String id);
    void delete(String id);
    Favorites findOne(Favorites favorites);
}
