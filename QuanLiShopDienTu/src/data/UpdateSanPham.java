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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.SanPham;
import services.ConnectSQLServer;

/**
 *
 * @author admin
 */
public class UpdateSanPham {

    public static interface IStateInsertSanPham {

        void onStart();

        void onEnd();

        void onSuccess(SanPham sp);

        void onError(String error);
    }
    private IStateInsertSanPham stateGet;

    public UpdateSanPham(IStateInsertSanPham stateGet) {
        this.stateGet = stateGet;
    }

    public void updateSanPham(SanPham sp) {

        try {
            Connection conn = null;
            try {
                stateGet.onStart();

                conn = ConnectSQLServer.getConnectCurrent();
            } catch (Exception ex) {
                Logger.getLogger(GetSanPhamData.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (conn == null) {
                stateGet.onEnd();
                stateGet.onError("Can not connect to SQLSERVER");
                return;
            }
            // crate statement
            Statement stmt = conn.createStatement();
            // show data
            String query="UPDATE PRODUCT SET "
                    + "PRODUCT_NAME = ?,"
                    + "PRODUCT_PRICE =?,"
                    + "PRODUCT_QUANTITY =?,"
                    + "PRODUCT_TYPE_ID = ?,"
                    + "TRADEMARK_ID=?,"
                    + "STATE = ? "
                    + "WHERE PRODUCT_ID = "+sp.GetID();
                    
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,sp.GetName());
            pst.setInt(2, sp.GetPrice());
            pst.setInt(3, sp.GetQuantity());
            pst.setInt(4, sp.GetTypeID());
            pst.setInt(5, sp.GetTradeMarkID());
            pst.setInt(6, sp.getState());
            
            try {
                boolean  rs =pst.executeUpdate()>0;
               
                if (rs) {
                    
                    stateGet.onSuccess(sp);
                } else {
                    
                    throw new Exception("Error to update San Pham");
                }

            } catch (Exception e) {
                System.out.println(e.toString());
                stateGet.onError(e.toString());
            }

        } catch (SQLException ex) {
            stateGet.onError(ex.toString());
        } finally {
            stateGet.onEnd();
        }
    }

}
