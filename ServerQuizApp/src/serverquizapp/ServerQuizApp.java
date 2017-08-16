/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverquizapp;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vunvd
 */
public class ServerQuizApp {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        try {
            ServerQuizAppDAO server = new ServerQuizAppDAO();
            Registry res = LocateRegistry.createRegistry(3000);
            res.rebind("server_quizapp", server);
            System.out.println("Server listening in port 3000...");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerQuizApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerQuizApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
