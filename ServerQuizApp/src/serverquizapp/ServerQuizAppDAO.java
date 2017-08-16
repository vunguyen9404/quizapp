/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverquizapp;

import iaquizapp.CauHoi;
import iaquizapp.DeThi;
import iaquizapp.DiemThi;
import iaquizapp.DotThi;
import iaquizapp.Lop;
import iaquizapp.MonHoc;
import iaquizapp.SinhVien;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vunvd
 */
public class ServerQuizAppDAO extends UnicastRemoteObject implements iaquizapp.IAQuizApp {
    public static Connection conn = new ConnectSqlServer().getConnectDB();

    public ServerQuizAppDAO() throws RemoteException, SQLException{
    }
    
    
    @Override
    public boolean isSvLogin(String svID, String svPass) throws SQLException, RemoteException {
        boolean login = false;
        List<SinhVien> list = getListSinhVien();
        login = list.stream().anyMatch(x -> x.getSvID().equals(svID) && x.getSvPass().equals(svPass));
        return login;
    }

    @Override
    public boolean isAdminLogin(String admID, String admPass) {
        boolean login = false;
        
        return login;
    }

    @Override
    public List<Lop> getListLop() throws SQLException {
        String sql = "SELECT * FROM Lop";
        List<Lop> listResult = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {            
            listResult.add(new Lop(rs.getInt("classID"), rs.getString("classTen")));
        }
        return listResult;
    }

    @Override
    public List<SinhVien> getListSinhVien(int classID) throws SQLException {
        String sql = "SELECT * FROM SinhVien INNER JOIN Lop ON Lop.classID = SinhVien.classID WHERE SinhVien.classID = ?";
        List<SinhVien> list = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, classID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(new SinhVien(
                    rs.getString("svID"), 
                    rs.getString("svPass"),
                    rs.getString("svHoTen"), 
                    rs.getInt("svGioiTinh"), 
                    rs.getString("svEmail"), 
                    rs.getString("svSdt"), 
                    rs.getString("classTen")
            ));
        }
        rs.close();
        stm.close();
        return list;
    }

    @Override
    public List<MonHoc> getListMonHoc() throws SQLException {
        String sql = "SELECT * FROM MonHoc";
        List<MonHoc> listResult = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {            
            listResult.add(new MonHoc(rs.getInt("monID"), rs.getString("monName")));
        }
        rs.close();
        stm.close();
        return listResult;
    }

    @Override
    public List<CauHoi> getListCauHoi(int monID) throws SQLException {
        String sql = "SELECT * FROM CauHoi WHERE monID = ?";
        List<CauHoi> list = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, monID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {            
            list.add(new CauHoi(
                    rs.getInt("cauhoiID"),
                    rs.getString("cauhoiNoiDung"),
                    rs.getString("cauhoiDapAnA"),
                    rs.getString("cauhoiDapAnB"),
                    rs.getString("cauhoiDapAnC"),
                    rs.getString("cauhoiDapAnD"), 
                    rs.getString("cauhoiDapAnDung"),
                    rs.getInt("monID")
            ));
        }
        rs.close();
        stm.close();
        return list;
    }

    @Override
    public List<DotThi> getListDotThi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int tinhtong(int a, int b) throws RemoteException, SQLException {
        return a+b;
    }

    @Override
    public void addLop(Lop lop) throws RemoteException, SQLException {
        String sql = "INSERT INTO Lop (classTen) VALUES (?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, lop.getClassTen());
        stm.executeUpdate();
        stm.close();
        System.out.println("Them lop: " + lop.getClassTen());
    }

    @Override
    public void deleteLop(int classID) throws RemoteException, SQLException {
        String sql = "DELETE FROM Lop WHERE classID=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, classID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Xoa Lop: " + classID);
    }

    @Override
    public void modifyLop(int classID, Lop lop) throws RemoteException, SQLException {
        String sql = "UPDATE Lop SET classTen = ? WHERE classID=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, lop.getClassTen());
        stm.setInt(2, classID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Sua lop : " + lop.getClassTen());
    }

    @Override
    public boolean isValidLop(String classTen) throws RemoteException, SQLException {
        List<Lop> list = getListLop();
        boolean valid = false;
        valid = list.stream().anyMatch(x -> x.getClassTen().equals(classTen));
        return valid;
    }

    @Override
    public void addMonHoc(MonHoc mon) throws RemoteException, SQLException {
        String sql = "INSERT INTO MonHoc (monName) VALUES (?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, mon.getMonName());
        stm.executeUpdate();
        stm.close();
        System.out.println("Them Mon: " + mon.getMonName());
    }

    @Override
    public void deleteMonHoc(int monID) throws RemoteException, SQLException {
        String sql = "DELETE FROM MonHoc WHERE monID=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, monID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Xoa Mon: " + monID);
    }

    @Override
    public void modifyMonHoc(int monID, MonHoc mon) throws RemoteException, SQLException {
        String sql = "UPDATE MonHoc SET monName = ? WHERE monID=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, mon.getMonName());
        stm.setInt(2, monID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Sua Mon : " + mon.getMonName());
    }

    @Override
    public boolean isValidMonHoc(String monName) throws RemoteException, SQLException {
        List<MonHoc> list = getListMonHoc();
        boolean valid = false;
        valid = list.stream().anyMatch(x -> x.getMonName().equals(monName));
        return valid;
    }

    @Override
    public void addCauHoi(CauHoi cauhoi) throws RemoteException, SQLException {
        String sql = "INSERT INTO CauHoi (cauhoiNoiDung, cauhoiDapAnA, cauhoiDapAnB, cauhoiDapAnC, cauhoiDapAnD, cauhoiDapAnDung, monID) VALUES (?, ? , ? , ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, cauhoi.getCauhoiNoiDung());
        stm.setString(2, cauhoi.getCauhoiDapAnA());
        stm.setString(3, cauhoi.getCauhoiDapAnB());
        stm.setString(4, cauhoi.getCauhoiDapAnC());
        stm.setString(5, cauhoi.getCauhoiDapAnD());
        stm.setString(6, cauhoi.getCauhoiDapAnDung());
        stm.setInt(7, cauhoi.getMonID());
        stm.executeUpdate();
        stm.close();
        System.out.println("Them cau hoi");
    }

    @Override
    public void deleteCauHoi(int cauhoiID) throws RemoteException, SQLException {
       String sql = "DELETE FROM CauHoi WHERE cauhoiID=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, cauhoiID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Xoa Cau Hoi: " + cauhoiID);
    }

    @Override
    public void modifyCauHoi(int cauhoiID, CauHoi cauhoi) throws RemoteException, SQLException {
        String sql = "UPDATE CauHoi SET cauhoiNoiDung = ?, cauhoiDapAnA = ?, cauhoiDapAnB = ?, cauhoiDapAnC = ?, cauhoiDapAnD = ?, cauhoiDapAnDung = ?, monID = ? WHERE cauhoiID=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, cauhoi.getCauhoiNoiDung());
        stm.setString(2, cauhoi.getCauhoiDapAnA());
        stm.setString(3, cauhoi.getCauhoiDapAnB());
        stm.setString(4, cauhoi.getCauhoiDapAnC());
        stm.setString(5, cauhoi.getCauhoiDapAnD());
        stm.setString(6, cauhoi.getCauhoiDapAnDung());
        stm.setInt(7, cauhoi.getMonID());
        stm.setInt(8, cauhoiID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Da sua cau hoi");
    }

    @Override
    public boolean isValidCauHoi(String cauhoi) throws RemoteException, SQLException {
        return true;
    }

    @Override
    public String getLastSinhVien() throws RemoteException, SQLException {
        String sql = "SELECT TOP 1 svID FROM SinhVien ORDER BY svID DESC";
        String svID = null;
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            svID = rs.getString("svID");
        }
        rs.close();
        stm.close();
        return svID;
    }

    @Override
    public void addSinhVien(SinhVien sv) throws RemoteException, SQLException {
        String sql = "INSERT INTO SinhVien (svID, svPass, svHoTen, svGioiTinh, svEmail, svSdt, classID) VALUES (?, ?, ?, ? , ?, ? ,?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, sv.getSvID());
        stm.setString(2, sv.getSvPass());
        stm.setString(3, sv.getSvHoTen());
        stm.setInt(4, sv.getSvGioiTinh());
        stm.setString(5, sv.getSvEmail());
        stm.setString(6, sv.getSvSDT());
        stm.setInt(7, sv.getClassID());
        stm.executeUpdate();
        stm.close();
        System.out.println("Add sv: " + sv.getSvID());
    }

    @Override
    public void deleteSinhVien(String svID) throws RemoteException, SQLException {
        String sql = "DELETE FROM SinhVien WHERE svID=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, svID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Xoa Sv: " + svID);
    }

    @Override
    public void modifySinhVien(String svID, SinhVien sv) throws RemoteException, SQLException {
        String sql = "UPDATE SinhVien SET svHoTen = ?, svGioiTinh = ?, svEmail = ?, svSdt = ?, classID = ? WHERE svID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, sv.getSvHoTen());
        stm.setInt(2, sv.getSvGioiTinh());
        stm.setString(3, sv.getSvEmail());
        stm.setString(4, sv.getSvSDT());
        stm.setInt(5, sv.getClassID());
        stm.setString(6, svID);
        stm.executeUpdate();
        stm.close();
        System.out.println("Sua SV: " + svID);
    }

    @Override
    public Lop getLop(int classID) throws RemoteException, SQLException {
        String sql = "SELECT * FROM Lop WHERE classID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, classID);
        ResultSet rs = stm.executeQuery();
        Lop lop = null;
        while (rs.next()) {
            lop = new Lop(rs.getInt("classID"), rs.getString("classTen"));
        }
        rs.close();
        stm.close();
        return lop;
    }

    @Override
    public List<DeThi> getListDeThi(int monID, String dotthiID) throws RemoteException, SQLException {
        String sql = "SELECT * FROM DeThi WHERE dethiMon = ? AND dotthiID = ?";
        List<DeThi> list = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, monID);
        stm.setString(2, dotthiID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(new DeThi(
                    rs.getString("dethiID"),
                    rs.getInt("dethiSoCau"),
                    rs.getString("dethiDSCauHoi"),
                    rs.getInt("dethiMon"), 
                    rs.getString("dotthiID")
            ));
        }
        rs.close();
        stm.close();
        return list;
    }

    @Override
    public void addDeThi(DeThi dethi) throws RemoteException, SQLException {
        String sql = "INSERT INTO DeThi (dethiID, dethiSoCau, dethiDSCauHoi, dethiMon, dotthiID) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dethi.getDethiID());
        stm.setInt(2, dethi.getDethiSoCau());
        stm.setString(3, dethi.getDethiDSCauHoi());
        stm.setInt(4, dethi.getDethiMon());
        stm.setString(5, dethi.getDotthiID());
        stm.executeUpdate();
        stm.close();
    }

    @Override
    public void addDotThi(DotThi thi) throws RemoteException, SQLException {
        String sql = "INSERT INTO DotThi (dotthiID, dotthiTen, dotthiNgay, dotthiDesc) VALUES (?,?,?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, thi.getDotthiID());
        stm.setString(2, thi.getDotthiTen());
        stm.setDate(3, thi.getDotthiNgay());
        stm.setString(4, thi.getDottthiDesc());
        stm.executeUpdate();
        stm.close();
    }

    @Override
    public void modifyDotThi(int dotthiID, DotThi thi) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDotThi(int dotthiID) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Lop> getDotThiLop(String dotthiID) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MonHoc> getDotThiMon(String dotthiID) throws RemoteException, SQLException {
        String sql = "SELECT * FROM dbo.DotThi FULL JOIN dbo.DotThiMon ON DotThiMon.dotthiID = DotThi.dotthiID INNER JOIN dbo.MonHoc ON MonHoc.monID = DotThiMon.monID WHERE DotThi.dotthiID = ?";
        List<MonHoc> list = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dotthiID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {            
            list.add(new MonHoc(rs.getInt("monID"), rs.getString("monName")));
        }
        rs.close();;
        stm.close();
        return list;
    }

    @Override
    public void addDotThiLop(String dotthiID, Lop lop) throws RemoteException, SQLException {
        String sql = "INSERT INTO DotThiLop (dotthiID, classID) VALUES (?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dotthiID);
        stm.setInt(2, lop.getClassID());
        stm.executeUpdate();
        stm.close();
    }

    @Override
    public void addDotThiMon(String dotthiID, MonHoc mon) throws RemoteException, SQLException {
        String sql = "INSERT INTO DotThiMon (dotthiID, monID) VALUES (?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dotthiID);
        stm.setInt(2, mon.getMonID());
        stm.executeUpdate();
        stm.close();
    }

    @Override
    public List<SinhVien> getListSinhVien() throws RemoteException, SQLException {
        String sql = "SELECT * FROM SinhVien";
        List<SinhVien> list = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(new SinhVien(
                    rs.getString("svID"), 
                    rs.getString("svPass"),
                    rs.getString("svHoTen"), 
                    rs.getInt("svGioiTinh"), 
                    rs.getString("svEmail"), 
                    rs.getString("svSdt"),
                    rs.getInt("classID")
            ));
        }
        rs.close();
        stm.close();
        return list;
    }

    @Override
    public SinhVien getSinhVien(String svID) throws RemoteException, SQLException {
        String sql = "SELECT * FROM SinhVien INNER JOIN Lop ON Lop.classID = SinhVien.classID WHERE SinhVien.svID = ?";
        SinhVien sv = null;
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, svID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            sv = new SinhVien(
                    rs.getString("svID"), 
                    rs.getString("svPass"),
                    rs.getString("svHoTen"), 
                    rs.getInt("svGioiTinh"), 
                    rs.getString("svEmail"), 
                    rs.getString("svSdt"), 
                    rs.getString("classTen")
            );
        }
        return sv;
    }

    @Override
    public String getDotThiID() throws RemoteException, SQLException {
        return "kd24fS8B27XLfmya";
    }

    @Override
    public DeThi getDeThi(String dethiID) throws RemoteException, SQLException {
        String sql = "SELECT * FROM DeThi WHERE dethiID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dethiID);
        ResultSet rs = stm.executeQuery();
        DeThi dethi = null;
        while (rs.next()) {
            dethi = new DeThi(rs.getString("dethiID"), rs.getInt("dethiSoCau"), rs.getString("dethiDSCauHoi"), rs.getInt("dethiMon"), rs.getString("dotthiID"));
        }
        rs.close();
        stm.close();
        return dethi;
    }

    @Override
    public CauHoi getCauHoi(int cauhoiID) throws RemoteException, SQLException {
        String sql = "SELECT * FROM CauHoi WHERE cauhoiID = ?";
        CauHoi cauhoi = null;
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, cauhoiID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {            
            cauhoi = new CauHoi(
                    rs.getInt("cauhoiID"),
                    rs.getString("cauhoiNoiDung"),
                    rs.getString("cauhoiDapAnA"),
                    rs.getString("cauhoiDapAnB"),
                    rs.getString("cauhoiDapAnC"),
                    rs.getString("cauhoiDapAnD"), 
                    rs.getString("cauhoiDapAnDung"),
                    rs.getInt("monID")
            );
        }
        rs.close();
        stm.close();
        return cauhoi;
    }

    @Override
    public void addDiem(DiemThi diem) throws RemoteException, SQLException {
        String sql = "INSERT INTO Diem (dotthiID, svID, monID, diemSo) VALUES (?,?,?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, diem.getDotthiID());
        stm.setString(2, diem.getSvID());
        stm.setInt(3, diem.getMonID());
        stm.setFloat(4, diem.getDiemSo());
        stm.executeUpdate();
        stm.close();
    }

    @Override
    public List<DiemThi> getDiemThi(String svID, String dotthiID) throws RemoteException, SQLException {
        List<DiemThi> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Diem FULL JOIN dbo.MonHoc ON MonHoc.monID = Diem.monID WHERE dotthiID = ? AND svID = ?;";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dotthiID);
        stm.setString(2, svID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {            
            list.add(new DiemThi(rs.getString("dotthiID"), rs.getString("svID"), rs.getInt("monID"), rs.getFloat("diemSo"), rs.getString("monName")));
        }
        rs.close();
        stm.close();
        return list;
    }
}
