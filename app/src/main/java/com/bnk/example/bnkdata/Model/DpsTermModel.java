package com.bnk.example.bnkdata.Model;

public class DpsTermModel {
    private String term;
    private double volume;
    private String dt;
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public double getVolume() {
        return volume;
    }
    public DpsTermModel(String term, double volume, String dt) {
        super();
        this.term = term;
        this.volume = volume;
        this.dt = dt;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
    public DpsTermModel() {
        super();
        // TODO Auto-generated constructor stub
    }
}
