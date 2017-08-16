/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import iaquizapp.CauHoi;
import iaquizapp.DeThi;
import iaquizapp.DiemThi;
import iaquizapp.MonHoc;
import iaquizapp.SinhVien;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quizapp.LoginController;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class TrangThiController implements Initializable {

    @FXML
    private JFXRadioButton rdA;
    @FXML
    private JFXRadioButton rdC;
    @FXML
    private JFXRadioButton rdB;
    @FXML
    private JFXRadioButton rdD;
    ToggleGroup group;
    @FXML
    private StackPane rootPane;
    JFXDialog dialog;
    
    private static Stage stage;
    private SinhVien sv;
    private List<CauHoi> listCauHoi;
    private List<String> listDapAn;
    private static MonHoc monthi;
    private static String maDe;
    private int cauHienTai;
    private iaquizapp.IAQuizApp server;
    
    @FXML
    private Text txtSvTen;
    @FXML
    private Text txtSvID;
    @FXML
    private Text txtMonThi;
    @FXML
    private Text txtCauHienTai;
    @FXML
    private Text txtSoCau;
    @FXML
    private Text txtNoiDungCauHoi;
    @FXML
    private JFXButton btnTroLai;
    @FXML
    private JFXButton btnTiep;
    @FXML
    private Text txtMaDe;
    @FXML
    private ImageView imgNext;
    @FXML
    private ImageView imgPrev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                server = LoginController.getServer();
                sv = Data.DataAccess.getSv();
                txtSvTen.setText(sv.getSvHoTen());
                txtSvID.setText("Mã SV: " + sv.getSvID());

                txtMonThi.setText(monthi.getMonName());

                listCauHoi = getListCauHoi();
                listDapAn = getListDapAn();
                cauHienTai = 0;
                
                txtMaDe.setText(maDe);
                setCauHoi(cauHienTai);
                
                imgPrev.setVisible(false);
                btnTroLai.setVisible(false);
            }
        });
        
        group = new ToggleGroup();
        setToggleGroup();
    }    
    
    private List<CauHoi> getListCauHoi() {
        try {
            List<CauHoi> list = new ArrayList<>();
            DeThi de = server.getDeThi(maDe);
            txtSoCau.setText(de.getDethiSoCau() + "");
            String[] arrCauhoi = de.getDethiDSCauHoi().split(", ");
            ArrayList<String> listID = new ArrayList<>(Arrays.asList(arrCauhoi));
            listID.forEach(id -> {
                try {
                    list.add(server.getCauHoi(Integer.parseInt(id)));
                } catch (RemoteException ex) {
                    Logger.getLogger(TrangThiController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TrangThiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return list;
        } catch (RemoteException ex) {
            Logger.getLogger(TrangThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TrangThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void setCauHoi(int cauHienTai) {
        CauHoi cau = listCauHoi.get(cauHienTai);
        txtCauHienTai.setText(cauHienTai + 1 + "/");
        txtNoiDungCauHoi.setText(cau.getCauhoiNoiDung());
        rdA.setText(cau.getCauhoiDapAnA());
        rdB.setText(cau.getCauhoiDapAnB());
        rdC.setText(cau.getCauhoiDapAnC());
        rdD.setText(cau.getCauhoiDapAnD());
    }
    
    private void setToggleGroup() {
        rdA.setToggleGroup(group);
        rdB.setToggleGroup(group);
        rdC.setToggleGroup(group);
        rdD.setToggleGroup(group);
    }
    
    private void clearSelected(RadioButton rd) {
        rdA.setSelected(false);
        rdB.setSelected(false);
        rdC.setSelected(false);
        rdD.setSelected(false);
        if (rd != null) {
            rd.setSelected(true);
        }
    }
    
    private List<String> getListDapAn() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listCauHoi.size(); i++) {
            list.add("");
        }
        return list;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        TrangThiController.stage = stage;
    }

    public static MonHoc getMonthi() {
        return monthi;
    }

    public static void setMonthi(MonHoc monthi) {
        TrangThiController.monthi = monthi;
    }

    public static String getMaDe() {
        return maDe;
    }

    public static void setMaDe(String maDe) {
        TrangThiController.maDe = maDe;
    }
    

    @FXML
    private void clickedTroLai(ActionEvent event) {
        dapan();
        cauHienTai--;
        setCauHoi(cauHienTai);
        retrieveDapAn();
        if (cauHienTai == 0) {
            imgPrev.setVisible(false);
            btnTroLai.setVisible(false);
        } else {
            imgNext.setVisible(true);
            btnTiep.setText("Tiếp Theo");
        }
    }

    @FXML
    private void clickedTiep(ActionEvent event) {
        dapan();
        if (cauHienTai < listCauHoi.size()-1) {
            cauHienTai++;
            setCauHoi(cauHienTai);
            retrieveDapAn();
        } else {
            try {
                float diem = 10;
                float diemTru = diem/listCauHoi.size();
                
                for (int i = 0; i < listCauHoi.size(); i++) {
                    if (!listCauHoi.get(i).getCauhoiDapAnDung().equals(listDapAn.get(i))) {
                        diem = diem - diemTru;
                        System.out.println(listCauHoi.get(i).getCauhoiDapAnDung() + "-" + listDapAn.get(i));
                    }
                }
                server.addDiem(new DiemThi(Data.DataAccess.getDotthiID(), Data.DataAccess.getSv().getSvID(), getMonthi().getMonID(), diem));
                System.out.println("Diem:" + diem);
                Parent root = FXMLLoader.load(getClass().getResource("/Thi/ChuyenMon.fxml"));
                Stage stage = new Stage();
                ChuyenMonController.setMonHoc(monthi);
                ChuyenMonController.setStage(stage);
                Scene scene = new Scene(root);
                stage.setResizable(false);

                stage.setScene(scene);
                stage.show();
                getStage().close();
            } catch (RemoteException ex) {
                Logger.getLogger(TrangThiController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TrangThiController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TrangThiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cauHienTai == listCauHoi.size()-1) {
            imgNext.setVisible(false);
            btnTiep.setText("Kết Thúc");
        } else {
            imgPrev.setVisible(true);
            btnTroLai.setVisible(true);
        }
    }
    
    private void dapan() {
        if (rdA.isSelected()) {
            listDapAn.set(cauHienTai, "A");
        } else if (rdB.isSelected()) {
            listDapAn.set(cauHienTai, "B");
        } else if (rdC.isSelected()) {
            listDapAn.set(cauHienTai, "C");
        } else if (rdD.isSelected()) {
            listDapAn.set(cauHienTai, "D");
        } else {
           listDapAn.set(cauHienTai, "");
        }
    }
    
    private void retrieveDapAn() {
        String dapan = listDapAn.get(cauHienTai);
        switch (dapan) {
            case "A":
                clearSelected(rdA);
                break;
            case "B":
                clearSelected(rdB);
                break;
            case "C":
                clearSelected(rdC);
                break;
            case "D":
                clearSelected(rdD);
                break;
            default:
                clearSelected(null);
        }
    }
}
