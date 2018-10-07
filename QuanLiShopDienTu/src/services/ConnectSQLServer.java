/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class ConnectSQLServer {
    private static final String DATATABASE_NAME = "QuanLiShopDienTu";
    private static final String DB_URL = String.format("jdbc:sqlserver://localhost:1433;"
            + "databaseName=%s;"
            + "integratedSecurity=true",DATATABASE_NAME);
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "123456789";
    
    private static Connection connection = null;
    
    public static synchronized Connection getConnectCurrent() throws Exception{
        if (connection == null){
            throw new Exception("Chưa kết nối dữ liệu!");
        }
        return connection;
    }
    
    private static void connect(StateLoginListener stateLogin){
        connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            stateLogin.onConnectSuccess();
        } catch (Exception ex) {
            stateLogin.onConnectFailure();
        }
    }

    

}
