/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaquizapp;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author vunvd
 */
public class DotThi implements Serializable {
    private String dotthiID;
    private String dotthiTen;
    private Date dotthiNgay;
    private String dottthiDesc;
    private List<Lop> dotthiLop;
    private List<MonHoc> dotthiMon;
    private List<DeThi> dotthiDe;

    public DotThi() {
    }

    public DotThi(String dotthiTen, Date dotthiNgay, String dottthiDesc) {
        this.dotthiTen = dotthiTen;
        this.dotthiNgay = dotthiNgay;
        this.dottthiDesc = dottthiDesc;
    }

    public DotThi(String dotthiID, String dotthiTen, Date dotthiNgay, String dottthiDesc) {
        this.dotthiID = dotthiID;
        this.dotthiTen = dotthiTen;
        this.dotthiNgay = dotthiNgay;
        this.dottthiDesc = dottthiDesc;
    }

    public DotThi(String dotthiID, String dotthiTen, Date dotthiNgay, String dottthiDesc, List<Lop> dotthiLop, List<MonHoc> dotthiMon, List<DeThi> dotthiDe) {
        this.dotthiID = dotthiID;
        this.dotthiTen = dotthiTen;
        this.dotthiNgay = dotthiNgay;
        this.dottthiDesc = dottthiDesc;
        this.dotthiLop = dotthiLop;
        this.dotthiMon = dotthiMon;
        this.dotthiDe = dotthiDe;
    }
    
    public String getDotthiID() {
        return dotthiID;
    }

    public void setDotthiID(String dotthiID) {
        this.dotthiID = dotthiID;
    }

    public String getDotthiTen() {
        return dotthiTen;
    }

    public void setDotthiTen(String dotthiTen) {
        this.dotthiTen = dotthiTen;
    }

    public String getDottthiDesc() {
        return dottthiDesc;
    }

    public void setDottthiDesc(String dottthiDesc) {
        this.dottthiDesc = dottthiDesc;
    }

    public Date getDotthiNgay() {
        return dotthiNgay;
    }

    public void setDotthiNgay(Date dotthiNgay) {
        this.dotthiNgay = dotthiNgay;
    }

    public List<Lop> getDotthiLop() {
        return dotthiLop;
    }

    public void setDotthiLop(List<Lop> dotthiLop) {
        this.dotthiLop = dotthiLop;
    }

    public List<MonHoc> getDotthiMon() {
        return dotthiMon;
    }

    public void setDotthiMon(List<MonHoc> dotthiMon) {
        this.dotthiMon = dotthiMon;
    }

    public List<DeThi> getDotthiDe() {
        return dotthiDe;
    }

    public void setDotthiDe(List<DeThi> dotthiDe) {
        this.dotthiDe = dotthiDe;
    }
    
     
}
