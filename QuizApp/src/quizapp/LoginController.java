/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import Admin.AdminHomeController;
import Thi.MaDeController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import iaquizapp.IAQuizApp;
import iaquizapp.SinhVien;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
import javafx.stage.Stage;

/**
 *
 * @author vunvd
 */
public class LoginController implements Initializable {
    private static iaquizapp.IAQuizApp server;
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXPasswordField tfPass;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            server = (IAQuizApp) Naming.lookup("rmi://localhost:3000/server_quizapp");
            String dotthiID = server.getDotThiID();
            Data.DataAccess.setDotthiID(dotthiID);
            Data.DataAccess.setListMonThi(server.getDotThiMon(dotthiID));
        } catch (NotBoundException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public static IAQuizApp getServer() {
        return server;
    }

    public static void setServer(IAQuizApp server) {
        LoginController.server = server;
    }

    @FXML
    private void clickedDangNhap(ActionEvent event) {
        try {
            String id = tfID.getText();
            String pass = tfPass.getText();
            Data.DataAccess.setSv(server.getSinhVien(id));
            if (server.isSvLogin(id, pass)) {
                Parent root = FXMLLoader.load(getClass().getResource("/Thi/MaDe.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
                MaDeController.setStage(stage);
                MaDeController.setIndexMon(0);
                stage.show();
                Login.getStage().close();
            } else {
                System.out.println("sai");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
