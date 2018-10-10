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
public class GetSanPhamData {

    public static interface IStateGetSanPham {
        void onStart();
        void onEnd();
        void onSuccess(ArrayList<SanPham> arr);
        void onError(String error);
    }
    private IStateGetSanPham stateGet;

    public GetSanPhamData(IStateGetSanPham stateGet) {
        this.stateGet = stateGet;
    }
    
    public void GetSanPhamData(String query){
        ArrayList<SanPham> arrsp = new ArrayList();
       
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
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.SetID(rs.getInt(1));
                sp.SetName(rs.getString(2));
                sp.SetPrice(rs.getInt(3));
                sp.SetQuantity(rs.getInt(4));
                sp.SetTypeID(rs.getInt(5));
                sp.SetTradeMarkID(rs.getInt(6));
                arrsp.add(sp);
            }
            stateGet.onSuccess(arrsp);
            
        } catch (SQLException ex) {
            Logger.getLogger(GetSanPhamData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             stateGet.onEnd();
        }
       
        //stateGet.onSuccess(arr);
    }
    
     
}
