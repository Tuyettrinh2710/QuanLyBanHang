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
//
//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    public void dangNhapHandle(ActionEvent e) {
        try {
            Connection conn = JdbcUtils.getConn();
            UserService us = new UserService(conn);
            
            if (us.account(txtUsername.getText(), txtPassword.getText()) == true) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("FXMLTrangChu.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            } else {
                Utils.getBox("Username hoặc password không hợp lệ!!!!", Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
