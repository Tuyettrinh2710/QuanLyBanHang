/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.ChiTietHD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CasMap
 */
public class ChiTietHDService {
    private Connection conn;
    
    public ChiTietHDService(Connection conn) {
        this.conn = conn;
    }
    
    public List<ChiTietHD> getHoaDon() throws SQLException {
        String sql = "SELECT * FROM banhangdb.chitiethd";
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery(sql);
       
        List<ChiTietHD> chiTietHD = new ArrayList<>();
        while (r.next()) {
            ChiTietHD c = new ChiTietHD();
            c.setMaHD(r.getInt("hd_id"));
            c.setMaSp(r.getInt("sp_id"));
            c.setTenSP(r.getString("tenSP"));
            c.setSl(r.getInt("soLuong"));
            c.setDonGia(r.getBigDecimal("donGia"));
            c.setGiamGia(r.getFloat("giamGia"));
            c.setThanhTien(r.getBigDecimal("thanhTien"));
            chiTietHD.add(c);
        }
        return chiTietHD;
    }
    
    public boolean themChiTietDH(ChiTietHD c) throws SQLException {
        String sql ="INSERT INTO banhangdb.chitiethd(hd_id, sp_id, tenSP, soLuong, donGia, giamGia, thanhTien) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, c.getMaHD());
        stm.setInt(2, c.getMaSp());
        stm.setString(3, c.getTenSP());
        stm.setInt(4, c.getSl());
        stm.setBigDecimal(5, c.getDonGia());
        stm.setFloat(6, c.getGiamGia());
        stm.setBigDecimal(7, c.getThanhTien());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
}
