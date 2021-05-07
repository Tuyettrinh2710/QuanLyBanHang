/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;

/**
 *
 * @author CasMap
 */
public class DonHang {
    private int idDH;
    private String kh_id;
    private int sp_id;
    private BigDecimal donGia;
    private String tinhTrang;

    /**
     * @return the idDH
     */
    public int getIdDH() {
        return idDH;
    }

    /**
     * @param idDH the idDH to set
     */
    public void setIdDH(int idDH) {
        this.idDH = idDH;
    }

    /**
     * @return the kh_id
     */
    public String getKh_id() {
        return kh_id;
    }

    /**
     * @param kh_id the kh_id to set
     */
    public void setKh_id(String kh_id) {
        this.kh_id = kh_id;
    }

    /**
     * @return the sp_id
     */
    public int getSp_id() {
        return sp_id;
    }

    /**
     * @param sp_id the sp_id to set
     */
    public void setSp_id(int sp_id) {
        this.sp_id = sp_id;
    }

    /**
     * @return the donGia
     */
    public BigDecimal getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
