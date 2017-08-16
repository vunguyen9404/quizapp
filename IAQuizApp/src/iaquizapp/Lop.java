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
public class Lop  implements Serializable {
    private int classID;
    private String classTen;

    public Lop() {
    }
    
    public Lop(String classTen) {
        this.classTen = classTen;
    }

    public Lop(int classID, String classTen) {
        this.classID = classID;
        this.classTen = classTen;
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
