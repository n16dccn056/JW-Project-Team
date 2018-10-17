/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

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
public class SetDetailOrder {

    public static interface IStateSetDetailOrder {
        void onStart();
        void onEnd();
        void onSuccess(boolean a);
        void onError();
    }
    private IStateSetDetailOrder stateGet;

    public SetDetailOrder(IStateSetDetailOrder stateGet) {
        this.stateGet = stateGet;
    }
    
    public void setDetailOrder(String query){
       
        try {
            Connection conn = null;
            try {
                stateGet.onStart();
                
                conn = ConnectSQLServer.getConnectCurrent();
            } catch (Exception ex) {
                Logger.getLogger(SetDetailOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (conn==null){
                stateGet.onEnd() ;
                stateGet.onError();
                return;
            }
            // set data
            PreparedStatement pst = conn.prepareStatement(query);
            pst.executeUpdate();
            stateGet.onSuccess(true);
            
        } catch (Exception ex) {
            Logger.getLogger(SetDetailOrder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             stateGet.onEnd();
        }
       
        //stateGet.onSuccess(arr);
    }
    
     
}
