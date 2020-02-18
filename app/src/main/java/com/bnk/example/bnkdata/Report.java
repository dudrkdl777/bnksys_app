package com.bnk.example.bnkdata;

public class Report {
    int rno;
    String ename;
    String dept;
    String title;
    String content;

    public Report(int rno, String ename, String dept, String title, String content) {
        this.rno = rno;
        this.ename = ename;
        this.dept = dept;
        this.title = title;
        this.content = content;
    }

    public Report(String ename, String dept, String title, String content) {
        this.ename = ename;
        this.dept = dept;
        this.title = title;
        this.content = content;
    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Report{" +
                "rno=" + rno +
                ", ename='" + ename + '\'' +
                ", dept='" + dept + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
