/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.DonHang;
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
public class DonHangService {
    private Connection conn;
    
    public DonHangService(Connection conn) {
        this.conn = conn;
    }
    
    public List<DonHang> getDH() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM banhangdb.donhang");
        
        List<DonHang> l = new ArrayList<>();
        while (r.next()) {
            DonHang d = new DonHang();
            d.setIdDH(r.getInt("idDH"));
            d.setKh_id(r.getString("kh_id"));
            d.setSp_id(r.getInt("sp_id"));
            d.setDonGia(r.getBigDecimal("donGia"));
            d.setTinhTrang(r.getString("tinhTrang"));
            l.add(d);
        }
        return l;
    }
    
    public boolean themDH(DonHang d) throws SQLException {
        String sql ="INSERT INTO banhangdb.donhang(kh_id, sp_id, donGia, tinhTrang) "
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, d.getKh_id());
        stm.setInt(2, d.getSp_id());
        stm.setBigDecimal(3, d.getDonGia());
        stm.setString(4, d.getTinhTrang());
        
        int row = stm.executeUpdate();
        return row > 0;
    }
}
