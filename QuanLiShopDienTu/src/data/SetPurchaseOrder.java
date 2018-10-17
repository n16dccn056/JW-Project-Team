/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import common.Constant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ConnectSQLServer;



/**
 *
 * @author admin
 */
public class SetPurchaseOrder {

    public static interface IStateSetPurchaseOrder {
        void onStart();
        void onEnd();
        void onSuccess(boolean a);
        void onError();
    }
    private IStateSetPurchaseOrder stateGet;

    public SetPurchaseOrder(IStateSetPurchaseOrder stateGet) {
        this.stateGet = stateGet;
    }
    
    public void setPurchaseOrder(String[] arrstr){
       
        try {
            Connection conn = null;
            try {
                stateGet.onStart();
                
                conn = ConnectSQLServer.getConnectCurrent();
            } catch (Exception ex) {
                Logger.getLogger(SetPurchaseOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (conn==null){
                stateGet.onEnd();
                stateGet.onError();
                return;
            }
            // set data
            PreparedStatement pst = conn.prepareStatement(Constant.QUERY_SET_PURCHASE_ORDER);
            pst.setInt(1,Integer.parseInt(arrstr[0]));
            pst.setInt(2,Integer.parseInt(arrstr[1]));
            pst.setNString(3,arrstr[2]);
            pst.setString(4,arrstr[3]);
            pst.executeUpdate();
            stateGet.onSuccess(true);
            
        } catch (Exception ex) {
            Logger.getLogger(SetPurchaseOrder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             stateGet.onEnd();
        }
       
        //stateGet.onSuccess(arr);
    }
    
     
}
