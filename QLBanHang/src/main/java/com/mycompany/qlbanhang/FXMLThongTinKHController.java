/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.SanPham;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLThongTinKHController implements Initializable {
    @FXML private TextField txtTen;
    @FXML private TextField txtDiaChi;
    @FXML private TextField txtSdt;
    @FXML private Button btTiepTuc;
    @FXML private Button btTroVe;
    private SanPham s = new SanPham();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void laySP(SanPham s) {
        this.s = s;
    }
    
    public void backHandle(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDatHang.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void xacNhanHandle(ActionEvent e) throws IOException {
        if (txtSdt.getText().length() == 10) {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLXacNhanDH.fxml"));
            Parent root = loader.load();
            FXMLXacNhanDHController controller = loader.getController();
            controller.loadKH(txtTen.getText(), txtDiaChi.getText(), txtSdt.getText());
            controller.loadSP(s);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Số điện thoại không hợp lệ!!!!!");
            alert.showAndWait();
        } 
    }
}
