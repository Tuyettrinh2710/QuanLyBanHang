/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.SanPham;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.css.converter.PaintConverter;
import javafx.css.converter.PaintConverter.ImagePatternConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author CasMap
 */
public class FXMLXemSPController implements Initializable {
    @FXML private Text txtMa;
    @FXML private Text txtTen;
    @FXML private Text txtSoLuong;
    @FXML private Text txtDonGia;
    @FXML private Text txtLoai;
    @FXML private ImageView imgAnh;
    private Image img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void load(SanPham s) {
        txtMa.setText(String.valueOf(s.getIdSP()));
        txtTen.setText(s.getTenSP());
        txtSoLuong.setText(String.valueOf(s.getSoLuong()));
        txtDonGia.setText(s.getDonGiaBan().toString());
        txtLoai.setText(String.valueOf(s.getLoaiSP_id()));
        InputStream is = new ByteArrayInputStream(s.getAnh());
        img = new Image(is);
        imgAnh.setImage(img);
    }
}
