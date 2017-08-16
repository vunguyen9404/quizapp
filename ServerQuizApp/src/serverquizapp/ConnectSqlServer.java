/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverquizapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vunvd
 */
public class ConnectSqlServer {
    Connection conn = null;
    public Connection getConnectDB(){
        try {
            String uRl = "jdbc:sqlserver://localhost:1433;databaseName=ThiTracNghiem";
            String user = "sa";
            String pass = "12345678";
            conn = DriverManager.getConnection(uRl, user, pass);
        } catch (SQLException e) {
            System.out.println("Khong ket Noi Dc");
        }
        return conn;
    }
}
