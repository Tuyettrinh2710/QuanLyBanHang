/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author CasMap
 */
public class NhanVienService {
    private Connection conn;
    
    public NhanVienService(Connection conn) {
        this.conn = conn;
    }
    
    public List<NhanVien> getNhanVien() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM loaisp");
        
        List<NhanVien> re = new ArrayList<>();
        while (r.next()) {
            NhanVien c = new NhanVien();
            c.setMaNV(r.getString("idNV"));
            c.setTenNV(r.getString("tenNV"));
            c.setGioiTinh(r.getString("gioiTinh"));
            c.setDiaChi(r.getString("diaChi"));
            c.setSdt(r.getString("dienThoai"));
            c.setNgaySinh(r.getDate("ngaySinh"));
            
            re.add(c);
        }
        return re;
    }
    
    public NhanVien getIDTenNV(String username) throws SQLException {
        String sql = "SELECT idNV, tenNV from banhangdb.nhanvien, banhangdb.user where nhanvien.idNV = user.id AND username = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, username);
        
        ResultSet r = stm.executeQuery();
        NhanVien n = null;
        
        while (r.next()) {
            n = new NhanVien();
            n.setMaNV(r.getString("idNV"));
            n.setTenNV(r.getString("tenNV"));
        }
        return n;
    } 
}
