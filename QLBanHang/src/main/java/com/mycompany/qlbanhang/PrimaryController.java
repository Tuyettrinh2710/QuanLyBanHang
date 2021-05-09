package com.mycompany.qlbanhang;

import com.mycompany.pojo.User;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.UserService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btDangNhap;
    @FXML private Button btTroVe;
//
//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    public void dangNhapHandle(ActionEvent e) throws IOException {
        try {
            Connection conn = JdbcUtils.getConn();
            UserService us = new UserService(conn);
            
            if (us.account(txtUsername.getText(), txtPassword.getText()) == true) {
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTrangChu.fxml"));
                Parent root = loader.load();
                FXMLTrangChuController controller = loader.getController();
                controller.nhanVien(txtUsername.getText());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Utils.getBox("Username hoặc password không hợp lệ!!!!", Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void troVeHandle(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBanHang.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
