/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thi;

import com.jfoenix.controls.JFXButton;
import iaquizapp.MonHoc;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class ChuyenMonController implements Initializable {

    private static MonHoc monHoc;
    private static Stage stage;
    @FXML
    private Text txtMonThi;
    @FXML
    private Text txtThongBao;
    @FXML
    private JFXButton btnThiMonTiep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (MaDeController.getIndexMon() == Data.DataAccess.getListMonThi().size()-1) {
                    txtMonThi.setText("");
                    txtThongBao.setText("Chúc mừng Bạn đã hoàn thành xong kì thi !");
                    btnThiMonTiep.setText("Xem Kết Quả Thi");
                } else {
                    txtMonThi.setText(monHoc.getMonName());
                }
            }
        });
    }    

    @FXML
    private void clickedThiMonTiep(ActionEvent event) {
        if (MaDeController.getIndexMon() == Data.DataAccess.getListMonThi().size()-1) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Thi/KetQuaThi.fxml"));
                Stage stage = new Stage();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                    }
                });
                Scene scene = new Scene(root);
                stage.setResizable(false);

                stage.setScene(scene);
                stage.show();
                getStage().close();
            } catch (IOException ex) {
                Logger.getLogger(ChuyenMonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                MaDeController.tangIndexMon();
                Parent root = FXMLLoader.load(getClass().getResource("/Thi/MaDe.fxml"));
                Stage stage = new Stage();
                MaDeController.setStage(stage);
                Scene scene = new Scene(root);
                stage.setResizable(false);

                stage.setScene(scene);
                stage.show();
                getStage().close();
            } catch (IOException ex) {
                Logger.getLogger(ChuyenMonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static MonHoc getMonHoc() {
        return monHoc;
    }

    public static void setMonHoc(MonHoc monHoc) {
        ChuyenMonController.monHoc = monHoc;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ChuyenMonController.stage = stage;
    }
}
