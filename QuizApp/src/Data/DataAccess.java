/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import iaquizapp.MonHoc;
import iaquizapp.SinhVien;
import java.util.List;

/**
 *
 * @author vunvd
 */
public class DataAccess {
    private static SinhVien sv;
    private static String dotthiID;
    private static String maDeThi;
    private static List<MonHoc> listMonThi;

    public DataAccess() {
    }

    public static String getDotthiID() {
        return dotthiID;
    }

    public static void setDotthiID(String dotthiID) {
        DataAccess.dotthiID = dotthiID;
    }

    
    public static SinhVien getSv() {
        return sv;
    }

    public static void setSv(SinhVien sv) {
        DataAccess.sv = sv;
    }

    public static List<MonHoc> getListMonThi() {
        return listMonThi;
    }

    public static void setListMonThi(List<MonHoc> listMonThi) {
        DataAccess.listMonThi = listMonThi;
    }

    public static String getMaDeThi() {
        return maDeThi;
    }

    public static void setMaDeThi(String maDeThi) {
        DataAccess.maDeThi = maDeThi;
    }
    
}
