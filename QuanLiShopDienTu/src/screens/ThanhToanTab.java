/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import common.InforStaff;
import data.GetInforCurrentUser;
import data.GetPurchaseOrder;
import data.GetSanPhamData;
import data.SetDetailOrder;
import data.SetPurchaseOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.SanPham;

/**
 *
 * @author admin
 */
public class ThanhToanTab extends javax.swing.JPanel {
    private GetSanPhamData getSPData;
    private String staffname;
    private int staffid;
    private SetPurchaseOrder setpurchaseorder;
    private SetDetailOrder setdetailorder;
    private GetPurchaseOrder getpurchaseorder;
    /**
     * Creates new form ThanhToanTab
     */

    public ThanhToanTab() {
        initComponents();
        Date date = new Date();
        SimpleDateFormat spdateformat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        String strdate = spdateformat.format(date);
        lbDate.setText(strdate);
        initModels();
        this.staffname = InforStaff.staffname;
        this.staffid = InforStaff.idCurrentUser;
        lbStaffName.setText(staffname);
        lbStaffId.setText(String.valueOf(staffid));
        //order
        getpurchaseorder = new GetPurchaseOrder(new GetPurchaseOrder.IStateGetPurchaseOrder() {
            @Override
            public void onStart() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onEnd() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(int a) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                id =a;
            }

            @Override
            public void onError(String error) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        getpurchaseorder.getPurchaseOrder("SELECT MAX(PURCHASE_ORDER.PURCHASE_ORDER_ID) FROM PURCHASE_ORDER");
        lbSP.setText(String.valueOf(++id));
        setpurchaseorder = new SetPurchaseOrder(new SetPurchaseOrder.IStateSetPurchaseOrder() {
            @Override
            public void onStart() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onEnd() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            }

            @Override
            public void onSuccess(boolean a) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JOptionPane.showMessageDialog(null,"Thanh toán thành công !","THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void onError() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JOptionPane.showMessageDialog(null,"Không thể kết nối tới SERVER !","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        setdetailorder = new SetDetailOrder(new SetDetailOrder.IStateSetDetailOrder() {
            @Override
            public void onStart() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onEnd() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(boolean a) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JOptionPane.showMessageDialog(null,"Không thể kết nối tới SERVER !","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edtTenKH = new javax.swing.JTextField();
        edtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        spSoLuong = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        edtMaSanPham = new javax.swing.JTextField();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbStaffId = new javax.swing.JLabel();
        lbStaffName = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        lbSP = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lbThanhTien = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("HOÁ ĐƠN BÁN HÀNG");
        jLabel1.setToolTipText("");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Họ tên khách hàng :");

        edtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtTenKHActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Ngày lập :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Số phiếu :");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nhân viên :");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ID :");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Mã sản phẩm :");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Số lượng :");

        spSoLuong.setName(""); // NOI18N

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jInternalFrame1.setBorder(null);
        jInternalFrame1.setTitle("Thông Tin");
        jInternalFrame1.setAlignmentX(0.1F);
        jInternalFrame1.setAlignmentY(0.1F);
        jInternalFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jInternalFrame1.setDoubleBuffered(true);
        jInternalFrame1.setFocusTraversalPolicyProvider(true);
        try {
            jInternalFrame1.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        jInternalFrame1.setVisible(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lbStaffId.setText("12545666");

        lbStaffName.setText("Nguyễn Thế Đạt");

        lbDate.setText("pppp");
        lbDate.setToolTipText("");

        lbSP.setText("54215");
        lbSP.setToolTipText("");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Số điện thoại :");

        jButton2.setText("Thanh Toán");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Tổng cộng :");

        lbThanhTien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbStaffName, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(lbDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(edtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(edtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(spSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(12, 12, 12)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbStaffId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbSP, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(243, 243, 243))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(292, 292, 292))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lbStaffName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(edtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lbStaffId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lbSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(spSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jButton2)
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    DefaultTableModel model;
    private int row=0,ThanhTien=0;
    private ArrayList<SanPham> arrsp;
    private int id;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        getSPData.GetSanPhamData("SELECT * FROM PRODUCT");
        if(edtMaSanPham.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Bạn chưa nhập hàng hoá !","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(spSoLuong.getValue().hashCode()<1){
            JOptionPane.showMessageDialog(null,"Bạn chưa nhập số lượng !","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(int i=0;i<arrsp.size();i++){
            String str;
            str = String.valueOf(arrsp.get(i).GetID());
            if(str.equals(edtMaSanPham.getText())){
                model = (DefaultTableModel) jTable1.getModel();
                //kt logic sp
                for(int j=0;j<model.getRowCount();j++){
                    if(edtMaSanPham.getText().equals(model.getValueAt(j,1).toString())){
                        JOptionPane.showMessageDialog(null,"Sản phẩm đã có trong giỏ hàng !","THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                //add on table
                int sl;
                sl = Integer.parseInt(spSoLuong.getValue().toString());
                row++;
                ThanhTien +=  sl * arrsp.get(i).GetPrice();
                model.addRow(new Object[]{row,arrsp.get(i).GetID(),arrsp.get(i).GetName(),"cai",spSoLuong.getValue(),arrsp.get(i).GetPrice(),sl * arrsp.get(i).GetPrice()});
                lbThanhTien.setText(String.valueOf(ThanhTien));
                return;
                
            }
        }
        JOptionPane.showMessageDialog(null,"Mã sản phẩm không tồn tại !","ERROR",JOptionPane.ERROR_MESSAGE);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
   
    private void edtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtTenKHActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(edtTenKH.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên khách hàng !","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(edtSDT.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập sdt khách hàng !","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(jTable1.getRowCount()<=0){
            JOptionPane.showMessageDialog(null, "Chưa có dữ liệu !","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String str;
        str = String.format("insert into PURCHASE_ORDER(PURCHASE_ORDER_ID,STAFF_ID,CUSTOMER_NAME,CUSTOMER_PHONENUMBER) values(%s,%s,\'%s\',\'%s\')",id,staffid,edtTenKH.getText().toString(),edtSDT.getText().toString());
        setpurchaseorder.setPurchaseOrder(str);         
        for(int i=0;i<model.getRowCount();i++){
            str = String.format("insert into DETAIL_ORDER(PURCHASE_ORDER_ID,PRODUCT_ID,PRODUCT_QUANTITY,PRODUCT_TOTAL_MONEY) values(%s,%s,%s,%s)",id,Integer.parseInt(model.getValueAt(i,1).toString()),Integer.parseInt(model.getValueAt(i,4).toString()),Integer.parseInt(model.getValueAt(i,6).toString()));
            setdetailorder.setDetailOrder(str);
            
        }
        model.setRowCount(0);
        edtTenKH.setText("");
        edtSDT.setText("");
        edtMaSanPham.setText("");
        spSoLuong.setValue(0);
        lbSP.setText(String.valueOf(++id));
        
       
                
        
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField edtMaSanPham;
    private javax.swing.JTextField edtSDT;
    private javax.swing.JTextField edtTenKH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbSP;
    private javax.swing.JLabel lbStaffId;
    private javax.swing.JLabel lbStaffName;
    private javax.swing.JLabel lbThanhTien;
    private javax.swing.JSpinner spSoLuong;
    // End of variables declaration//GEN-END:variables

    private void initModels() {
        getSPData = new GetSanPhamData(new GetSanPhamData.IStateGetSanPham() {
            @Override
            public void onStart() {
               //view.show(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onEnd() {
               //view.onHide();
            }

            @Override
            public void onSuccess(ArrayList<SanPham> arr) {
                //To change body of generated methods, choose Tools | Templates.
                arrsp = new ArrayList();
                arrsp.addAll(arr);
            }

            @Override
            public void onError(String error) {
                JOptionPane.showMessageDialog(null,"Không thể kết nối tới SERVER !","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
