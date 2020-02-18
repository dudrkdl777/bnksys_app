package com.bnk.example.bnkdata.Model;

public class DepositModel {
    private int dpstyp;
    private double amount;
    private String dt;
    public int getDpstyp() {
        return dpstyp;
    }
    public void setDpstyp(int dpstyp) {
        this.dpstyp = dpstyp;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
    public DepositModel(int dpstyp, double amount, String dt) {
        super();
        this.dpstyp = dpstyp;
        this.amount = amount;
        this.dt = dt;
    }
    public DepositModel() {
        super();
        // TODO Auto-generated constructor stub
    }
}
