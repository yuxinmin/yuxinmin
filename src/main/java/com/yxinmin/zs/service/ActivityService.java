package com.yxinmin.zs.service;

import com.yxinmin.zs.dao.AcitvityDao;
import com.yxinmin.zs.entity.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {


    @Autowired
    private AcitvityDao acitvityDao;

    @Transactional
    public List<Activity> activitiesList(){
        return acitvityDao.findAll();
    }
}
