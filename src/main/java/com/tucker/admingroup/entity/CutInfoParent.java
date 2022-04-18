package com.tucker.admingroup.entity;

import java.util.List;

public class CutInfoParent {
    private String cid;
    private List<CutInfo> mds;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<CutInfo> getMds() {
        return mds;
    }

    public void setMds(List<CutInfo> mds) {
        this.mds = mds;
    }

    public CutInfoParent(String cid, List<CutInfo> mds) {
        this.cid = cid;
        this.mds = mds;
    }

    public CutInfoParent() {
    }
}
