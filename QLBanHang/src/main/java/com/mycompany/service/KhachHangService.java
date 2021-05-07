/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CasMap
 */
public class KhachHangService {
    private Connection conn;
    
    public KhachHangService(Connection conn) {
        this.conn = conn;
    }
    
    public KhachHang getKHById(String id) throws SQLException {
        String sql = "SELECT * FROM banhangdb.khachhang WHERE idKH = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, id);
        
        ResultSet r = stm.executeQuery();
        KhachHang k = null;
        while (r.next()) {
            k = new KhachHang();
            k.setMaKH(r.getString("idKH"));
            k.setTenKH(r.getString("tenKH"));
            k.setDiaChi(r.getString("diaChi"));
            k.setSdt(r.getString("dienThoai"));
        }
        return k;
    }
    
    public List<KhachHang> getKhachhang() throws SQLException {
        String sql = "SELECT * FROM banhangdb.khachhang";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        ResultSet r = stm.executeQuery();
        List<KhachHang> ks = new ArrayList<>();
        while (r.next()) {
            KhachHang k = new KhachHang();
            k.setMaKH(r.getString("idKH"));
            k.setTenKH(r.getString("tenKH"));
            k.setDiaChi(r.getString("diaChi"));
            k.setSdt(r.getString("dienThoai"));
            ks.add(k);
        }
        return ks;
    }
    
    public boolean themKH(KhachHang k) throws SQLException {
        String sql = "INSERT INTO banhangdb.khachhang(idKH, tenKH, diaChi, dienThoai) "
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, k.getMaKH());
        stm.setString(2, k.getTenKH());
        stm.setString(3, k.getDiaChi());
        stm.setString(4, k.getSdt());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
    
    public boolean kiemTraIdTonTai(String id) throws SQLException {
        String sql = "SELECT * FROM banhangdb.khachhang WHERE idKH = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, id);
        
        ResultSet r = stm.executeQuery();
        if (r.next()) {
            return true;
        }
        return false;
    }
}
