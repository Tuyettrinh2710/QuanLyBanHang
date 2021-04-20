package com.mycompany.qlbanhang;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable{
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
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
}
