/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.SanPham;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CasMap
 */
public class SanPhamService {
    private Connection conn;
    
    public SanPhamService(Connection conn) {
        this.conn = conn;
    }
    
    public List<SanPham> getSanPham() throws SQLException, UnsupportedEncodingException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM sanpham");
        
        List<SanPham> re = new ArrayList<>();
        while (r.next()) {
            SanPham s = new SanPham();
            s.setIdSP(r.getInt("idSP"));
            s.setTenSP(r.getString("tenSP"));
            s.setSoLuong(r.getInt("soLuong"));
            s.setDonGiaNhap(r.getBigDecimal("donGiaNhap"));
            s.setDonGiaBan(r.getBigDecimal("donGiaBan"));
            s.setAnh(r.getBytes("anh"));
            s.setLoaiSP_id(r.getInt("loaiSP_id"));
            
            re.add(s);
        }
        return re;
    }
    
    public boolean themSanPham(SanPham s) throws SQLException{
        String sql = "INSERT INTO sanpham(tenSP, soLuong, donGiaNhap, donGiaBan, anh, loaiSP_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, s.getTenSP());
        stm.setInt(2, s.getSoLuong());
        stm.setBigDecimal(3, s.getDonGiaNhap());
        stm.setBigDecimal(4, s.getDonGiaBan());
        stm.setBytes(5, s.getAnh());
        stm.setInt(6, s.getLoaiSP_id());
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean xoaSanPham(int sp) throws SQLException {
        String sql = "DELETE FROM banhangdb.sanpham WHERE idSP=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, sp);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
}
