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
public class DiemThi implements Serializable {
    private String dotthiID;
    private String svID;
    private int monID;
    private float diemSo;
    private String monName;

    public DiemThi() {
    }

    public DiemThi(String dotthiID, String svID, int monID, float diemSo) {
        this.dotthiID = dotthiID;
        this.svID = svID;
        this.monID = monID;
        this.diemSo = diemSo;
    }

    public DiemThi(String dotthiID, String svID, int monID, float diemSo, String monName) {
        this.dotthiID = dotthiID;
        this.svID = svID;
        this.monID = monID;
        this.diemSo = diemSo;
        this.monName = monName;
    }

    public String getDotthiID() {
        return dotthiID;
    }

    public void setDotthiID(String dotthiID) {
        this.dotthiID = dotthiID;
    }

    public String getSvID() {
        return svID;
    }

    public void setSvID(String svID) {
        this.svID = svID;
    }

    public int getMonID() {
        return monID;
    }

    public void setMonID(int monID) {
        this.monID = monID;
    }

    public float getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(float diemSo) {
        this.diemSo = diemSo;
    }

    public String getMonName() {
        return monName;
    }

    public void setMonName(String monName) {
        this.monName = monName;
    }
    
}
