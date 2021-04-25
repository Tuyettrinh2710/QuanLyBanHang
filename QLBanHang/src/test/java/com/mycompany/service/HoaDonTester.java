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
    public void ThemHD() throws SQLException {
        HoaDonService hs = new HoaDonService(CONN);
        HoaDon h = new HoaDon();
        h.setMaNV("NV02");
        h.setMaKH("KH01");
        h.setNgayBan(new java.sql.Date(System.currentTimeMillis()));
        
        Assertions.assertTrue(hs.layHoaDon(h));
    }
    
//    @Test
//    public void testTimeout() {
//         try {
//             HoaDon h = new HoaDon();
//             h.setMaNV("NV02");
//             h.setMaKH("KH02");
//             SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd");
//             h.setNgayBan((Date) d.parse("2020-04-24"));
//             Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
//                 new HoaDonService(CONN).layIdHoaDon(h);
//             });
//         } catch (ParseException ex) {
//             Logger.getLogger(HoaDonTester.class.getName()).log(Level.SEVERE, null, ex);
//         }
//    }
}
