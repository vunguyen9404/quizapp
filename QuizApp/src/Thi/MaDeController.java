/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import iaquizapp.DeThi;
import iaquizapp.MonHoc;
import iaquizapp.SinhVien;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quizapp.Login;
import quizapp.LoginController;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class MaDeController implements Initializable {

    private static SinhVien sv;
    private static Stage stage;
    private static int indexMon;
    private iaquizapp.IAQuizApp server;
    @FXML
    private JFXTextField tfMaDe;
    @FXML
    private JFXButton btnBatDauThi;
    @FXML
    private Text txtMonThi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        server = LoginController.getServer();
        MonHoc mon = Data.DataAccess.getListMonThi().get(indexMon);
        txtMonThi.setText(mon.getMonName());
    }
    
    public static void tangIndexMon() {
        MaDeController.indexMon++;
    }
    
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MaDeController.stage = stage;
    }
    
    public static SinhVien getSv() {
        return sv;
    }

    public static void setSv(SinhVien sv) {
        MaDeController.sv = sv;
    }

    public static int getIndexMon() {
        return indexMon;
    }

    public static void setIndexMon(int indexMon) {
        MaDeController.indexMon = indexMon;
    }
    
    @FXML
    private void clickedThi(ActionEvent event) {
        if (tfMaDe.getText() == null) {
            System.out.println("Sai ma De");
        } else {
            try {
                String dotthiID = Data.DataAccess.getDotthiID();
                MonHoc mon = Data.DataAccess.getListMonThi().get(indexMon);
                List<DeThi> dethi = server.getListDeThi(mon.getMonID(), dotthiID);
                if (dethi.stream().anyMatch(x -> x.getDethiID().equals(tfMaDe.getText()))) {
                    TrangThiController.setMaDe(tfMaDe.getText());
                    Parent root = FXMLLoader.load(getClass().getResource("/Thi/TrangThi.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    TrangThiController.setStage(stage);
                    TrangThiController.setMonthi(mon);
                    stage.show();
                    getStage().close();
                } else {
                    System.out.println("Sai");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(MaDeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MaDeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MaDeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
