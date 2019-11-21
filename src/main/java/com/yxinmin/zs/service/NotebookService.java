package com.yxinmin.zs.service;

import com.yxinmin.zs.dao.NotebookDao;
import com.yxinmin.zs.dao.NotebookTypeDao;
import com.yxinmin.zs.entity.Notebook;
import com.yxinmin.zs.entity.NotebookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class NotebookService {
    @Autowired
    private NotebookDao notebookDao;
    @Autowired
    private NotebookTypeDao notebookTypeDao;
    @Transactional
    public List<Notebook> findSpecial(String userId){
        return notebookDao.findBySpecail(userId);
    }
    @Transactional
    public List<Notebook> findNormal(String userId){
        return notebookDao.findByNormal(userId);
    }
    @Transactional
    public void deleteNotebook(String id){
        notebookDao.delete(id);
    }

    @Transactional
    public Map<String,Object> addNotebook(String notebookName,String userId){
        Map<String,Object> result=new HashMap<>();
        if(notebookName==null||notebookName.trim().length()==0){
            result.put("success",false);
            result.put("name_null",true);
            return result;
        }
        Notebook nb=new Notebook();
        nb.setName(notebookName);
        nb.setUserId(userId);
        Notebook notebook = notebookDao.findByName(nb);
        if(notebook!=null){
            result.put("success",false);
            result.put("name_repeat",true);
            return result;
        }
        nb.setName(nb.getName().trim());
        nb.setId(UUID.randomUUID().toString());
        nb.setCreateTime(new Date());
        NotebookType notebookType = notebookTypeDao.findNormal();
        nb.setNotebookTypeId(notebookType.getId());
        notebookDao.add(nb);
        result.put("success",true);
        result.put("notebook",nb);
        return result;
    }
    @Transactional
    public Map<String,Object> updateNotebook(Notebook nb){
        Map<String,Object> result=new HashMap<>();
        if(nb.getName()==null||nb.getName().trim().length()==0){
            result.put("success",false);
            result.put("name_null",true);
            return result;
        }
        Notebook notebook = notebookDao.findByName(nb);
        if(notebook!=null){
            result.put("success",false);
            result.put("name_repeat",true);
            return result;
        }
        notebookDao.update(nb);
        result.put("success",true);
        return result;
    }
    @Transactional
    public void initSpecialNotbook(String userId){
        List<NotebookType> nbts = notebookTypeDao.findSpecail();
        for(NotebookType nbt:nbts){
            Notebook nb=new Notebook();
            nb.setName(nbt.gettDesc());
            nb.setNotebookTypeId(nbt.getId());
            nb.setCreateTime(new Date());
            nb.setId(UUID.randomUUID().toString());
            nb.setUserId(userId);
            notebookDao.add(nb);
        }
    }



}
