package com.yxinmin.zs.dao;

import com.yxinmin.zs.entity.NotebookType;

import java.util.List;

public interface NotebookTypeDao {
    NotebookType findNormal();
    List<NotebookType> findSpecail();
}
