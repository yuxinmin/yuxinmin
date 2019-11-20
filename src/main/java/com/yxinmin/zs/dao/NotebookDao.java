package com.yxinmin.zs.dao;


import com.yxinmin.zs.entity.Notebook;

import java.util.List;

public interface NotebookDao {
    void add(Notebook notebook);
    void update(Notebook notebook);
    void delete(String id);
    List<Notebook> findBySpecail(String userId);
    List<Notebook> findByNormal(String userId);
    Notebook findByName(Notebook notebook);
}
