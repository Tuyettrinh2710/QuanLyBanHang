/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.DonHang;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.SanPham;
import com.mycompany.service.DonHangService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhachHangService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLXacNhanDHController implements Initializable {
    @FXML private Text txtTen;
    @FXML private Text txtDiaChi;
    @FXML private Text txtSDT;
    @FXML private Text txtSanPham;
    @FXML private Text txtDonGia;
    @FXML private Text txtThanhTien;
    @FXML private Button btXacNhan;
    @FXML private Button btHuy;
    @FXML private Button btTroVe;
    private SanPham s = new SanPham();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void loadKH(String ten, String diaChi, String sdt) {
        txtTen.setText(ten);
        txtDiaChi.setText(diaChi);
        txtSDT.setText(sdt);
    }
    
    public void loadSP(SanPham s) {
        txtSanPham.setText(s.getTenSP());
        txtDonGia.setText(s.getDonGiaBan().toString());
        txtThanhTien.setText(s.getDonGiaBan().toString());
        this.s = s;
    }
    
    public void huyHandle(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDatHang.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void themDH(Connection conn) {
        try {
            DonHang d = new DonHang();
            d.setKh_id(txtSDT.getText());
            d.setSp_id(s.getIdSP());
            d.setDonGia(new BigDecimal(txtDonGia.getText()));
            d.setTinhTrang("Chưa giao");
            DonHangService ds = new DonHangService(conn);
            if (ds.themDH(d) == true) {
                Utils.getBox("Đặt hàng thành công!!!!!", Alert.AlertType.INFORMATION).show();
                btXacNhan.setDisable(true);
                btHuy.setDisable(true);
                btTroVe.setVisible(true);
                
            } else {
                Utils.getBox("Đặt hàng thất bại!!!!!!", Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLXacNhanDHController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void xacNhanHandle(ActionEvent e) {
        try {
            Connection conn = JdbcUtils.getConn();
            KhachHangService ks = new KhachHangService(conn);
            
            if (ks.kiemTraIdTonTai(txtSDT.getText())) {
                themDH(conn);
            } else {
                KhachHang k = new KhachHang();
                k.setMaKH(txtSDT.getText());
                k.setTenKH(txtTen.getText());
                k.setDiaChi(txtDiaChi.getText());
                k.setSdt(txtSDT.getText());

                if(ks.themKH(k) == true) {
                    themDH(conn);
                } else {
                    Utils.getBox("Thông tin khách hàng không hợp lệ!!!", Alert.AlertType.ERROR).show();
                }
            }
            conn.close();
        }catch (SQLException ex) {
            Logger.getLogger(FXMLXacNhanDHController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void troVeHandle(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDatHang.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
