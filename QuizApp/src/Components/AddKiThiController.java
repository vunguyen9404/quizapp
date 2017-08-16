/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Admin.AdminHomeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import iaquizapp.CauHoi;
import iaquizapp.DeThi;
import iaquizapp.DotThi;
import iaquizapp.Lop;
import iaquizapp.MonHoc;
import iaquizapp.SinhVien;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class AddKiThiController implements Initializable {

    @FXML
    private VBox vbTaoKiThi;
    @FXML
    private JFXTextField tfTenKiThi;
    @FXML
    private JFXTextArea taGioiThieu;
    @FXML
    private JFXDatePicker tfNgayThi;
    @FXML
    private JFXButton btnTaoKiThi;
    @FXML
    private VBox vbThemLop;
    @FXML
    private JFXListView<Lop> lvLop;
    @FXML
    private JFXButton btnThemLop;
    @FXML
    private VBox vbThemMonHoc;
    @FXML
    private JFXListView<MonHoc> lvMonHoc;
    @FXML
    private JFXButton btnThemMonHoc;
    @FXML
    private VBox vbTaoDeThi;
    @FXML
    private JFXComboBox<Integer> cbSoDeThi;
    @FXML
    private JFXComboBox<Integer> cbSoCauHoi;
    @FXML
    private JFXToggleButton tgGuiMail;
    @FXML
    private JFXButton btnTaoDeThi;
    @FXML
    private JFXButton btnHuy;
    private static iaquizapp.IAQuizApp server;
    private String id;
    private List<Lop> listLopThi;
    private List<MonHoc> listMonThi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        server = AdminHomeController.getServer();
    }    
    
    private String getRdID() {
        String Ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(8);
        for( int i = 0; i < 16; i++ )
           sb.append( Ab.charAt( rnd.nextInt(Ab.length()) ) );
        return sb.toString();
    }
    
    private String getRdDeThiID(MonHoc monHoc, int index) {
        String mon = monHoc.getMonName().substring(0 ,1) + index;
        String Ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(8);
        for( int i = 0; i < 8; i++ )
           sb.append( Ab.charAt( rnd.nextInt(Ab.length()) ) );
        return mon + sb.toString();
    }
    
    private List<CauHoi> getRdCauHoi(MonHoc mon, int socau) {
        List<CauHoi> listResult = null;
        try {
            List<CauHoi> list = server.getListCauHoi(mon.getMonID());
            Collections.shuffle(list);
            listResult = list.stream().limit(socau).collect(Collectors.toList());
        } catch (RemoteException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listResult;
    }
    
    private void showUI(VBox vbox) {
        vbTaoDeThi.setVisible(false);
        vbTaoKiThi.setVisible(false);
        vbThemLop.setVisible(false);
        vbThemMonHoc.setVisible(false);
        vbox.setVisible(true);
    };
    
    private void setListLop() {
        try {
            List<Lop> list = server.getListLop();
            ObservableList<Lop> listItem = FXCollections.observableArrayList();
            listItem.addAll(list);
            lvLop.setItems(listItem);
            lvLop.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lvLop.setCellFactory(new Callback<ListView<Lop>, ListCell<Lop>>() {
                @Override
                public ListCell<Lop> call(ListView<Lop> param) {
                    return new JFXListCell<Lop>(){
                        @Override
                        public void updateItem(Lop item, boolean empty) {
                            super.updateItem(item, empty); 
                            if (item!= null) {
                                try {
                                    super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                                    URL urlImage = getClass().getResource("../Assets/School_50px.png");
                                    File file = new File(urlImage.toURI());
                                    ImageView graphic = new ImageView(new Image(file.toURI().toString()));
                                    graphic.setFitHeight(20);
                                    graphic.setFitWidth(20);
                                    
                                    
                                    Text name = new Text(item.getClassTen());
                                    name.setFont(new Font(16));
                                    name.setFill(Color.gray(0.31));
                                    
                                    HBox hbox = new HBox(graphic, name);
                                    hbox.setSpacing(20);
                                    HBox.setHgrow(name, Priority.ALWAYS);
                                    
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
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setListMon() {
        try {
            List<MonHoc> list = server.getListMonHoc();
            ObservableList<MonHoc> listItem = FXCollections.observableArrayList();
            listItem.addAll(list);
            lvMonHoc.setItems(listItem);
            lvMonHoc.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lvMonHoc.setCellFactory(new Callback<ListView<MonHoc>, ListCell<MonHoc>>() {
                @Override
                public ListCell<MonHoc> call(ListView<MonHoc> param) {
                    return new JFXListCell<MonHoc>(){
                        @Override
                        public void updateItem(MonHoc item, boolean empty) {
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
                                    
                                    HBox hbox = new HBox(graphic, name );
                                    hbox.setSpacing(20);
                                    HBox.setHgrow(name, Priority.ALWAYS);
                                    
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
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setListSoCau() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(10);
        list.add(15);
        ObservableList<Integer> itemList = FXCollections.observableArrayList();
        itemList.addAll(list);
        cbSoCauHoi.setItems(itemList);
    }
    
    private void setListSoDe() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(6);
        list.add(8);
        ObservableList<Integer> itemList = FXCollections.observableArrayList();
        itemList.addAll(list);
        cbSoDeThi.setItems(itemList);
    }
    
    private void sendEmail() {
        
    }
    
    @FXML
    private void clickedTaoKiThi(ActionEvent event) {
        try {
            LocalDate localDate = tfNgayThi.getValue();
            Date date = Date.valueOf(localDate);
            id = getRdID();
            DotThi thi = new DotThi(id,tfTenKiThi.getText(), date, taGioiThieu.getText());
            System.out.println(id);
            server.addDotThi(thi);
            setListLop();
            showUI(vbThemLop);
        } catch (RemoteException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedThemLop(ActionEvent event) {
        try {
            List<Lop> list = lvLop.getSelectionModel().getSelectedItems();
            listLopThi = list;
            for (Lop lop : listLopThi) {
                server.addDotThiLop(id, lop);
            }
            setListMon();
            showUI(vbThemMonHoc);
        } catch (RemoteException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedThemMonHoc(ActionEvent event) {
        try {
            List<MonHoc> list = lvMonHoc.getSelectionModel().getSelectedItems();
            listMonThi = list;
            for (MonHoc monHoc : listMonThi) {
                server.addDotThiMon(id, monHoc);
            }
            setListSoCau();
            setListSoDe();
            showUI(vbTaoDeThi);
        } catch (RemoteException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clicedTaoDeThi(ActionEvent event) {
        listMonThi.stream().forEach(x -> {
            StringBuilder email = new StringBuilder();
            for (int i = 0; i < cbSoDeThi.getSelectionModel().getSelectedItem(); i++) {
                try {
                    String dethiID = getRdDeThiID(x, i);
                    int socau = cbSoCauHoi.getSelectionModel().getSelectedItem();
                    StringBuilder dsCauHoi = new StringBuilder();
                    for (CauHoi cauhoi: getRdCauHoi(x, socau)) {
                        dsCauHoi.append(cauhoi.getCauhoiID() + ", ");
                    }
                    server.addDeThi(new DeThi(dethiID, socau, dsCauHoi.toString(), x.getMonID(), id));
                } catch (RemoteException ex) {
                    Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddKiThiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        sendEmail();
    }

    @FXML
    private void clickedClose(ActionEvent event) {
        System.out.println("Close");
    }
    
}
