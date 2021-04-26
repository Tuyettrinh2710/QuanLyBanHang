/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.HoaDon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author CasMap
 */
public class HoaDonTester {
     private static Connection CONN;
    
    @BeforeAll
    public static void beforeAll(){
        try {
            CONN = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void afterALL() {
        if (CONN != null)
            try {
                CONN.close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void xoaHD() {
        try {
            HoaDonService c = new HoaDonService(CONN);
            Assertions.assertTrue(c.xoaHD(5));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDHTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void xoaHDKhongTonTai() {
        try {
            HoaDonService c = new HoaDonService(CONN);
            Assertions.assertFalse(c.xoaHD(0));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDHTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
