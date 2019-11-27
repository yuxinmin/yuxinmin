package com.yxinmin.zs.service;

import com.yxinmin.zs.dao.NoteDao;
import com.yxinmin.zs.dao.ShareDao;
import com.yxinmin.zs.entity.Note;
import com.yxinmin.zs.entity.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ShareService {
    @Autowired
    private ShareDao shareDao;
    @Autowired
    private NoteDao noteDao;

    @Transactional
    public boolean addShare(String noteId,String userName){
        Note note = noteDao.findById(noteId);
        if(note.getBody()==null||note.getBody().trim().length()==0){
            return false;
        }
        note.setShare(1);
        noteDao.update(note);
        Share share=new Share();
        share.setId(UUID.randomUUID().toString());
        share.setTitle(note.getTitle()+"---由"+userName+"分享");
        share.setBody(note.getBody());
        shareDao.add(share);
        return true;
    }
    @Transactional
    public List<Share> shareList(String title){
        return shareDao.findLikeTitle(title);
    }

}
