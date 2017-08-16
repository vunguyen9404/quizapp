/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Admin.AdminHomeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXTextField;
import iaquizapp.Lop;
import iaquizapp.MonHoc;
import iaquizapp.SinhVien;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class AddSinhVienController implements Initializable {

    @FXML
    private JFXTextField tfSvHoTen;
    @FXML
    private JFXTextField tfSvEmail;
    @FXML
    private JFXTextField tfSvSdt;
    @FXML
    private JFXComboBox<GioiTinh> cbSvGioiTinh;
    @FXML
    private JFXComboBox<Lop> cbLopHoc;
    @FXML
    private JFXButton btnThemSV;
    @FXML
    private JFXButton btnHuy;
    
    private static Stage stage;
    private iaquizapp.IAQuizApp server;
    private static boolean editSinhVien;
    private static SinhVien sv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        server = AdminHomeController.getServer();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setComboBoxLop();
                setComboBoxGioiTinh();
            }
        });
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (editSinhVien) {
                    btnThemSV.setText("Cập Nhật");
                    tfSvHoTen.setText(getSv().getSvHoTen());
                    tfSvEmail.setText(getSv().getSvEmail());
                    tfSvSdt.setText(getSv().getSvSDT());
                    cbLopHoc.getItems().stream().forEach(x -> {
                        if (x.getClassID() == getSv().getClassID()) {
                            cbLopHoc.getSelectionModel().select(x);
                        }
                    });
                    cbSvGioiTinh.getItems().stream().forEach(x -> {
                        if (x.getId() == sv.getSvGioiTinh()) {
                            cbSvGioiTinh.getSelectionModel().select(x);
                        }
                    });
                }
            }
        });
    }    
    
    private void setComboBoxGioiTinh() {
        List<GioiTinh> list = new ArrayList<>();
        list.add(new GioiTinh(0, "Nữ"));
        list.add(new GioiTinh(1, "Nam"));
        ObservableList<GioiTinh> itemList = FXCollections.observableArrayList();
        itemList.addAll(list);
        cbSvGioiTinh.setItems(itemList);
        cbSvGioiTinh.setCellFactory(new Callback<ListView<GioiTinh>, ListCell<GioiTinh>>() {
                @Override
                public ListCell<GioiTinh> call(ListView<GioiTinh> param) {
                    return new JFXListCell<GioiTinh>(){
                        @Override
                        public void updateItem(GioiTinh item, boolean empty) {
                            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                            if (item != null) {
                                Text text = new Text(item.getName());
                                getStyleClass().add("list-cell--gray");
                                setGraphic(text);
                            }
                        }
                    };
                }
            });
            
            cbSvGioiTinh.setConverter(new StringConverter<GioiTinh>() {
                @Override
                public String toString(GioiTinh object) {
                    if (object != null) {
                        return object.getName();
                    }
                    return null;
                }

                @Override
                public GioiTinh fromString(String string) {
                    return null;
                }
            });
    }
    
    private void setComboBoxLop() {
        try {
            List<Lop> list = server.getListLop();
            ObservableList<Lop> itemlist = FXCollections.observableArrayList();
            itemlist.addAll(list);
            cbLopHoc.setItems(itemlist);
            cbLopHoc.setCellFactory(new Callback<ListView<Lop>, ListCell<Lop>>() {
                @Override
                public ListCell<Lop> call(ListView<Lop> param) {
                    return new JFXListCell<Lop>(){
                        @Override
                        public void updateItem(Lop item, boolean empty) {
                            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                            if (item != null) {
                                Text text = new Text(item.getClassTen());
                                getStyleClass().add("list-cell--gray");
                                setGraphic(text);
                            }
                        }
                    };
                }
            });
            
            cbLopHoc.setConverter(new StringConverter<Lop>() {
                @Override
                public String toString(Lop object) {
                    if (object != null) {
                        return object.getClassTen();
                    }
                    return null;
                }

                @Override
                public Lop fromString(String string) {
                    return null;
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(AddSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getAutoSvID() {
        String newID = null;
        try {
            String svID = server.getLastSinhVien();
            int numberID;
            if (svID == null) {
                numberID = 0;
            } else {
                numberID  = Integer.parseInt(svID.substring(1));
            }
            newID = "D" + String.format("%05d", numberID + 1);
        } catch (RemoteException ex) {
            Logger.getLogger(AddSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newID;
    }
    
    private String getRdPass() {
        String Ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(8);
        for( int i = 0; i < 8; i++ )
           sb.append( Ab.charAt( rnd.nextInt(Ab.length()) ) );
        return sb.toString();
    }
    
    @FXML
    private void clickSubmit(ActionEvent event) {
        try {
            
            if (editSinhVien) {
                SinhVien sv2 = new SinhVien(
                        null,
                        null,
                        tfSvHoTen.getText(),
                        cbSvGioiTinh.getSelectionModel().getSelectedItem().getId(),
                        tfSvEmail.getText(),
                        tfSvSdt.getText(),
                        cbLopHoc.getSelectionModel().getSelectedItem().getClassID());
                server.modifySinhVien(getSv().getSvID(), sv2);
                setEditSinhVien(false);
                getStage().close();
            } else {
                SinhVien sv = new SinhVien(
                        getAutoSvID(),
                        getRdPass(),
                        tfSvHoTen.getText(),
                        cbSvGioiTinh.getSelectionModel().getSelectedItem().getId(),
                        tfSvEmail.getText(),
                        tfSvSdt.getText(),
                        cbLopHoc.getSelectionModel().getSelectedItem().getClassID());
                server.addSinhVien(sv);
                getStage().close();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(AddSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedClose(ActionEvent event) {
        getStage().close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AddSinhVienController.stage = stage;
    }

    public static boolean isEditSinhVien() {
        return editSinhVien;
    }

    public static void setEditSinhVien(boolean editSinhVien) {
        AddSinhVienController.editSinhVien = editSinhVien;
    }

    public static SinhVien getSv() {
        return sv;
    }

    public static void setSv(SinhVien sv) {
        AddSinhVienController.sv = sv;
    }
}
