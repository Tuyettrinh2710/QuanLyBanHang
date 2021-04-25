/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.HoaDon;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.SanPham;
import com.mycompany.service.HoaDonService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.SanPhamService;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLThanhToanController implements Initializable {
    @FXML private TextField txtMaHD;
    @FXML private DatePicker dpNgay;
    @FXML private TextField txtMaNV;
    @FXML private TextField txtTenNV;
    @FXML private TextField txtMaKH;
    @FXML private TextField txtTenKH;
    @FXML private TextField txtDiaChi;
    @FXML private TextField txtSDT;
    @FXML private TextField txtMaSP;
    @FXML private TextField txtTenSP;
    @FXML private TextField txtDonGia;
    @FXML private TextField txtSoLuong;
    @FXML private TextField txtGiamGia;
    @FXML private TextField txtThanhTien;
    @FXML private TextField txtTongTien;
    @FXML private Button btTaoHD;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       dpNgay.setValue(LocalDate.now());
       txtMaKH.textProperty().addListener(obj -> {
           try {
               Connection conn = JdbcUtils.getConn();
               KhachHangService ks = new KhachHangService(conn);
               KhachHang k = ks.getKHById(txtMaKH.getText());
               txtTenKH.setText(k.getTenKH());
               txtDiaChi.setText(k.getDiaChi());
               txtSDT.setText(k.getSdt());
               conn.close();
           } catch (SQLException ex) {
               Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
    }    
    
    public void loadNhanVien(String ma, String ten) {
        txtMaNV.setText(ma);
        txtTenNV.setText(ten);
    }
    
    public void toaHDHanle(ActionEvent e) {
        try {
            Connection conn = JdbcUtils.getConn();
            HoaDonService hs = new HoaDonService(conn);
            HoaDon h = new HoaDon();
            h.setMaNV(txtMaNV.getText());
            h.setMaKH(txtMaKH.getText());
            h.setNgayBan(new java.sql.Date(System.currentTimeMillis()));
            
            String s = String.valueOf(hs.layIdHoaDon(h));
            txtMaHD.setText(s);
//            if (hs.layHoaDon(h) == true){
//                Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
//            } else
//                Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
