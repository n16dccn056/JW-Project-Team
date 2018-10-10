/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import screens.DangNhap;
import screens.MainApp;
import services.ConnectSQLServer;
import services.StateLoginListener;
import data.GetSanPhamData;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        ConnectSQLServer.connect(new StateLoginListener() {

            @Override
            public void onConnectSuccess() {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new DangNhap().setVisible(true);
                    }
                });
            }

            @Override
            public void onConnectFailure(String error) {
                int n = JOptionPane.showConfirmDialog(
                        null, "Do you wan to reconnect?",
                        "Error connect server",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    ConnectSQLServer.connect(this);
                } else if (n == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });
        /* Create and display the form */

    }

}
