package com.bnk.example.bnkdata.Model;

public class CltRgnModel {
    private int condst;
    private long collateral;
    private String dt;
    public CltRgnModel(int condst, long collateral, String dt) {
        super();
        this.condst = condst;
        this.collateral = collateral;
        this.dt = dt;
    }
    public CltRgnModel() {
        super();
        // TODO Auto-generated constructor stub
        condst = 0;
        collateral = 0;
        dt = null;
    }
    public int getCondst() {
        return condst;
    }
    public void setCondst(int condst) {
        this.condst = condst;
    }
    public long getCollateral() {
        return collateral;
    }
    public void setCollateral(long collateral) {
        this.collateral = collateral;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
}
