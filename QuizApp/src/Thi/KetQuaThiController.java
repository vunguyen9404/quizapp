/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thi;

import Admin.AdminHomeController;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import iaquizapp.DiemThi;
import iaquizapp.MonHoc;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import quizapp.LoginController;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class KetQuaThiController implements Initializable {

    @FXML
    private Text txtSvTen;
    @FXML
    private Text txtSvID;
    @FXML
    private JFXListView<DiemThi> lvDiemThi;
    private iaquizapp.IAQuizApp server;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        server = LoginController.getServer();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtSvTen.setText(Data.DataAccess.getSv().getSvHoTen());
                txtSvID.setText(Data.DataAccess.getSv().getSvID());
                setListDiem();
            }
        });
    }
    
    private void setListDiem() {
        try {
            List<DiemThi> list = server.getDiemThi(Data.DataAccess.getSv().getSvID(), Data.DataAccess.getDotthiID());
            ObservableList<DiemThi> listItem = FXCollections.observableArrayList();
            listItem.addAll(list);
            lvDiemThi.setItems(listItem);
            lvDiemThi.setCellFactory(new Callback<ListView<DiemThi>, ListCell<DiemThi>>() {
                @Override
                public ListCell<DiemThi> call(ListView<DiemThi> param) {
                    return new JFXListCell<DiemThi>(){
                        @Override
                        public void updateItem(DiemThi item, boolean empty) {
                            super.updateItem(item, empty); 
                            if (item!= null) {
                                try {
                                    URL urlImage = getClass().getResource("../Assets/Book2_50px.png");
                                    File file = new File(urlImage.toURI());
                                    ImageView graphic = new ImageView(new Image(file.toURI().toString()));
                                    graphic.setFitHeight(20);
                                    graphic.setFitWidth(20);
                                    
                                    
                                    
                                    Text name = new Text(item.getMonName());
                                    name.setFont(new Font(16));
                                    name.setFill(Color.gray(0.31));
                                    
                                    Text diem = new Text(item.getDiemSo() + "");
                                    diem.setFont(new Font(16));
                                    diem.setFill(Color.gray(0.31));
                                    Pane pn = new Pane();
                                    
                                    HBox hbox = new HBox(graphic, name, pn, diem);
                                    hbox.setSpacing(20);
                                    HBox.setHgrow(pn, Priority.ALWAYS);
                                    
                                    setGraphic(hbox);
                                } catch (URISyntaxException ex) {
                                    Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    };
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(KetQuaThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KetQuaThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
