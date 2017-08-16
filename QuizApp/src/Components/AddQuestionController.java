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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import iaquizapp.CauHoi;
import iaquizapp.MonHoc;
import java.net.URL;
import java.rmi.RemoteException;
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
import quizapp.LoginController;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class AddQuestionController implements Initializable {

    @FXML
    private JFXTextArea taCauHoi;
    @FXML
    private JFXTextField tfDapAnA;
    @FXML
    private JFXTextField tfDapAnB;
    @FXML
    private JFXTextField tfDapAnC;
    @FXML
    private JFXTextField tfDapAnD;
    @FXML
    private JFXButton btnThemCauHoi;
    @FXML
    private JFXButton btnHuy;
    private iaquizapp.IAQuizApp server;
    private static Stage stage;
    private static CauHoi cauhoi;
    private static boolean editCauHoi = false;
    @FXML
    private JFXComboBox<String> cbDapAnDungCauHoi;
    @FXML
    private JFXComboBox<MonHoc> cbMonHocCauHoi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        server = AdminHomeController.getServer();
        setComboBoxDAD();
        setComboBoxMonHoc();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (editCauHoi) {
                    btnThemCauHoi.setText("Cập Nhật");
                    taCauHoi.setText(getCauhoi().getCauhoiNoiDung());
                    tfDapAnA.setText(getCauhoi().getCauhoiDapAnA());
                    tfDapAnB.setText(getCauhoi().getCauhoiDapAnB());
                    tfDapAnC.setText(getCauhoi().getCauhoiDapAnC());
                    tfDapAnD.setText(getCauhoi().getCauhoiDapAnD());

                    cbDapAnDungCauHoi.getItems().stream().forEach(x -> {
                        if (x.contains(getCauhoi().getCauhoiDapAnDung())) {
                            cbDapAnDungCauHoi.getSelectionModel().select(x);
                        }
                    });

                    cbMonHocCauHoi.getItems().stream().forEach(x -> {
                        if (x.getMonID() == getCauhoi().getMonID()) {
                            cbMonHocCauHoi.getSelectionModel().select(x);
                        }
                    });
                }
            }
        });
    }
    
    private void setComboBoxDAD() {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        ObservableList<String> listItem = FXCollections.observableArrayList();
        listItem.addAll(list);
        cbDapAnDungCauHoi.setItems(listItem);
    }
    
    private void setComboBoxMonHoc() {
        try {
            List<MonHoc> list = server.getListMonHoc();
            ObservableList<MonHoc> itemlist = FXCollections.observableArrayList();
            itemlist.addAll(list);
            cbMonHocCauHoi.setItems(itemlist);
            cbMonHocCauHoi.setCellFactory(new Callback<ListView<MonHoc>, ListCell<MonHoc>>() {
                @Override
                public ListCell<MonHoc> call(ListView<MonHoc> param) {
                    return new JFXListCell<MonHoc>(){
                        @Override
                        public void updateItem(MonHoc item, boolean empty) {
                            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                            if (item != null) {
                                Text text = new Text(item.getMonName());
                                setGraphic(text);
                            }
                        }
                    };
                }
            });
            cbMonHocCauHoi.setConverter(new StringConverter<MonHoc>() {
                @Override
                public String toString(MonHoc object) {
                    if (object != null) {
                        return object.getMonName();
                    }
                    return null;
                }

                @Override
                public MonHoc fromString(String string) {
                    return null;
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void clickedThem(ActionEvent event) {
        if (taCauHoi.getText().equals("") || tfDapAnA.getText().equals("") || tfDapAnB.getText().equals("") || tfDapAnC.getText().equals("") || tfDapAnD.getText().equals("")) {
            
        } else if (cbDapAnDungCauHoi.getSelectionModel().isEmpty()) {
            
        } else if (cbMonHocCauHoi.getSelectionModel().isEmpty()) {
            
        } else if(editCauHoi) {
            try {
                server.modifyCauHoi(getCauhoi().getCauhoiID(), new CauHoi(taCauHoi.getText(),
                        tfDapAnA.getText(),
                        tfDapAnB.getText(),
                        tfDapAnC.getText(),
                        tfDapAnD.getText(),
                        cbDapAnDungCauHoi.getSelectionModel().getSelectedItem(),
                        cbMonHocCauHoi.getSelectionModel().getSelectedItem().getMonID()));
                editCauHoi = false;
            } catch (RemoteException ex) {
                Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                server.addCauHoi(new CauHoi(taCauHoi.getText(),
                        tfDapAnA.getText(),
                        tfDapAnB.getText(),
                        tfDapAnC.getText(),
                        tfDapAnD.getText(), 
                        cbDapAnDungCauHoi.getSelectionModel().getSelectedItem(),
                        cbMonHocCauHoi.getSelectionModel().getSelectedItem().getMonID()));
                
            } catch (RemoteException ex) {
                Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getStage().close();
    }

    @FXML
    private void clickClosed(ActionEvent event) {
        getStage().close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AddQuestionController.stage = stage;
    }

    public static CauHoi getCauhoi() {
        return cauhoi;
    }

    public static void setCauhoi(CauHoi cauhoi) {
        AddQuestionController.cauhoi = cauhoi;
    }

    public static boolean isEditCauHoi() {
        return editCauHoi;
    }

    public static void setEditCauHoi(boolean editCauHoi) {
        AddQuestionController.editCauHoi = editCauHoi;
    }
}
