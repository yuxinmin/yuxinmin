package com.yxinmin.zs.entity;

import java.util.Date;

public class Note {
    private String id;
    private String title;
    private String body;
    private Date modifyTime;
    private String notebookId;
    private int share;


    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId;
    }


    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", modifyTime=" + modifyTime +
                ", notebookId='" + notebookId + '\'' +
                '}';
    }
}
