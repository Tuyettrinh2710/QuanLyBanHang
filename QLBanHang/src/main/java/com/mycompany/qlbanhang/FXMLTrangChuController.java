/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.NhanVien;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.NhanVienService;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLTrangChuController implements Initializable {
    @FXML private Button btQlsp;
    @FXML private Button btTimKiem;
    @FXML private Button btThanhToan;
    @FXML private Text txtMa;
    @FXML private Text txtTen;
    @FXML private Button btDangXuat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void nhanVien(String user) {
        try {
            Connection conn = JdbcUtils.getConn();
            NhanVienService ns = new NhanVienService(conn);
            NhanVien n = ns.getIDTenNV(user);
            txtMa.setText(n.getMaNV());
            txtTen.setText(n.getTenNV());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void qlSPHandle(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLQLSanPham.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void timKiemHandle(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLTimKiem.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void thanhToan(ActionEvent e) throws IOException {
//         Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLThanhToan.fxml"));
        Parent root = loader.load();
        FXMLThanhToanController controller = loader.getController();
        controller.loadNhanVien(txtMa.getText(), txtTen.getText());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    public void dangXuat(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
