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
public class InsertSanPham {

    public static interface IStateInsertSanPham {

        void onStart();

        void onEnd();

        void onSuccess(SanPham sp);

        void onError(String error);
    }
    private IStateInsertSanPham stateGet;

    public InsertSanPham(IStateInsertSanPham stateGet) {
        this.stateGet = stateGet;
    }

    public void insertSanPham(SanPham sp) {

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
            String sql = "INSERT INTO PRODUCT ("
                   // +  "PRODUCT_ID," 
                    + "PRODUCT_NAME,"
                    + "PRODUCT_PRICE,"
                    + "PRODUCT_QUANTITY,"
                    + "PRODUCT_TYPE_ID,"
                    + "TRADEMARK_ID,"
                    + "STATE ) "
                    + "VALUES(?,?,?,?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                //ps.setInt(1,sp.GetID());
                ps.setString(1, sp.GetName());
                ps.setInt(2, sp.GetPrice());
                ps.setInt(3, sp.GetQuantity());
                ps.setInt(4, sp.GetTypeID());
                ps.setInt(5, sp.GetTradeMarkID());
                ps.setInt(6,sp.getState());

                boolean rs = ps.executeUpdate() > 0;
                if (rs) {
                    stateGet.onSuccess(sp);
                } else {
                    throw new Exception("Error to insert San Pham");
                }

            } catch (Exception e) {
                stateGet.onError(e.toString());
            }

        } catch (SQLException ex) {
            stateGet.onError(ex.toString());
        } finally {
            stateGet.onEnd();
        }
    }

}
