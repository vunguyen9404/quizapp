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
public class CauHoi implements Serializable {
    private int cauhoiID;
    private String cauhoiNoiDung;
    private String cauhoiDapAnA;
    private String cauhoiDapAnB;
    private String cauhoiDapAnC;
    private String cauhoiDapAnD;
    private String cauhoiDapAnDung;
    private int monID;

    public CauHoi() {
    }

    public CauHoi(String cauhoiNoiDung, String cauhoiDapAnA, String cauhoiDapAnB, String cauhoiDapAnC, String cauhoiDapAnD, String cauhoiDapAnDung, int monID) {
        this.cauhoiNoiDung = cauhoiNoiDung;
        this.cauhoiDapAnA = cauhoiDapAnA;
        this.cauhoiDapAnB = cauhoiDapAnB;
        this.cauhoiDapAnC = cauhoiDapAnC;
        this.cauhoiDapAnD = cauhoiDapAnD;
        this.cauhoiDapAnDung = cauhoiDapAnDung;
        this.monID = monID;
    }

    public CauHoi(int cauhoiID, String cauhoiNoiDung, String cauhoiDapAnA, String cauhoiDapAnB, String cauhoiDapAnC, String cauhoiDapAnD, String cauhoiDapAnDung, int monID) {
        this.cauhoiID = cauhoiID;
        this.cauhoiNoiDung = cauhoiNoiDung;
        this.cauhoiDapAnA = cauhoiDapAnA;
        this.cauhoiDapAnB = cauhoiDapAnB;
        this.cauhoiDapAnC = cauhoiDapAnC;
        this.cauhoiDapAnD = cauhoiDapAnD;
        this.cauhoiDapAnDung = cauhoiDapAnDung;
        this.monID = monID;
    }

    public int getCauhoiID() {
        return cauhoiID;
    }

    public void setCauhoiID(int cauhoiID) {
        this.cauhoiID = cauhoiID;
    }

    public String getCauhoiNoiDung() {
        return cauhoiNoiDung;
    }

    public void setCauhoiNoiDung(String cauhoiNoiDung) {
        this.cauhoiNoiDung = cauhoiNoiDung;
    }

    public String getCauhoiDapAnA() {
        return cauhoiDapAnA;
    }

    public void setCauhoiDapAnA(String cauhoiDapAnA) {
        this.cauhoiDapAnA = cauhoiDapAnA;
    }

    public String getCauhoiDapAnB() {
        return cauhoiDapAnB;
    }

    public void setCauhoiDapAnB(String cauhoiDapAnB) {
        this.cauhoiDapAnB = cauhoiDapAnB;
    }

    public String getCauhoiDapAnC() {
        return cauhoiDapAnC;
    }

    public void setCauhoiDapAnC(String cauhoiDapAnC) {
        this.cauhoiDapAnC = cauhoiDapAnC;
    }

    public String getCauhoiDapAnD() {
        return cauhoiDapAnD;
    }

    public void setCauhoiDapAnD(String cauhoiDapAnD) {
        this.cauhoiDapAnD = cauhoiDapAnD;
    }

    public String getCauhoiDapAnDung() {
        return cauhoiDapAnDung;
    }

    public void setCauhoiDapAnDung(String cauhoiDapAnDung) {
        this.cauhoiDapAnDung = cauhoiDapAnDung;
    }

    public int getMonID() {
        return monID;
    }

    public void setMonID(int monID) {
        this.monID = monID;
    }
    
    
}
