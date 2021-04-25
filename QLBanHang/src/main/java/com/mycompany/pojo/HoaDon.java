/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author CasMap
 */
public class HoaDon {
    private int maHD;
    private String maNV;
    private Date ngayBan;
    private String maKH;
    

    /**
     * @return the maHD
     */
    public int getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    /**
     * @return the maNV
     */
    public String getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the ngayBan
     */
    public Date getNgayBan() {
        return ngayBan;
    }

    /**
     * @param ngayBan the ngayBan to set
     */
    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    /**
     * @return the maKH
     */
    public String getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setNgayBan(DatePicker dpNgay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
