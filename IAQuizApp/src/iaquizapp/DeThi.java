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
public class DeThi implements Serializable {
    private String dethiID;
    private int dethiSoCau;
    private String dethiDSCauHoi;
    private int dethiMon;
    private String dotthiID;

    public DeThi() {
    }

    public DeThi(String dethiID, int dethiSoCau, String dethiDSCauHoi, int dethiMon, String dotthiID) {
        this.dethiID = dethiID;
        this.dethiSoCau = dethiSoCau;
        this.dethiDSCauHoi = dethiDSCauHoi;
        this.dethiMon = dethiMon;
        this.dotthiID = dotthiID;
    }
    
    public String getDotthiID() {
        return dotthiID;
    }

    public void setDotthiID(String dotthiID) {
        this.dotthiID = dotthiID;
    }

    

    public String getDethiID() {
        return dethiID;
    }

    public void setDethiID(String dethiID) {
        this.dethiID = dethiID;
    }

    public int getDethiSoCau() {
        return dethiSoCau;
    }

    public void setDethiSoCau(int dethiSoCau) {
        this.dethiSoCau = dethiSoCau;
    }

    public String getDethiDSCauHoi() {
        return dethiDSCauHoi;
    }

    public void setDethiDSCauHoi(String dethiDSCauHoi) {
        this.dethiDSCauHoi = dethiDSCauHoi;
    }

    public int getDethiMon() {
        return dethiMon;
    }

    public void setDethiMon(int dethiMon) {
        this.dethiMon = dethiMon;
    }
}
