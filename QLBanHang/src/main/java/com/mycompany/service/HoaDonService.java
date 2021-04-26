/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CasMap
 */
public class HoaDonService {
    private Connection conn;
    
    public HoaDonService(Connection conn) {
        this.conn = conn;
    }
    
    public boolean layHoaDon(HoaDon h) throws SQLException {
        String sql = "INSERT INTO banhangdb.hoadon(nv_id, kh_id, ngayBan) VALUES (?, ? ,?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, h.getMaNV());
        stm.setString(2, h.getMaKH());
        stm.setDate(3, h.getNgayBan());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
    
    public int layIdHoaDon(HoaDon h) throws SQLException {
        int hoaDonId = 0;
        ResultSet rs = null;
        String sql = "INSERT INTO banhangdb.hoadon(nv_id, kh_id, ngayBan) VALUES (?, ? ,?)";
        PreparedStatement stm = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, h.getMaNV());
        stm.setString(2, h.getMaKH());
        stm.setDate(3, h.getNgayBan());
        
        int row = stm.executeUpdate();
        if (row == 1) {
            rs = stm.getGeneratedKeys();
            if (rs.next())
                hoaDonId = rs.getInt(1);
        }
        return hoaDonId;
    }
    
    public boolean xoaHD(int hd) throws SQLException {
        String sql = "DELETE FROM hoadon WHERE idHD = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, hd);
        int row = stm.executeUpdate();
        return row > 0;
    }
}
