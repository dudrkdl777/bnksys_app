package com.bnk.example.bnkdata.Model;

public class CrByRgnModel {
    private int condst;
    private int ratesg;
    private int rateg;
    private int ratebd;
    private int rategbd;
    private String dt;
    public CrByRgnModel() {
        super();
        // TODO Auto-generated constructor stub
        condst = 0;
        ratesg = 0;
        rateg = 0;
        ratebd = 0;
        rategbd = 0;
        dt = null;
    }
    public CrByRgnModel(int condst, int ratesg, int rateg, int ratebd, int rategbd, String dt) {
        super();
        this.condst = condst;
        this.ratesg = ratesg;
        this.rateg = rateg;
        this.ratebd = ratebd;
        this.rategbd = rategbd;
        this.dt = dt;
    }
    public int getCondst() {
        return condst;
    }
    public void setCondst(int condst) {
        this.condst = condst;
    }
    public int getRatesg() {
        return ratesg;
    }
    public void setRatesg(int ratesg) {
        this.ratesg = ratesg;
    }
    public int getRateg() {
        return rateg;
    }
    public void setRateg(int rateg) {
        this.rateg = rateg;
    }
    public int getRatebd() {
        return ratebd;
    }
    public void setRatebd(int ratebd) {
        this.ratebd = ratebd;
    }
    public int getRategbd() {
        return rategbd;
    }
    public void setRategbd(int rategbd) {
        this.rategbd = rategbd;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
}
