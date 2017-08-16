/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import Admin.AdminHomeController;
import iaquizapp.IAQuizApp;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

/**
 *
 * @author vunvd
 */
public class LoginController implements Initializable {
    private static iaquizapp.IAQuizApp server;
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

    public static IAQuizApp getServer() {
        return server;
    }

    public static void setServer(IAQuizApp server) {
        LoginController.server = server;
    }
    
}
