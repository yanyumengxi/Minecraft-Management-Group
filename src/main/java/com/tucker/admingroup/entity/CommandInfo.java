package com.tucker.admingroup.entity;

public class CommandInfo {
    private String srcName;
    private String findName;
    private String id;
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommandInfo() {
    }

    public CommandInfo(String srcName, String findName, String id, int level) {
        this.srcName = srcName;
        this.findName = findName;
        this.id = id;
        this.level = level;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getFindName() {
        return findName;
    }

    public void setFindName(String findName) {
        this.findName = findName;
    }
}
