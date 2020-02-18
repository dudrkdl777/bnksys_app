package com.bnk.example.bnkdata.Model;

public class DpsTypModel {
    private int id;
    private int pid;
    private String nm;
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
    public DpsTypModel(int id, int pid, String nm) {
        super();
        this.id = id;
        this.pid = pid;
        this.nm = nm;
    }
    public DpsTypModel() {
        super();
        // TODO Auto-generated constructor stub
    }
}
