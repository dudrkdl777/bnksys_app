package com.bnk.example.bnkdata.Model;

public class CondstModel {
    private int id;
    private String nm;
    public CondstModel() {
        super();
        // TODO Auto-generated constructor stub
        id = 0;
        nm = null;
    }
    public CondstModel(int id, String nm) {
        super();
        this.id = id;
        this.nm = nm;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNm() {
        return nm;
    }
    public void setNm(String nm) {
        this.nm = nm;
    }
}
