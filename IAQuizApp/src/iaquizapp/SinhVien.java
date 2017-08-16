/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaquizapp;

import java.io.Serializable;

/**
 *
 * @author vunvd
 */
public class SinhVien implements Serializable {
    private String svID;
    private String svPass;
    private String svHoTen;
    private int svGioiTinh;
    private String svEmail;
    private String svSDT;
    private int classID;
    private String classTen;

    public SinhVien(String svID, String svPass, String svHoTen, int svGioiTinh, String svEmail, String svSDT, int classID) {
        this.svID = svID;
        this.svPass = svPass;
        this.svHoTen = svHoTen;
        this.svGioiTinh = svGioiTinh;
        this.svEmail = svEmail;
        this.svSDT = svSDT;
        this.classID = classID;
    }

    public SinhVien(String svID, String svPass, String svHoTen, int svGioiTinh, String svEmail, String svSDT, String classTen) {
        this.svID = svID;
        this.svPass = svPass;
        this.svHoTen = svHoTen;
        this.svGioiTinh = svGioiTinh;
        this.svEmail = svEmail;
        this.svSDT = svSDT;
        this.classTen = classTen;
    }
    
    public String getSvID() {
        return svID;
    }

    public void setSvID(String svID) {
        this.svID = svID;
    }

    public String getSvPass() {
        return svPass;
    }

    public void setSvPass(String svPass) {
        this.svPass = svPass;
    }

    public String getSvHoTen() {
        return svHoTen;
    }

    public void setSvHoTen(String svHoTen) {
        this.svHoTen = svHoTen;
    }

    public int getSvGioiTinh() {
        return svGioiTinh;
    }

    public void setSvGioiTinh(int svGioiTinh) {
        this.svGioiTinh = svGioiTinh;
    }

    public String getSvEmail() {
        return svEmail;
    }

    public void setSvEmail(String svEmail) {
        this.svEmail = svEmail;
    }

    public String getSvSDT() {
        return svSDT;
    }

    public void setSvSDT(String svSDT) {
        this.svSDT = svSDT;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassTen() {
        return classTen;
    }

    public void setClassTen(String classTen) {
        this.classTen = classTen;
    }
}
