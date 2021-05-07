/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.KhachHang;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author CasMap
 */
public class KhachHangTester {
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
    public void testLayKHById() {
        try {
            int expected = 2;
            KhachHangService ks = new KhachHangService(CONN);
//            KhachHang k = ks.getKHById("KH01");
            List<KhachHang> kh = ks.getKhachhang();
            Assertions.assertTrue(kh.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new KhachHangService(CONN).getKHById("KH01");
        });
    }
    
//    @Test
//    public void them() {
//        try {
//            KhachHang k = new KhachHang();
//            k.setMaKH("0983763212");
//            k.setTenKH("Lê Mỹ Quyên");
//            k.setDiaChi("371 Nguyễn Kiệm, P.3, Q.Gò Vấp, TP.HCM");
//            k.setSdt("0983763212");
//            KhachHangService ks = new KhachHangService(CONN);
//            Assertions.assertTrue(ks.themKH(k));
//        } catch (SQLException ex) {
//            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    @Test
    public void themTrungIDKH() {
        try {
            KhachHang k = new KhachHang();
            k.setMaKH("KH02");
            k.setTenKH("sàdss");
            k.setDiaChi("371 Nguyễn Kiệm, P.3, Q.Gò Vấp, TP.HCM");
            k.setSdt("09837632121");
            KhachHangService ks = new KhachHangService(CONN);
            Assertions.assertFalse(ks.themKH(k));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void themQuaDoDaiSDT() {
        try {
            KhachHang k = new KhachHang();
            k.setMaKH("0983763212");
            k.setTenKH("Lê Mỹ Quyên");
            k.setDiaChi("371 Nguyễn Kiệm, P.3, Q.Gò Vấp, TP.HCM");
            k.setSdt("09837632121");
            KhachHangService ks = new KhachHangService(CONN);
            Assertions.assertFalse(ks.themKH(k));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void themQuaDoDaiID() {
        try {
            KhachHang k = new KhachHang();
            k.setMaKH("09837632121");
            k.setTenKH("Lê Mỹ Quyên");
            k.setDiaChi("371 Nguyễn Kiệm, P.3, Q.Gò Vấp, TP.HCM");
            k.setSdt("0983763212");
            KhachHangService ks = new KhachHangService(CONN);
            Assertions.assertFalse(ks.themKH(k));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void idKHTonTai() {
        try {
            KhachHangService ks = new KhachHangService(CONN);
            Assertions.assertTrue(ks.kiemTraIdTonTai("0983763212"));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void idKHKhongTonTai() {
        try {
            KhachHangService ks = new KhachHangService(CONN);
            Assertions.assertFalse(ks.kiemTraIdTonTai("adf"));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void idKHNull() {
        try {
            KhachHangService ks = new KhachHangService(CONN);
            Assertions.assertFalse(ks.kiemTraIdTonTai(""));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
