/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.User;
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
public class UserService {
    private Connection conn;
    
    public UserService(Connection conn) {
        this.conn = conn;
    }
    
//    public List<User> dangNhap(String user, String pass) throws SQLException {
//        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
//        PreparedStatement stm = this.conn.prepareStatement(sql);
//        stm.setString(1, user);
//        stm.setString(2, pass);
//     
//        ResultSet r = stm.executeQuery();
//        List<User> re= new ArrayList<>();
//        while (r.next()) {
//            User u = new User();
//            u.setUsername(r.getString("username"));
//            u.setPassword(r.getString("password"));
//            re.add(u);
//        }
//        return re;
//    }
    public boolean dangNhap(String user, String pass) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, user);
        stm.setString(2, pass);
     
        ResultSet r = stm.executeQuery();
        if (!r.next())
            return false;
        return true;
    }
}
