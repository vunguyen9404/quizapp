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
public class MonHoc implements Serializable {
    private int monID;
    private String monName;

    public MonHoc() {
    }
    
    public MonHoc(String monName) {
        this.monName = monName;
    }

    public MonHoc(int monID, String monName) {
        this.monID = monID;
        this.monName = monName;
    }
    
    public int getMonID() {
        return monID;
    }

    public void setMonID(int monID) {
        this.monID = monID;
    }

    public String getMonName() {
        return monName;
    }

    public void setMonName(String monName) {
        this.monName = monName;
    }
    
    
}
