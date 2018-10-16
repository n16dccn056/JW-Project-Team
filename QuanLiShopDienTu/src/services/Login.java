/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Login {
    
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void login(StateLogin stateLogin) {
        try {
            Connection conn = ConnectSQLServer.getConnectCurrent();
            Statement stmt = conn.createStatement();
            String query = String.format("select * from STAFF where STAFF_ACCOUNT_NAME = %s"
                    + " && STAFF_ACCOUNT_PASSWORD = %s", userName,password);
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(query);
            // show data
            if (rs.first()){
                stateLogin.onLoginSuccess();
            } else {
                stateLogin.onLoginFailure("Tên tài khoản hoặc mật khẩu sai!!");
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            stateLogin.onLoginFailure(ex.toString());
        }
}

public interface StateLogin {

    void onLoginSuccess();

    void onLoginFailure(String error);
}
}
