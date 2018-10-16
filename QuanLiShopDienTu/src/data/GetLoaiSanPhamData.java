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
import services.ConnectSQLServer;


/**
 *
 * @author admin
 */
public class GetLoaiSanPhamData  {

    public static interface IStateGetLoaiSanPham {
        void onStart();
        void onEnd();
        void onSuccess(ArrayList<LoaiSanPham> arr);
        void onError(String error);
    }
    private IStateGetLoaiSanPham stateGet;

    public GetLoaiSanPhamData(IStateGetLoaiSanPham stateGet) {
        this.stateGet = stateGet;
    }
    
    public void GetLoaiSanPhamData(String query){
        ArrayList<LoaiSanPham> arrsp = new ArrayList();
       
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
                LoaiSanPham sp = new LoaiSanPham();
                sp.setID(rs.getInt(1));
                sp.setName(rs.getNString(2));
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
