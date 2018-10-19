/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import com.sun.xml.internal.ws.util.StringUtils;
import common.Constant;
import common.NumberUtils;
import data.GetLoaiSanPhamData;
import data.GetSanPhamData;
import data.GetThuongHieuData;
import data.InsertSanPham;
import data.UpdateSanPham;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.LoaiSanPham;
import models.SanPham;
import models.ThuongHieu;
import services.ConnectSQLServer;

/**
 *
 * @author admin
 */
public class SanPhamTab extends javax.swing.JPanel {

    private GetLoaiSanPhamData getLoaiSanPhamData;
    private GetSanPhamData getsp;
    private GetThuongHieuData getThuongHieu;
    private InsertSanPham insertSanPham;
    private UpdateSanPham updateSanPham;
    private int indexSelected = -1;
    /**
     * 
     */
    private ArrayList<SanPham> arrsp;
    private ArrayList<LoaiSanPham> arrlsp;
    private ArrayList<ThuongHieu> arrth;

    public SanPhamTab() {
        initComponents();
        updateSanPham = new UpdateSanPham(new UpdateSanPham.IStateInsertSanPham() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(SanPham sp) {
                System.out.println("Cap nhat thanh cong");
                JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công ", "Succesfull", JOptionPane.INFORMATION_MESSAGE);
                int index = -1;
                for (int i = 0; i < arrsp.size(); i++) {
                    if (arrsp.get(i).GetID() == sp.GetID()) {
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    return;
                }
                arrsp.set(index, sp);
                showSanPham();
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
                JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại ", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        });

        insertSanPham = new InsertSanPham(new InsertSanPham.IStateInsertSanPham() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(SanPham sp) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công ", "Succesfull", JOptionPane.INFORMATION_MESSAGE);
                getsp.GetSanPhamData(Constant.QUERY_SAN_PHAM);
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        arrlsp = new ArrayList<>();
        getLoaiSanPhamData = new GetLoaiSanPhamData(new GetLoaiSanPhamData.IStateGetLoaiSanPham() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(ArrayList<LoaiSanPham> arr) {
                for (LoaiSanPham loai : arr) {
                    cbLoaiSanPham.addItem(loai.getName());
                }
                arrlsp.addAll(arr);
            }

            @Override
            public void onError(String error) {
            }
        });
        getLoaiSanPhamData.GetLoaiSanPhamData("select * from PRODUCT_TYPE");

        arrth = new ArrayList<>();
        getThuongHieu = new GetThuongHieuData(new GetThuongHieuData.IStateGetThuongHieu() {
            @Override
            public void onStart() {

            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(ArrayList<ThuongHieu> arr) {
                for (ThuongHieu value : arr) {
                    cbThuongHieu.addItem(value.getName());
                }
                arrth.addAll(arr);

            }

            @Override
            public void onError(String error) {

            }
        });
        getThuongHieu.getThuongHieuData("select * from TRADEMARK");

        arrsp = new ArrayList();
        getsp = new GetSanPhamData(new GetSanPhamData.IStateGetSanPham() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(ArrayList<SanPham> arr) {
                System.out.println(".onSuccess()");
                arrsp.clear();
                arrsp.addAll(arr);
                showSanPham();
            }

            @Override
            public void onError(String error) {
            }

        });
        getsp.GetSanPhamData("select * from PRODUCT");

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                indexSelected = jTable1.getSelectedRow();
                showSanPham(indexSelected);
            }

        });
    }

    private int getIndexFromLoaiSanPham(int ID) {
        for (int i = 0; i < arrlsp.size(); i++) {
            if (arrlsp.get(i).getID() == ID) {
                return i;
            }
        }
        return 0;
    }

    private int getIndexFromThuongHieu(int ID) {
        for (int i = 0; i < arrth.size(); i++) {
            if (arrth.get(i).getID() == ID) {
                return i;
            }
        }
        return 0;
    }

    private void showSanPham(int index) {
        SanPham sp = arrsp.get(index);
        txtTenSanPham.setText(sp.GetName());
        txtDonGia.setText(String.valueOf(sp.GetPrice()));
        txtSoLuong.setText(String.valueOf(sp.GetQuantity()));
        cbLoaiSanPham.setSelectedIndex(getIndexFromLoaiSanPham(sp.GetTypeID()));
        cbThuongHieu.setSelectedIndex(getIndexFromThuongHieu(sp.GetTradeMarkID()));

        if (sp.getState() == 1) {
            rbHoatDong.setSelected(true);
        } else {
            rbNgungHoatDong.setSelected(true);
        }

    }

    private String getTenLoaiSanPham(int GetTypeID) {
        System.out.println(GetTypeID + "");
        for (LoaiSanPham value : arrlsp) {
            if (value.getID() == GetTypeID) {
                return value.getName();
            }

        }

        return "";

    }

    private String getTenThuongHieu(int GetThuongHieu) {
        System.out.println(GetThuongHieu + " ");
        for (ThuongHieu value : arrth) {
            if (value.getID() == GetThuongHieu) {
                return value.getName();
            }
        }
        return "";
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGState = new javax.swing.ButtonGroup();
        edtTenSanPham = new javax.swing.JLabel();
        edtGiaMotSanPham = new javax.swing.JLabel();
        edtSoLuong = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        edtTenSanPham1 = new javax.swing.JLabel();
        edtTenSanPham2 = new javax.swing.JLabel();
        btnLamMoi = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        cbLoaiSanPham = new javax.swing.JComboBox<>();
        cbThuongHieu = new javax.swing.JComboBox<>();
        edtTenSanPham3 = new javax.swing.JLabel();
        rbHoatDong = new javax.swing.JRadioButton();
        rbNgungHoatDong = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        edtTenSanPham.setText("Tên sản phẩm :");

        edtGiaMotSanPham.setText("Đơn giá :");

        edtSoLuong.setText("Số lượng");

        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Chi tiết nhập sản phẩm");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Sản Phẩm", "Mã Sản Phẩm", "Đơn Giá", "Số Lượng", "Loại Sản Phẩm", "Thương Hiệu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        edtTenSanPham1.setText("Loại sản phẩm :");

        edtTenSanPham2.setText("Thương hiệu:");

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        cbLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiSanPhamActionPerformed(evt);
            }
        });

        cbThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbThuongHieuActionPerformed(evt);
            }
        });

        edtTenSanPham3.setText("Trạng thái");

        btnGState.add(rbHoatDong);
        rbHoatDong.setSelected(true);
        rbHoatDong.setText("Hoạt động");
        rbHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbHoatDongActionPerformed(evt);
            }
        });

        btnGState.add(rbNgungHoatDong);
        rbNgungHoatDong.setText("Ngừng hoạt động");
        rbNgungHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNgungHoatDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtTenSanPham2)
                            .addComponent(edtTenSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtTenSanPham3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbLoaiSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbThuongHieu, 0, 122, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbHoatDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(47, 47, 47))
                            .addComponent(rbNgungHoatDong))
                        .addGap(513, 513, 513))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edtTenSanPham, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtGiaMotSanPham)
                                    .addComponent(edtSoLuong))))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(154, 154, 154))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(edtTenSanPham)
                    .addComponent(btnSua))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(edtGiaMotSanPham)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtSoLuong)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtTenSanPham1)
                    .addComponent(cbLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtTenSanPham2)
                    .addComponent(cbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtTenSanPham3)
                    .addComponent(rbHoatDong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbNgungHoatDong)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (indexSelected == -1) {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm nào để sửa?", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isThem = checkForm();
        if (!isThem) {
            return;
        }
        SanPham sp = arrsp.get(indexSelected);
        sp.SetName(txtTenSanPham.getText());
        sp.SetPrice(Integer.parseInt(txtDonGia.getText()));
        sp.SetQuantity(Integer.parseInt(txtSoLuong.getText()));
        sp.SetTypeID(getIdLoaiSanPhamFromCombox());
        sp.SetTradeMarkID(getIdThuongHieuFromCombox());
        if (rbHoatDong.isSelected()) {
            sp.setState(1);
        } else {
            sp.setState(0);
        }
        System.out.println("screens.SanPhamTab.btnSuaActionPerformed()");
        updateSanPham.updateSanPham(sp);

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        resetForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void resetForm() {
        txtTenSanPham.setText(" ");
        txtDonGia.setText(" ");
        txtSoLuong.setText(" ");
        cbLoaiSanPham.setSelectedIndex(0);
        cbThuongHieu.setSelectedIndex(0);
        rbHoatDong.setSelected(true);
        indexSelected = -1;
    }

    private boolean checkForm() {
        String soLuong = txtSoLuong.getText().toString().trim();
        String donGia = txtDonGia.getText().toString().trim();
        // kiem tra logic
        if (txtTenSanPham.getText().trim().isEmpty()) {//tensp
            JOptionPane.showMessageDialog(null, "Chưa nhập tên sản phẩm ", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtDonGia.getText().trim().isEmpty()) {//dongia
            JOptionPane.showMessageDialog(null, "Chưa nhập đơn giá ", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {//soluong
            JOptionPane.showMessageDialog(null, "Chưa nhập số lượng", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!NumberUtils.isNumeric(soLuong)) {
            JOptionPane.showMessageDialog(null, "Nhập sai số lượng  ", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!NumberUtils.isNumeric(donGia)) {
            JOptionPane.showMessageDialog(null, "Nhập sai đơn giá  ", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private int getIdLoaiSanPhamFromCombox() {
        for (LoaiSanPham sp : arrlsp) {
            if (cbLoaiSanPham.getItemAt(cbLoaiSanPham.getSelectedIndex()).equalsIgnoreCase(sp.getName())) {
                return sp.getID();
            }
        }
        return 1;
    }

    private int getIdThuongHieuFromCombox() {
        for (ThuongHieu sp : arrth) {
            if (cbThuongHieu.getItemAt(cbThuongHieu.getSelectedIndex()).equalsIgnoreCase(sp.getName())) {
                return sp.getID();
            }
        }
        return 1;
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        boolean isThem = checkForm();
        if (indexSelected != -1) {
            JOptionPane.showMessageDialog(null, "Ban dang sua san pham! khong them duoc", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isThem) {
            return;
        }
        SanPham sp = new SanPham();
        sp.SetName(txtTenSanPham.getText().trim());
        sp.SetPrice(Integer.parseInt(txtDonGia.getText().trim()));
        sp.SetQuantity(Integer.parseInt(txtSoLuong.getText().trim()));
        sp.SetTypeID(getIdLoaiSanPhamFromCombox());
        sp.SetTradeMarkID(getIdThuongHieuFromCombox());
        if (rbHoatDong.isSelected()) {
            sp.setState(1);
        } else {
            sp.setState(0);
        }
        insertSanPham.insertSanPham(sp);
        // them database
        //them vao table

    }//GEN-LAST:event_btnThemActionPerformed
    private Vector createVector(int index, SanPham sp) {
        Vector v = new Vector<String>();
        String thuongHieu;
        String loaiSanPham;
        loaiSanPham = getTenLoaiSanPham(sp.GetTypeID());
        thuongHieu = getTenThuongHieu(sp.GetTradeMarkID());
        v.add(index + "");
        v.add(sp.GetName());
        v.add(sp.GetID() + "");
        v.add(sp.GetPrice() + "");
        v.add(sp.GetQuantity() + "");
        v.add(loaiSanPham);
        v.add(thuongHieu);
        v.add(sp.getState() == 1 ? "Đang bán" : "Ngừng kinh doanh");
        return v;
    }

    public void showSanPham() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (model.getRowCount() > 0 && indexSelected != -1) {
            model.getDataVector().set(indexSelected, createVector(indexSelected + 1, arrsp.get(indexSelected)));
            model.fireTableRowsUpdated(indexSelected, indexSelected);
            return;
        }
        model.getDataVector().removeAllElements();
        if (model.getRowCount()== 0){
            for (int i = 0; i < arrsp.size(); i++) {
                model.addRow(createVector(i + 1, arrsp.get(i)));
            }
            return;
        }
       

    }


    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed

    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void cbThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbThuongHieuActionPerformed

    private void cbLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLoaiSanPhamActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void rbNgungHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNgungHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNgungHoatDongActionPerformed

    private void rbHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbHoatDongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGState;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbLoaiSanPham;
    private javax.swing.JComboBox<String> cbThuongHieu;
    private javax.swing.JLabel edtGiaMotSanPham;
    private javax.swing.JLabel edtSoLuong;
    private javax.swing.JLabel edtTenSanPham;
    private javax.swing.JLabel edtTenSanPham1;
    private javax.swing.JLabel edtTenSanPham2;
    private javax.swing.JLabel edtTenSanPham3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rbHoatDong;
    private javax.swing.JRadioButton rbNgungHoatDong;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables

}
