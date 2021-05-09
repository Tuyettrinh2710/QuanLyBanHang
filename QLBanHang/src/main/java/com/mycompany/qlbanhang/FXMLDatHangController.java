/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.SanPham;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.SanPhamService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLDatHangController implements Initializable {
    @FXML private TextField txtTimKiem;
    @FXML private TableView<SanPham> tbSanPham;
    @FXML private Button btXem;

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
        
        TableColumn colGiaBan = new TableColumn("Đơn giá");
        colGiaBan.setCellValueFactory(new PropertyValueFactory("donGiaBan"));
        
        TableColumn colAnh = new TableColumn("Ảnh");
        colAnh.setCellValueFactory(new PropertyValueFactory("anh"));
        
        TableColumn colLoai = new TableColumn("Loại sp");
        colLoai.setCellValueFactory(new PropertyValueFactory("loaiSP_id"));
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((obj) -> {
            Button btn = new Button("Đặt hàng");
            
            btn.setOnAction(evt -> {
                try {
                    TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                    SanPham s = (SanPham) cell.getTableRow().getItem();
                    Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLThongTinKH.fxml"));
                    Parent root = loader.load();
                    FXMLThongTinKHController controller = loader.getController();
                    controller.laySP(s);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbSanPham.getColumns().addAll(colId, colName, colSoLuong 
                        , colGiaBan, colAnh, colLoai, colAction);
    }
    
    public void xemHandle(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLXemSP.fxml"));
        Parent root = loader.load();
        FXMLXemSPController controller = loader.getController();
        SanPham s = tbSanPham.getSelectionModel().getSelectedItem();
        controller.load(s);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
