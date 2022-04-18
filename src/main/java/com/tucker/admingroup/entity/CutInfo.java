package com.tucker.admingroup.entity;

public class CutInfo {
    private String id;
    private String src;
    private String now;
    private int level;

    public CutInfo() {
    }

    public CutInfo(String id, String src, String now, int level) {
        this.id = id;
        this.src = src;
        this.now = now;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
