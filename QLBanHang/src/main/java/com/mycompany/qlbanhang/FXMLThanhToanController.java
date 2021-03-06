/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import com.mycompany.pojo.ChiTietHD;
import com.mycompany.pojo.HoaDon;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.SanPham;
import com.mycompany.service.ChiTietHDService;
import com.mycompany.service.HoaDonService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.SanPhamService;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
public class FXMLThanhToanController implements Initializable {
    @FXML private TextField txtMaHD;
    @FXML private DatePicker dpNgay;
    @FXML private TextField txtMaNV;
    @FXML private TextField txtTenNV;
    @FXML private TextField txtMaKH;
    @FXML private TextField txtTenKH;
    @FXML private TextField txtDiaChi;
    @FXML private TextField txtSDT;
    @FXML private TextField txtMaSP;
    @FXML private TextField txtTenSP;
    @FXML private TextField txtDonGia;
    @FXML private TextField txtSoLuong;
    @FXML private TextField txtGiamGia;
    @FXML private TextField txtThanhTien;
    @FXML private TextField txtTongTien;
    @FXML private Button btTaoHD;
    @FXML private Button btThem;
    @FXML private TableView<ChiTietHD> tbChiTietHD;
    @FXML private Button btHuy;
    @FXML private Button btThanhToan;
    private List<ChiTietHD> l = new ArrayList<ChiTietHD>();
    private BigDecimal tong = new BigDecimal(0);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       dpNgay.setValue(LocalDate.now());
       txtMaKH.setText("");
       txtThanhTien.setText("0");
       txtGiamGia.setText("0.0");
       txtTongTien.setText("0");
       txtMaKH.textProperty().addListener(obj -> {
           try {
               Connection conn = JdbcUtils.getConn();
               KhachHangService ks = new KhachHangService(conn);
               KhachHang k = ks.getKHById(txtMaKH.getText());
               txtTenKH.setText(k.getTenKH());
               txtDiaChi.setText(k.getDiaChi());
               txtSDT.setText(k.getSdt());
               conn.close();
           } catch (SQLException ex) {
               Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       
       txtMaSP.textProperty().addListener(obj -> {
           try {
               Connection conn = JdbcUtils.getConn();
               SanPhamService sv = new SanPhamService(conn);
               if (txtMaSP.getText() == ""){
                    txtTenSP.setText("");
                    txtDonGia.setText("");
               } else if (sv.kiemTraIdSpTonTai(Integer.parseInt(txtMaSP.getText())) == true){
                    SanPham s = sv.laySanPhamById(Integer.parseInt(txtMaSP.getText()));
                    txtTenSP.setText(s.getTenSP());
                    txtDonGia.setText(String.valueOf(s.getDonGiaBan()));
               } else {
                   txtTenSP.setText("");
                    txtDonGia.setText("");
               }
               conn.close();
           } catch (SQLException ex) {
               Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       
       txtSoLuong.textProperty().addListener(obj -> {
           BigDecimal dg, tt, sl;
           float gg = 0.f;
           sl = new BigDecimal(txtSoLuong.getText());
           dg = new BigDecimal(txtDonGia.getText());
           gg = Float.parseFloat(txtGiamGia.getText());
           BigDecimal tongTienChuaGiam = dg.multiply(sl);
           float giam = (float) (1.0 - gg);
           BigDecimal giamGia = new BigDecimal(Float.toString(giam));
           BigDecimal tongTien = tongTienChuaGiam.multiply(giamGia);
           
           txtThanhTien.setText(tongTien.toString());
       });
       
       txtGiamGia.textProperty().addListener(obj -> {
           BigDecimal dg, tt, sl;
           float gg = 0.f;
           sl = new BigDecimal(txtSoLuong.getText());
           dg = new BigDecimal(txtDonGia.getText());
           gg = Float.parseFloat(txtGiamGia.getText());
           BigDecimal tongTienChuaGiam = dg.multiply(sl);
           float giam = (float) (1.0 - gg);
           BigDecimal giamGia = new BigDecimal(Float.toString(giam));
           BigDecimal tongTien = tongTienChuaGiam.multiply(giamGia);
           
           txtThanhTien.setText(tongTien.toString());
       });
       loadTable();
//       loadChiTietHD();
       this.tbChiTietHD.setItems(FXCollections.observableList(l));
    }    
    
    public void loadNhanVien(String ma, String ten) {
        txtMaNV.setText(ma);
        txtTenNV.setText(ten);
    }
    
    public void loadChiTietHD() {
        this.tbChiTietHD.getItems().clear();
        try {
            Connection conn = JdbcUtils.getConn();
            ChiTietHDService c = new ChiTietHDService(conn);
            this.tbChiTietHD.setItems(FXCollections.observableList(c.getHoaDon()));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadSP() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtThanhTien.setText("0");
        txtGiamGia.setText("0.0");
    }
    
    public void loadForm() {
        txtMaHD.setText("");
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        loadSP();
        txtTongTien.setText("0");
    }
    
    public void loadTable() {
        TableColumn colId = new TableColumn("M?? SP");
        colId.setCellValueFactory(new PropertyValueFactory("maSp"));
        
        TableColumn colName = new TableColumn("T??n SP");
        colName.setCellValueFactory(new PropertyValueFactory("tenSP"));
        
        TableColumn colSoLuong = new TableColumn("S??? l?????ng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("sl"));
        
        TableColumn colDonGia = new TableColumn("????n gi??");
        colDonGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        
        TableColumn colGiamGia = new TableColumn("Gi???m gi??");
        colGiamGia.setCellValueFactory(new PropertyValueFactory("giamGia"));
        
        TableColumn colThanhTien = new TableColumn("Th??nh ti???n");
        colThanhTien.setCellValueFactory(new PropertyValueFactory("thanhTien"));
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((obj) -> {
            Button btn = new Button("X??a");
            
            btn.setOnAction(evt -> {
                Utils.getBox("B???n ch???c ch???n x??a kh??ng?", Alert.AlertType.CONFIRMATION)
                        .showAndWait().ifPresent(bt -> {
                                if (bt == ButtonType.OK) {
                                    try {
                                        TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                                        ChiTietHD c = (ChiTietHD) cell.getTableRow().getItem();
                                        
                                        Connection conn = JdbcUtils.getConn();
                                        ChiTietHDService cs = new ChiTietHDService(conn);
                                        
                                        if (cs.xoaChiTietSP(c.getMaHD(), c.getMaSp())) {
                                            Utils.getBox("X??a s???n ph???m kh???i h??a ????n th??nh c??ng", Alert.AlertType.INFORMATION).show();
//                                            BigDecimal giaTien = new BigDecimal(txtThanhTien.getText());
                                            BigDecimal tongTien = new BigDecimal(txtTongTien.getText());
                                            tong = tongTien.subtract(c.getThanhTien());
                                            txtTongTien.setText(tong.toString());
                                            l.remove(c);
                                        } else
                                            Utils.getBox("X??a s???n ph???m th???t b???i", Alert.AlertType.ERROR).show();
                                        
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
        
        this.tbChiTietHD.getColumns().addAll(colId, colName, colSoLuong, 
                        colDonGia, colGiamGia, colThanhTien, colAction);
    }
    
    public void toaHDHanle(ActionEvent e) {
        try {
            Connection conn = JdbcUtils.getConn();
            HoaDonService hs = new HoaDonService(conn);
            HoaDon h = new HoaDon();
            h.setMaNV(txtMaNV.getText());
            h.setMaKH(txtMaKH.getText());
            h.setNgayBan(new java.sql.Date(System.currentTimeMillis()));
            String s = String.valueOf(hs.layIdHoaDon(h));
            txtMaHD.setText(s);
            if (txtMaHD.equals("")){
                Utils.getBox("T???o h??a ????n th???t b???i", Alert.AlertType.ERROR).show();
            } else {
                txtMaKH.setEditable(false);
                btTaoHD.setDisable(true);
                Utils.getBox("T???o h??a ????n th??nh c??ng", Alert.AlertType.INFORMATION).show();
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void themHandle(ActionEvent e) {
        try {
            Connection conn = JdbcUtils.getConn();
            ChiTietHDService cs = new ChiTietHDService(conn);
            ChiTietHD c = new ChiTietHD();
            
            c.setMaHD(Integer.parseInt(txtMaHD.getText()));
            c.setMaSp(Integer.parseInt(txtMaSP.getText()));
            c.setTenSP(txtTenSP.getText());
            c.setDonGia(new BigDecimal(txtDonGia.getText()));
            c.setSl(Integer.parseInt(txtSoLuong.getText()));
            c.setGiamGia(Float.parseFloat(txtGiamGia.getText()));
            c.setThanhTien(new BigDecimal(txtThanhTien.getText()));
            
            if(cs.themChiTietDH(c) == true) {
                Utils.getBox("S???n ph???m ???????c th??m v??o chi ti???t ????n h??ng", Alert.AlertType.INFORMATION).show();
//                loadChiTietHD();
                BigDecimal giaTien = new BigDecimal(txtThanhTien.getText());
                BigDecimal tongTien = new BigDecimal(txtTongTien.getText());
                tong = tongTien.add(giaTien);
                txtTongTien.setText(tong.toString());
                l.add(c);
                this.tbChiTietHD.setItems(FXCollections.observableList(l));
                loadSP();
            }else
                Utils.getBox("Th??m s???n ph???m v??o chi ti???t ????n h??ng th???t b???i!!!!!!", Alert.AlertType.ERROR).show();
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void huyHandle(ActionEvent e) {
        try {
            Connection conn = JdbcUtils.getConn();
            ChiTietHDService cs = new ChiTietHDService(conn);
            HoaDonService hs = new HoaDonService(conn);
            if(cs.xoaChiTietDH(Integer.parseInt(txtMaHD.getText())) == true) {
                if (hs.xoaHD(Integer.parseInt(txtMaHD.getText())) == true) {
                    Utils.getBox("H???y ????n h??ng th??nh c??ng!!!!!!", Alert.AlertType.INFORMATION).show();
                    txtMaKH.setEditable(true);
                    btTaoHD.setDisable(false);
                    loadForm();
                    this.tbChiTietHD.getItems().clear();
                }
            } else if (hs.xoaHD(Integer.parseInt(txtMaHD.getText())) == true) {
                Utils.getBox("H???y ????n h??ng th??nh c??ng!!!!!!", Alert.AlertType.INFORMATION).show();
                txtMaKH.setEditable(true);
                btTaoHD.setDisable(false);
                loadForm();
                this.tbChiTietHD.getItems().clear();
            } else
                 Utils.getBox("H???y ????n h??ng th???t b???i!!!!!!", Alert.AlertType.ERROR).show();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void thanhToanHandle(ActionEvent e) {
        if (txtMaHD.getText().equals("")) {
            Utils.getBox("M?? h??a ????n tr???ng thanh to??n th???t b???i!!!!!!", Alert.AlertType.ERROR).show();
        } else if (l.isEmpty()) {
            try {
                Connection conn = JdbcUtils.getConn();
                HoaDonService hs = new HoaDonService(conn);
                if (hs.xoaHD(Integer.parseInt(txtMaHD.getText())) == true) {
                    Utils.getBox("????n h??ng kh??ng c?? s???n ph???m thanh to??n th???t b???i!!!!!", Alert.AlertType.ERROR).show();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Utils.getBox("Thanh to??n th??nh c??ng", Alert.AlertType.INFORMATION).show();
        }
        txtMaKH.setEditable(true);
        btTaoHD.setDisable(false);
        loadForm();
        tbChiTietHD.getItems().clear();
    }
}
