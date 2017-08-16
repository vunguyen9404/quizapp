/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaquizapp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vunvd
 */
public interface IAQuizApp extends Remote {
    public int tinhtong(int a, int b) throws RemoteException, SQLException;
    
    public boolean isSvLogin(String svID, String svPass) throws RemoteException, SQLException;
    
    public boolean isAdminLogin(String admID, String admPass) throws RemoteException, SQLException;
    
    public List<Lop> getListLop() throws RemoteException, SQLException;
    public Lop getLop(int classID) throws RemoteException, SQLException;
    public void addLop(Lop lop) throws RemoteException, SQLException;
    public void deleteLop(int classID) throws RemoteException, SQLException;
    public void modifyLop(int classID, Lop lop) throws RemoteException, SQLException;
    public boolean isValidLop(String classTen) throws RemoteException, SQLException;
    
    public List<SinhVien> getListSinhVien(int classID) throws RemoteException, SQLException;
    public String getLastSinhVien() throws RemoteException, SQLException;
    public void addSinhVien(SinhVien sv) throws RemoteException, SQLException;
    public void deleteSinhVien(String svID) throws RemoteException, SQLException;
    public void modifySinhVien(String svID, SinhVien sv) throws RemoteException, SQLException;
    
    public List<MonHoc> getListMonHoc() throws RemoteException, SQLException;
    public void addMonHoc(MonHoc mon) throws RemoteException, SQLException;
    public void deleteMonHoc(int monID) throws RemoteException, SQLException;
    public void modifyMonHoc(int monID, MonHoc mon) throws RemoteException, SQLException;
    public boolean isValidMonHoc(String monName) throws RemoteException, SQLException;
    
    public List<CauHoi> getListCauHoi(int monID) throws RemoteException, SQLException;
    public void addCauHoi(CauHoi cauhoi) throws RemoteException, SQLException;
    public void deleteCauHoi(int cauhoiID) throws RemoteException, SQLException;
    public void modifyCauHoi(int cauhoiID, CauHoi cauhoi) throws RemoteException, SQLException;
    public boolean isValidCauHoi(String cauhoi) throws RemoteException, SQLException;
    
    public List<DeThi> getListDeThi(int monID, int dotthiID) throws RemoteException, SQLException;
    public void addDeThi(DeThi dethi) throws RemoteException, SQLException;
    
    public List<DotThi> getListDotThi() throws RemoteException, SQLException;
    public void addDotThi(DotThi thi) throws RemoteException, SQLException;
    public void modifyDotThi(int dotthiID, DotThi thi) throws RemoteException, SQLException;
    public void deleteDotThi(int dotthiID) throws RemoteException, SQLException;
    
    public List<Lop> getDotThiLop(String dotthiID) throws RemoteException, SQLException;
    public List<MonHoc> getDotThiMon(String dotthiID) throws RemoteException, SQLException;
    public void addDotThiLop(String dotthiID, Lop lop) throws RemoteException, SQLException;
    public void addDotThiMon(String dotthiID, MonHoc mon) throws RemoteException, SQLException;
}
