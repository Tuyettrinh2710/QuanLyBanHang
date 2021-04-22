/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.SanPham;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.SanPhamService;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLTimKiemController implements Initializable {
    @FXML private TextField txtTimKiem;
    @FXML private TableView<SanPham> tbSanPham;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTable();
        loadSanPham("");
        this.txtTimKiem.textProperty().addListener((obj) -> {
            loadSanPham(this.txtTimKiem.getText());
        });
    } 
    
    public void loadSanPham(String kw){
        this.tbSanPham.getItems().clear();
        try {
            Connection conn = JdbcUtils.getConn();
            SanPhamService s = new SanPhamService(conn);
            this.tbSanPham.setItems(FXCollections.observableList(s.getSanPhams(kw)));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(FXMLQLSanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private void loadTable() {
        TableColumn colId = new TableColumn("Mã SP");
        colId.setCellValueFactory(new PropertyValueFactory("idSP"));
        
        TableColumn colName = new TableColumn("Tên SP");
        colName.setCellValueFactory(new PropertyValueFactory("tenSP"));
        
        TableColumn colSoLuong = new TableColumn("Số lượng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        
        TableColumn colGiaNhap = new TableColumn("Giá nhập");
        colGiaNhap.setCellValueFactory(new PropertyValueFactory("donGiaNhap"));
        
        TableColumn colGiaBan = new TableColumn("Giá bán");
        colGiaBan.setCellValueFactory(new PropertyValueFactory("donGiaBan"));
        
        TableColumn colAnh = new TableColumn("Ảnh");
        colAnh.setCellValueFactory(new PropertyValueFactory("anh"));
        
        TableColumn colLoai = new TableColumn("Loại sp");
        colLoai.setCellValueFactory(new PropertyValueFactory("loaiSP_id"));
   
        this.tbSanPham.getColumns().addAll(colId, colName, colSoLuong, 
                        colGiaNhap, colGiaBan, colAnh, colLoai);
    }
}
