package com.bnk.example.bnkdata.Model;

public class CrdStrModel {
    private int sector;
    private long volume;
    private String dt;
    public int getSector() {
        return sector;
    }
    public void setSector(int sector) {
        this.sector = sector;
    }
    public CrdStrModel() {
        super();
        // TODO Auto-generated constructor stub
        sector = 0;
        volume = 0;
        dt = null;
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(long volume) {
        this.volume = volume;
    }
    public CrdStrModel(int sector, long volume, String dt) {
        super();
        this.sector = sector;
        this.volume = volume;
        this.dt = dt;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
}
