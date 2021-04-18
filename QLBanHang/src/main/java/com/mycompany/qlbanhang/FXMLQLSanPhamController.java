/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.LoaiSP;
import com.mycompany.pojo.SanPham;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.LoaiSPService;
import com.mycompany.service.SanPhamService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLQLSanPhamController implements Initializable {
    @FXML private GridPane ap;
    @FXML private TextField txtId;
    @FXML private TextField txtTen;
    @FXML private TextField txtSoLuong;
    @FXML private TextField txtGiaNhap;
    @FXML private TextField txtGiaBan;
    @FXML private TextField txtAnh;
    @FXML private ComboBox<LoaiSP> cbLoaiSP;
    @FXML private Button btUpload;
    @FXML private Button btThem;
    @FXML private Button btCapNhat;
    @FXML private ImageView imgV;
    @FXML private TableView<SanPham> tbSanPham;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn= JdbcUtils.getConn();
            LoaiSPService l = new LoaiSPService(conn);
            
            this.cbLoaiSP.setItems(FXCollections.observableList(l.getCates()));
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQLSanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
        loadSanPham("");
        
        this.tbSanPham.setRowFactory(obj -> {
            TableRow r = new TableRow();
            r.setOnMouseClicked(e -> {  
                try {
                    Connection conn = JdbcUtils.getConn();
                    LoaiSPService l = new LoaiSPService(conn);
                    SanPham s = this.tbSanPham.getSelectionModel().getSelectedItem();
                    txtId.setText(String.valueOf(s.getIdSP()));
                    txtTen.setText(s.getTenSP());
                    txtSoLuong.setText(String.valueOf(s.getSoLuong()));
                    txtGiaNhap.setText(s.getDonGiaNhap().toString());
                    txtGiaBan.setText(s.getDonGiaBan().toString());
                    txtAnh.setText(s.getAnh().toString());
                    cbLoaiSP.getSelectionModel().select(l.getCateById(s.getLoaiSP_id()));
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLQLSanPhamController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            return r;
        });
    }
    
    public void loadForm() {
        txtTen.setText("");
        txtSoLuong.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtAnh.setText("");
        imgV.setImage(null);
    }
    
     
    public void loadhandle(ActionEvent event) {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fc.getExtensionFilters().add(imageFilter);
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            imgV.setImage(img);
            txtAnh.setText(file.getAbsolutePath());
        }    
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
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((obj) -> {
            Button btn = new Button("Xóa");
            
            btn.setOnAction(evt -> {
                Utils.getBox("Bạn chắc chắn xóa không?", Alert.AlertType.CONFIRMATION)
                        .showAndWait().ifPresent(bt -> {
                                if (bt == ButtonType.OK) {
                                    try {
                                        TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                                        SanPham s = (SanPham) cell.getTableRow().getItem();
                                        
                                        Connection conn = JdbcUtils.getConn();
                                        SanPhamService sv = new SanPhamService(conn);
                                        
                                        if (sv.xoaSanPham(s.getIdSP())) {
                                            Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                                            loadSanPham("");
                                        } else
                                            Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                                        
                                        conn.close();
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                        Logger.getLogger(FXMLQLSanPhamController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                        });
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbSanPham.getColumns().addAll(colId, colName, colSoLuong, 
                        colGiaNhap, colGiaBan, colAnh, colLoai, colAction);
    }
    
    public void addHandle(ActionEvent e) throws IOException {
        Connection conn;
        try {
            conn = JdbcUtils.getConn();
            SanPhamService sv = new SanPhamService(conn);
            SanPham s = new SanPham();
            
            s.setTenSP(txtTen.getText());
            s.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            s.setDonGiaNhap(new BigDecimal(txtGiaNhap.getText()));
            s.setDonGiaBan(new BigDecimal(1800000));
            s.setAnh(Files.readAllBytes(new File(txtAnh.getText()).toPath()));
            s.setLoaiSP_id(this.cbLoaiSP.getSelectionModel().getSelectedItem().getIdLoaiSP());
            
            if (sv.themSanPham(s) == true){
                Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                loadSanPham("");
            } else
                Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQLSanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadForm();
    }
    
    public void updateHandle(ActionEvent e) throws IOException {
        try {
            Connection conn = JdbcUtils.getConn();
            SanPhamService sv = new SanPhamService(conn);
            SanPham s = new SanPham();
            
            s.setTenSP(txtTen.getText());
            s.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            s.setDonGiaNhap(new BigDecimal(txtGiaNhap.getText()));
            s.setDonGiaBan(new BigDecimal(1800000));
            s.setAnh(txtAnh.getText().getBytes());
            s.setLoaiSP_id(this.cbLoaiSP.getSelectionModel().getSelectedItem().getIdLoaiSP());
            
            s.setIdSP(Integer.parseInt(txtId.getText()));
            
            if (sv.capNhatSanPham(s) == true){
                Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                loadSanPham("");
            } else
                Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQLSanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadForm();
    }
}
