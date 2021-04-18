/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.LoaiSP;
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
public class LoaiSPService {
    private Connection conn;
    
    public LoaiSPService(Connection conn) {
        this.conn = conn;
    }
    public List<LoaiSP> getCates() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM loaisp");
        
        List<LoaiSP> re = new ArrayList<>();
        while (r.next()) {
            LoaiSP c = new LoaiSP();
            c.setIdLoaiSP(r.getInt("idLoaiSP"));
            c.setTenLoaiSP(r.getString("tenLoaiSP"));
            
            re.add(c);
        }
        return re;
    }
    
    public LoaiSP getCateById(int id) throws SQLException {
        String sql = "SELECT * FROM loaisp WHERE idLoaiSP=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        LoaiSP c = null;
        while (rs.next()) {
            c = new LoaiSP();
            
            c.setIdLoaiSP(rs.getInt("idLoaiSP"));
            c.setTenLoaiSP(rs.getString("tenLoaiSP"));
        }
        
        return c;
    }
}
