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
import models.SanPham;
import services.ConnectSQLServer;


/**
 *
 * @author admin
 */
public class GetPurchaseOrder {

    public static interface IStateGetPurchaseOrder {
        void onStart();
        void onEnd();
        void onSuccess(int a);
        void onError(String error);
    }
    private IStateGetPurchaseOrder stateGet;

    public GetPurchaseOrder(IStateGetPurchaseOrder stateGet) {
        this.stateGet = stateGet;
    }
    
    public void getPurchaseOrder(String query){       
        try {
            Connection conn = null;
            try {
                stateGet.onStart();
                
                conn = ConnectSQLServer.getConnectCurrent();
            } catch (Exception ex) {
                Logger.getLogger(GetPurchaseOrder.class.getName()).log(Level.SEVERE, null, ex);
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
            int id=0;
            while(rs.next()){
                id=rs.getInt(1);
            }
            stateGet.onSuccess(id);
            
        } catch (SQLException ex) {
            Logger.getLogger(GetPurchaseOrder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             stateGet.onEnd();
        }
       
        //stateGet.onSuccess(arr);
    }
    
     
}
