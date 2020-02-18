package com.bnk.example.bnkdata.Model;

public class SectorModel {
    private int id;
    private int pid;
    private String nm;
    public SectorModel(int id, int pid, String nm) {
        super();
        this.id = id;
        this.pid = pid;
        this.nm = nm;
    }
    public SectorModel() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getNm() {
        return nm;
    }
    public void setNm(String nm) {
        this.nm = nm;
    }
}
