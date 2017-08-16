/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Components.AddQuestionController;
import Components.AddSinhVienController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import iaquizapp.CauHoi;
import iaquizapp.IAQuizApp;
import iaquizapp.Lop;
import iaquizapp.MonHoc;
import iaquizapp.SinhVien;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import quizapp.LoginController;

/**
 * FXML Controller class
 *
 * @author vunvd
 */
public class AdminHomeController implements Initializable {

    @FXML
    private VBox vbKiThi;
    @FXML
    private VBox vbCauHoi;
    @FXML
    private VBox vbMonHoc;
    @FXML
    private Pane btnKiThi;
    @FXML
    private Pane btnLopHoc;
    @FXML
    private Pane btnMonHoc;
    @FXML
    private Pane btnCauHoi;
    @FXML
    private Pane btnSinhVien;
    @FXML
    private VBox vbSinhVien;
    @FXML
    private VBox vbLopHoc;
    @FXML
    private JFXTextField tfTenLopHoc;
    @FXML
    private JFXListView<Lop> lvLopHoc;
    private static iaquizapp.IAQuizApp server;
    @FXML
    private StackPane stackPane;
    private boolean editLop = false;
    private boolean editMonHoc = false;
    private int classID = 0;
    private int monID = 0;
    @FXML
    private JFXButton btnThemLop;
    @FXML
    private Text txtValidLop;
    @FXML
    private JFXTextField tfTenMonHoc;
    @FXML
    private Text txtValidMonHoc;
    @FXML
    private JFXButton btnThemMonHoc;
    @FXML
    private JFXListView<MonHoc> lvMonHoc;
    @FXML
    private JFXComboBox<MonHoc> cbMonHocCauHoi;
    @FXML
    private JFXListView<CauHoi> lvCauHoi;
    @FXML
    private VBox vbThongTinSv;
    @FXML
    private Text txtSvID;
    @FXML
    private Text txtSvHoTen;
    @FXML
    private Text txtSvGioiTinh;
    @FXML
    private Text txtSvEmail;
    @FXML
    private Text txtSvSdt;
    @FXML
    private Text txtSvClass;
    @FXML
    private JFXComboBox<Lop> cbDsLop;
    @FXML
    private ImageView btnThemSinhVien;
    @FXML
    private JFXListView<SinhVien> lvSinhVien;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            server = (IAQuizApp) Naming.lookup("rmi://localhost:3000/server_quizapp");
        } catch (NotBoundException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setListLopHoc() {
        try {
            List<Lop> list = server.getListLop();
            ObservableList<Lop> listItem = FXCollections.observableArrayList();
            listItem.addAll(list);
            lvLopHoc.setItems(listItem);
            lvLopHoc.setCellFactory(new Callback<ListView<Lop>, ListCell<Lop>>() {
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
                                    
                                    urlImage = getClass().getResource("../Assets/View_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView view = new ImageView(new Image(file.toURI().toString()));
                                    view.setFitHeight(20);
                                    view.setFitWidth(20);
                                    view.setCursor(Cursor.HAND);
                                    view.getStyleClass().add("list-cell__button");
                                    view.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            
                                        }
                                    });
                                    
                                    
                                    urlImage = getClass().getResource("../Assets/Edit File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView edit = new ImageView(new Image(file.toURI().toString()));
                                    edit.setFitHeight(20);
                                    edit.setFitWidth(20);
                                    edit.setCursor(Cursor.HAND);
                                    edit.getStyleClass().add("list-cell__button");
                                    edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editLop = true;
                                            classID = item.getClassID();
                                            tfTenLopHoc.setFocusTraversable(editLop);
                                            tfTenLopHoc.setText(item.getClassTen());
                                            btnThemLop.setText("Cập Nhật");
                                        }
                                    });
                                    
                                    urlImage = getClass().getResource("../Assets/Delete File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView delete = new ImageView(new Image(file.toURI().toString()));
                                    delete.setFitHeight(20);
                                    delete.setFitWidth(20);
                                    delete.setCursor(Cursor.HAND);
                                    delete.getStyleClass().add("list-cell__button");
                                    delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            JFXDialogLayout content = new JFXDialogLayout();
                                            JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
                                            JFXButton ok = new JFXButton("Xóa");
                                            ok.getStyleClass().add("btn-gray");
                                            ok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    try {
                                                        server.deleteLop(item.getClassID());
                                                        setListLopHoc();
                                                        dialog.close();
                                                    } catch (RemoteException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    } catch (SQLException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            });
                                            
                                            JFXButton cancel = new JFXButton("Hủy");
                                            cancel.getStyleClass().add("btn-gray");
                                            cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    dialog.close();
                                                }
                                            });
                                            HBox hbox = new HBox(ok, cancel);
                                            hbox.setAlignment(Pos.CENTER_RIGHT);
                                            hbox.setSpacing(10);
                                            
                                            content.setHeading(new Text("Bạn có chắc chắc muốn xóa ?"));
                                            content.setBody(hbox);
                                            dialog.show();
                                        }
                                    });
                                    
                                    Text name = new Text(item.getClassTen());
                                    name.setFont(new Font(16));
                                    name.setFill(Color.gray(0.31));
                                    Pane pn = new Pane();
                                    
                                    HBox hbox = new HBox(graphic, name, pn, view, edit, delete);
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
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setListMonHoc() {
        try {
            List<MonHoc> list = server.getListMonHoc();
            ObservableList<MonHoc> listItem = FXCollections.observableArrayList();
            listItem.addAll(list);
            lvMonHoc.setItems(listItem);
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
                                    
                                    urlImage = getClass().getResource("../Assets/Edit File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView edit = new ImageView(new Image(file.toURI().toString()));
                                    edit.setFitHeight(20);
                                    edit.setFitWidth(20);
                                    edit.setCursor(Cursor.HAND);
                                    edit.getStyleClass().add("list-cell__button");
                                    edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editMonHoc = true;
                                            monID = item.getMonID();
                                            tfTenMonHoc.setFocusTraversable(editMonHoc);
                                            tfTenMonHoc.setText(item.getMonName());
                                            btnThemMonHoc.setText("Cập Nhật");
                                        }
                                    });
                                    
                                    urlImage = getClass().getResource("../Assets/Delete File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView delete = new ImageView(new Image(file.toURI().toString()));
                                    delete.setFitHeight(20);
                                    delete.setFitWidth(20);
                                    delete.setCursor(Cursor.HAND);
                                    delete.getStyleClass().add("list-cell__button");
                                    delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            JFXDialogLayout content = new JFXDialogLayout();
                                            JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
                                            JFXButton ok = new JFXButton("Xóa");
                                            ok.getStyleClass().add("btn-gray");
                                            ok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    try {
                                                        server.deleteMonHoc(item.getMonID());
                                                        setListMonHoc();
                                                        dialog.close();
                                                    } catch (RemoteException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    } catch (SQLException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            });
                                            
                                            JFXButton cancel = new JFXButton("Hủy");
                                            cancel.getStyleClass().add("btn-gray");
                                            cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    dialog.close();
                                                }
                                            });
                                            HBox hbox = new HBox(ok, cancel);
                                            hbox.setAlignment(Pos.CENTER_RIGHT);
                                            hbox.setSpacing(10);
                                            
                                            content.setHeading(new Text("Bạn có chắc chắc muốn xóa ?"));
                                            content.setBody(hbox);
                                            dialog.show();
                                        }
                                    });
                                    
                                    Text name = new Text(item.getMonName());
                                    name.setFont(new Font(16));
                                    name.setFill(Color.gray(0.31));
                                    Pane pn = new Pane();
                                    
                                    HBox hbox = new HBox(graphic, name, pn, edit, delete);
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
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                                getStyleClass().add("list-cell--gray");
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
            cbMonHocCauHoi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MonHoc>() {
                @Override
                public void changed(ObservableValue<? extends MonHoc> observable, MonHoc oldValue, MonHoc newValue) {
                    setListCauHoi();
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void setListCauHoi() {
        try {
            List<CauHoi> list;
            if (cbMonHocCauHoi.getSelectionModel().getSelectedItem() != null) {
                list = server.getListCauHoi(cbMonHocCauHoi.getSelectionModel().getSelectedItem().getMonID());
            } else if (!cbMonHocCauHoi.getItems().isEmpty()) {
                list = server.getListCauHoi(cbMonHocCauHoi.getItems().get(0).getMonID());
            } else {
                list = server.getListCauHoi(0);
            }
                
            ObservableList<CauHoi> itemList = FXCollections.observableArrayList();
            itemList.addAll(list);
            lvCauHoi.setItems(itemList);
            
            lvCauHoi.setCellFactory(new Callback<ListView<CauHoi>, ListCell<CauHoi>>() {
                @Override
                public ListCell<CauHoi> call(ListView<CauHoi> param) {
                    return new JFXListCell<CauHoi>(){
                        @Override
                        public void updateItem(CauHoi item, boolean empty) {
                            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                            if (item != null) {
                                try {
                                    URL urlImage = getClass().getResource("../Assets/Book2_50px.png");
                                    File file = new File(urlImage.toURI());
                                    ImageView graphic = new ImageView(new Image(file.toURI().toString()));
                                    graphic.setFitHeight(20);
                                    graphic.setFitWidth(20);
                                    
                                    urlImage = getClass().getResource("../Assets/Edit File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView edit = new ImageView(new Image(file.toURI().toString()));
                                    edit.setFitHeight(20);
                                    edit.setFitWidth(20);
                                    edit.setCursor(Cursor.HAND);
                                    edit.getStyleClass().add("list-cell__button");
                                    edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            try {
                                                Stage stage = new Stage();
                                                Parent root = FXMLLoader.load(getClass().getResource("/Components/AddQuestion.fxml"));
                                                
                                                stage.setOnHidden(new EventHandler<WindowEvent>() {
                                                    @Override
                                                    public void handle(WindowEvent event) {
                                                        setListCauHoi();
                                                        AddQuestionController.setEditCauHoi(false);
                                                    }
                                                });
                                                stage.initStyle(StageStyle.UNDECORATED);
                                                Scene scene = new Scene(root);
                                                stage.setResizable(false);
                                                stage.setScene(scene);
                                                AddQuestionController.setStage(stage);
                                                AddQuestionController.setCauhoi(item);
                                                AddQuestionController.setEditCauHoi(true);
                                                stage.show();
                                            } catch (IOException ex) {
                                                Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });
                                    
                                    urlImage = getClass().getResource("../Assets/Delete File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView delete = new ImageView(new Image(file.toURI().toString()));
                                    delete.setFitHeight(20);
                                    delete.setFitWidth(20);
                                    delete.setCursor(Cursor.HAND);
                                    delete.getStyleClass().add("list-cell__button");
                                    delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            JFXDialogLayout content = new JFXDialogLayout();
                                            JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
                                            JFXButton ok = new JFXButton("Xóa");
                                            ok.getStyleClass().add("btn-gray");
                                            ok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    try {
                                                        server.deleteCauHoi(item.getCauhoiID());
                                                        setListCauHoi();
                                                        dialog.close();
                                                    } catch (RemoteException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    } catch (SQLException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            });
                                            
                                            JFXButton cancel = new JFXButton("Hủy");
                                            cancel.getStyleClass().add("btn-gray");
                                            cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    dialog.close();
                                                }
                                            });
                                            HBox hbox = new HBox(ok, cancel);
                                            hbox.setAlignment(Pos.CENTER_RIGHT);
                                            hbox.setSpacing(10);
                                            
                                            content.setHeading(new Text("Bạn có chắc chắc muốn xóa ?"));
                                            content.setBody(hbox);
                                            dialog.show();
                                        }
                                    });
                                    
                                    Text name = new Text(item.getCauhoiNoiDung());
                                    name.setFont(new Font(16));
                                    name.setFill(Color.gray(0.31));
                                    Pane pn = new Pane();
                                    
                                    HBox hbox = new HBox(graphic, name, pn, edit, delete);
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
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void setComboBoxLop() {
        try {
            List<Lop> list = server.getListLop();
            ObservableList<Lop> itemlist = FXCollections.observableArrayList();
            itemlist.addAll(list);
            cbDsLop.setItems(itemlist);
            cbDsLop.setCellFactory(new Callback<ListView<Lop>, ListCell<Lop>>() {
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
            cbDsLop.setConverter(new StringConverter<Lop>() {
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
            cbDsLop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Lop>() {
                @Override
                public void changed(ObservableValue<? extends Lop> observable, Lop oldValue, Lop newValue) {
                    setListSinhVien();
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setListSinhVien() {
        try {
            List<SinhVien> list = null;
            if (cbDsLop.getSelectionModel().getSelectedItem() != null) {
                list = server.getListSinhVien(cbDsLop.getSelectionModel().getSelectedItem().getClassID());
            } else if(!cbDsLop.getItems().isEmpty()) {
                list = server.getListSinhVien(cbDsLop.getItems().get(0).getClassID());
            } else {
                list = server.getListSinhVien(0);
            }
            ObservableList<SinhVien> itemList = FXCollections.observableArrayList();
            itemList.addAll(list);
            lvSinhVien.setItems(itemList);
            lvSinhVien.getSelectionModel().select(0);
            lvSinhVien.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SinhVien>() {
                @Override
                public void changed(ObservableValue<? extends SinhVien> observable, SinhVien oldValue, SinhVien newValue) {
                    setSvInfo(newValue);
                }
            });
            
            lvSinhVien.setCellFactory(new Callback<ListView<SinhVien>, ListCell<SinhVien>>() {
                @Override
                public ListCell<SinhVien> call(ListView<SinhVien> param) {
                    return new JFXListCell<SinhVien>(){
                        @Override
                        public void updateItem(SinhVien item, boolean empty) {
                            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                            if (item != null) {
                                try {
                                    URL urlImage = getClass().getResource("../Assets/Student Female_50px.png");
                                    if (item.getSvGioiTinh() == 1) {
                                        urlImage = getClass().getResource("../Assets/Student Male_50px.png");
                                    }
                                    File file = new File(urlImage.toURI());
                                    ImageView graphic = new ImageView(new Image(file.toURI().toString()));
                                    graphic.setFitHeight(20);
                                    graphic.setFitWidth(20);
                                    
                                    urlImage = getClass().getResource("../Assets/Edit File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView edit = new ImageView(new Image(file.toURI().toString()));
                                    edit.setFitHeight(20);
                                    edit.setFitWidth(20);
                                    edit.setCursor(Cursor.HAND);
                                    edit.getStyleClass().add("list-cell__button");
                                    edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            try {
                                                Parent root = FXMLLoader.load(getClass().getResource("/Components/AddSinhVien.fxml"));
                                                Stage stage = new Stage();
                                                AddSinhVienController.setStage(stage);
                                                Scene scene = new Scene(root);
                                                stage.setOnHidden(new EventHandler<WindowEvent>() {
                                                    @Override
                                                    public void handle(WindowEvent event) {
                                                        setListSinhVien();
                                                    }
                                                });
                                                stage.setResizable(true);
                                                stage.setScene(scene);
                                                AddSinhVienController.setSv(item);
                                                AddSinhVienController.setEditSinhVien(true);
                                                stage.show();
                                            } catch (IOException ex) {
                                                Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });
                                    
                                    urlImage = getClass().getResource("../Assets/Delete File_50px.png");
                                    file = new File(urlImage.toURI());
                                    ImageView delete = new ImageView(new Image(file.toURI().toString()));
                                    delete.setFitHeight(20);
                                    delete.setFitWidth(20);
                                    delete.setCursor(Cursor.HAND);
                                    delete.getStyleClass().add("list-cell__button");
                                    delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            JFXDialogLayout content = new JFXDialogLayout();
                                            JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
                                            JFXButton ok = new JFXButton("Xóa");
                                            ok.getStyleClass().add("btn-gray");
                                            ok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    try {
                                                        server.deleteSinhVien(item.getSvID());
                                                        setListSinhVien();
                                                        dialog.close();
                                                    } catch (RemoteException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    } catch (SQLException ex) {
                                                        Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            });
                                            
                                            JFXButton cancel = new JFXButton("Hủy");
                                            cancel.getStyleClass().add("btn-gray");
                                            cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    dialog.close();
                                                }
                                            });
                                            HBox hbox = new HBox(ok, cancel);
                                            hbox.setAlignment(Pos.CENTER_RIGHT);
                                            hbox.setSpacing(10);
                                            
                                            content.setHeading(new Text("Bạn có chắc chắc muốn xóa ?"));
                                            content.setBody(hbox);
                                            dialog.show();
                                        }
                                    });
                                    
                                    Text id = new Text(item.getSvID());
                                    id.setFont(new Font(16));
                                    id.setFill(Color.gray(0.31));
                                    id.setWrappingWidth(80);
                                    
                                    Text name = new Text(item.getSvHoTen());
                                    
                                    name.setFont(new Font(16));
                                    name.setFill(Color.gray(0.31));
                                    Pane pn = new Pane();
                                    
                                    HBox hbox = new HBox(graphic, id, name, pn, edit, delete);
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
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private String getAutoSvID() {
        return null;
    }
    
    private void setSvInfo(SinhVien sv) {
        if (sv != null) {
            vbThongTinSv.setVisible(true);
            txtSvID.setText(sv.getSvID());
            txtSvHoTen.setText(sv.getSvHoTen());
            txtSvGioiTinh.setText( sv.getSvGioiTinh() == 1 ? "Nam" : "Nữ");
            txtSvEmail.setText(sv.getSvEmail());
            txtSvSdt.setText(sv.getSvSDT());
            txtSvClass.setText(sv.getClassTen());
        } else {
            vbThongTinSv.setVisible(false);
        }
    } 
    
    @FXML
    private void clickedShowKiThi(MouseEvent event) {
        showActive(btnKiThi);
        showUI(vbKiThi);
    }

    @FXML
    private void clickedShowLopHoc(MouseEvent event) {
        setListLopHoc();
        
        showActive(btnLopHoc);
        showUI(vbLopHoc);
    }

    @FXML
    private void clickedShowMonHoc(MouseEvent event) {
        setListMonHoc();
        
        showActive(btnMonHoc);
        showUI(vbMonHoc);
    }
    
    @FXML
    private void clickedShowCauHoi(MouseEvent event) {
        setComboBoxMonHoc();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cbMonHocCauHoi.getSelectionModel().select(0);
                setListCauHoi();
            }
        });
        
        showActive(btnCauHoi);
        showUI(vbCauHoi);
    }

    @FXML
    private void clickedShowSinhVien(MouseEvent event) {
        setComboBoxLop();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cbDsLop.getSelectionModel().select(0);
                setListSinhVien();
            }
        });
        showActive(btnSinhVien);
        showUI(vbSinhVien);
    }
    
    private void showUI(VBox visible) {
        vbCauHoi.setVisible(false);
        vbKiThi.setVisible(false);
        vbMonHoc.setVisible(false);
        vbSinhVien.setVisible(false);
        vbLopHoc.setVisible(false);
        
        visible.setVisible(true);
    }
    
    private void showActive(Pane active) {
        btnCauHoi.getStyleClass().remove("dashboad-item--active");
        btnKiThi.getStyleClass().remove("dashboad-item--active");
        btnLopHoc.getStyleClass().remove("dashboad-item--active");
        btnMonHoc.getStyleClass().remove("dashboad-item--active");
        btnSinhVien.getStyleClass().remove("dashboad-item--active");
        
        active.getStyleClass().add("dashboad-item--active");
    }

    @FXML
    private void clickedThemLopHoc(MouseEvent event) {
        try {
            if (tfTenLopHoc.getText() == null || tfTenLopHoc.getText().equals("")) {
                txtValidLop.setText("Tên lớp không thể bỏ trống !");
            } else if (editLop) {
                server.modifyLop(classID, new Lop(tfTenLopHoc.getText()));
                tfTenLopHoc.setText("");
                setListLopHoc();
                editLop = false;
                tfTenLopHoc.setFocusTraversable(editLop);
                btnThemLop.setText("Thêm");
                txtValidLop.setText("");
            } else if (server.isValidLop(tfTenLopHoc.getText())) {
                txtValidLop.setText("Lớp đã tồn tại !");
            }  else {
                server.addLop(new Lop(tfTenLopHoc.getText()));
                tfTenLopHoc.setText("");
                txtValidLop.setText("");
                setListLopHoc();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedThemMonHoc(MouseEvent event) {
        try {
            if (tfTenMonHoc.getText() == null || tfTenMonHoc.getText().equals("")) {
                txtValidMonHoc.setText("Tên lớp không thể bỏ trống !");
            } else if (editMonHoc) {
                server.modifyMonHoc(monID, new MonHoc(tfTenMonHoc.getText()));
                tfTenMonHoc.setText("");
                setListMonHoc();
                editMonHoc = false;
                tfTenMonHoc.setFocusTraversable(editLop);
                btnThemMonHoc.setText("Thêm");
                txtValidLop.setText("");
            } else if (server.isValidMonHoc(tfTenMonHoc.getText())) {
                txtValidMonHoc.setText("Môn đã tồn tại !");
            } else {
                server.addMonHoc(new MonHoc(tfTenMonHoc.getText()));
                tfTenMonHoc.setText("");
                txtValidMonHoc.setText("");
                setListMonHoc();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedThemCauHoi(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Components/AddQuestion.fxml"));
            AddQuestionController.setStage(stage);
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    setListCauHoi();
                }
            });
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static IAQuizApp getServer() {
        return server;
    }

    public static void setServer(IAQuizApp server) {
        AdminHomeController.server = server;
    }

    @FXML
    private void clickedShowAddSinhVien(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Components/AddSinhVien.fxml"));
            Stage stage = new Stage();
            AddSinhVienController.setStage(stage);
            Scene scene = new Scene(root);
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    setListSinhVien();
                }
            });
            stage.setResizable(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedShowAddKiThi(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Components/AddKiThi.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
