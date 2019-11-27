package com.yxinmin.zs.dao;

import com.yxinmin.zs.entity.Note;

import java.util.List;

public interface NoteDao {
    void add(Note note);
    void update (Note note);
    void delete(String id);
    void deleteByNotebookId(String notrbookId);
    void move(Note note);
    List<Note> findByNotebookId(String id);
    Note findById(String id);
}
