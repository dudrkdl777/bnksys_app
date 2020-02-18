package com.bnk.example.bnkdata.Model;

public class CrByAgeModel {
    private int condst;
    private int age;
    private float avgcr;
    private String dt;
    public CrByAgeModel() {
        super();
        // TODO Auto-generated constructor stub
        condst = 0;
        age = 0;
        avgcr = 0f;
        dt = null;
    }
    public CrByAgeModel(int condst, int age, float avgcr, String dt) {
        super();
        this.condst = condst;
        this.age = age;
        this.avgcr = avgcr;
        this.dt = dt;
    }
    public int getCondst() {
        return condst;
    }
    public void setCondst(int condst) {
        this.condst = condst;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public float getAvgcr() {
        return avgcr;
    }
    public void setAvgcr(float avgcr) {
        this.avgcr = avgcr;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
}
