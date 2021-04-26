/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.ChiTietHD;
import com.mycompany.pojo.HoaDon;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.SanPham;
import com.mycompany.service.ChiTietHDService;
import com.mycompany.service.HoaDonService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.SanPhamService;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


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
    @FXML private Button btThem;
    @FXML private TableView<ChiTietHD> tbChiTietHD;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       dpNgay.setValue(LocalDate.now());
       txtMaKH.setText("");
       txtThanhTien.setText("0");
       txtGiamGia.setText("0.0");
       txtTongTien.setText("0");
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
       
       txtMaSP.textProperty().addListener(obj -> {
           try {
               if (txtMaSP.getText() == ""){
                    txtTenSP.setText("");
                    txtDonGia.setText("");
               } else {
                    Connection conn = JdbcUtils.getConn();
                    SanPhamService sv = new SanPhamService(conn);
                    SanPham s = sv.laySanPhamById(Integer.parseInt(txtMaSP.getText()));
                    txtTenSP.setText(s.getTenSP());
                    txtDonGia.setText(String.valueOf(s.getDonGiaBan()));
                    conn.close();
               }
           } catch (SQLException ex) {
               Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       
       txtSoLuong.textProperty().addListener(obj -> {
           BigDecimal dg, tt, sl;
           float gg = 0.f;
           sl = new BigDecimal(txtSoLuong.getText());
           dg = new BigDecimal(txtDonGia.getText());
           gg = Float.parseFloat(txtGiamGia.getText());
           BigDecimal tongTienChuaGiam = dg.multiply(sl);
           float giam = (float) (1.0 - gg);
           BigDecimal giamGia = new BigDecimal(Float.toString(giam));
           BigDecimal tongTien = tongTienChuaGiam.multiply(giamGia);
           
           txtThanhTien.setText(tongTien.toString());
       });
       
       txtGiamGia.textProperty().addListener(obj -> {
           BigDecimal dg, tt, sl;
           float gg = 0.f;
           sl = new BigDecimal(txtSoLuong.getText());
           dg = new BigDecimal(txtDonGia.getText());
           gg = Float.parseFloat(txtGiamGia.getText());
           BigDecimal tongTienChuaGiam = dg.multiply(sl);
           float giam = (float) (1.0 - gg);
           BigDecimal giamGia = new BigDecimal(Float.toString(giam));
           BigDecimal tongTien = tongTienChuaGiam.multiply(giamGia);
           
           txtThanhTien.setText(tongTien.toString());
       });
       loadTable();
       loadChiTietHD();
    }    
    
    public void loadNhanVien(String ma, String ten) {
        txtMaNV.setText(ma);
        txtTenNV.setText(ten);
    }
    
    public void loadChiTietHD() {
        this.tbChiTietHD.getItems().clear();
        try {
            Connection conn = JdbcUtils.getConn();
            ChiTietHDService c = new ChiTietHDService(conn);
            this.tbChiTietHD.setItems(FXCollections.observableList(c.getHoaDon()));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void loadTable() {
        TableColumn colId = new TableColumn("Mã SP");
        colId.setCellValueFactory(new PropertyValueFactory("maSp"));
        
        TableColumn colName = new TableColumn("Tên SP");
        colName.setCellValueFactory(new PropertyValueFactory("tenSP"));
        
        TableColumn colSoLuong = new TableColumn("Số lượng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("sl"));
        
        TableColumn colDonGia = new TableColumn("Đơn giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        
        TableColumn colGiamGia = new TableColumn("Giảm giá");
        colGiamGia.setCellValueFactory(new PropertyValueFactory("giamGia"));
        
        TableColumn colThanhTien = new TableColumn("Thành tiền");
        colThanhTien.setCellValueFactory(new PropertyValueFactory("thanhTien"));
        
        this.tbChiTietHD.getColumns().addAll(colId, colName, colSoLuong, 
                        colDonGia, colGiamGia, colThanhTien);
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

            if (txtMaHD.equals("")){
                Utils.getBox("Tạo hóa đơn thất bại", Alert.AlertType.ERROR).show();
            } else
                Utils.getBox("Tạo hóa đơn thành công", Alert.AlertType.INFORMATION).show();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void themHandle(ActionEvent e) {
        try {
            Connection conn = JdbcUtils.getConn();
            ChiTietHDService cs = new ChiTietHDService(conn);
            ChiTietHD c = new ChiTietHD();
            
            c.setMaHD(Integer.parseInt(txtMaHD.getText()));
            c.setMaSp(Integer.parseInt(txtMaSP.getText()));
            c.setTenSP(txtTenSP.getText());
            c.setDonGia(new BigDecimal(txtDonGia.getText()));
            c.setSl(Integer.parseInt(txtSoLuong.getText()));
            c.setGiamGia(Float.parseFloat(txtGiamGia.getText()));
            c.setThanhTien(new BigDecimal(txtThanhTien.getText()));
            
            if(cs.themChiTietDH(c) == true) {
                Utils.getBox("Sản phẩm được thêm vào chi tiết đơn hàng", Alert.AlertType.INFORMATION).show();
                loadChiTietHD();
            }else
                Utils.getBox("Thêm sản phẩm vào chi tiết đơn hàng thất bại!!!!!!", Alert.AlertType.ERROR).show();
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
