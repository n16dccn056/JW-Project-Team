/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.LoaiSanPham;
import models.SanPham;
import models.Staff;
import services.ConnectSQLServer;


/**
 *
 * @author admin
 */
public class GetInforCurrentUser  {

    public static interface IStateGetCurrentUser {
        void onStart();
        void onEnd();
        void onSuccess(Staff staff);
        void onError(String error);
    }
    private IStateGetCurrentUser stateGet;

    public GetInforCurrentUser(IStateGetCurrentUser stateGet) {
        this.stateGet = stateGet;
    }
    
    public void getInfoCurrentUser(String query){
        Staff staff = null;
       
        try {
            Connection conn = null;
            try {
                stateGet.onStart();
                
                conn = ConnectSQLServer.getConnectCurrent();
            } catch (Exception ex) {
                Logger.getLogger(GetSanPhamData.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (conn==null){
                stateGet.onEnd();
                stateGet.onError("Can not connect to SQLSERVER");
                return;
            }
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(query);
            // show data
            
            if (rs.next()){
                staff = new Staff();
                staff.setId(rs.getInt(1));
                staff.setName(rs.getNString(2));
                staff.setAccountName(rs.getString(3));
                staff.setPassword(rs.getString(4));
                staff.setAddress(rs.getString(5));
                staff.setBirthdate(rs.getString(6));
                staff.setPhoneNumber(rs.getString(7));
                staff.setGender(rs.getBoolean(8));
                staff.setCmnd(rs.getString(9));
                staff.setTypeStaffId(rs.getInt(10));
                stateGet.onSuccess(staff);
            } else {
                stateGet.onError("No user!");
            }
            

            
        } catch (SQLException ex) {
            
        } finally {
             stateGet.onEnd();
        }
       
        //stateGet.onSuccess(arr);
    }
    
     
}
