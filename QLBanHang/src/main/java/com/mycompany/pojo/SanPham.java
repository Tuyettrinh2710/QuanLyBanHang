/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.io.FileInputStream;
import java.math.BigDecimal;

/**
 *
 * @author CasMap
 */
public class SanPham {
    private int idSP;
    private String tenSP;
    private int soLuong;
    private BigDecimal donGiaNhap;
    private BigDecimal donGiaBan;
    private byte[] anh;
    private int loaiSP_id;

    /**
     * @return the idSP
     */
    public int getIdSP() {
        return idSP;
    }

    /**
     * @param idSP the idSP to set
     */
    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    /**
     * @return the tenSP
     */
    public String getTenSP() {
        return tenSP;
    }

    /**
     * @param tenSP the tenSP to set
     */
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the donGiaNhap
     */
    public BigDecimal getDonGiaNhap() {
        return donGiaNhap;
    }

    /**
     * @param donGiaNhap the donGiaNhap to set
     */
    public void setDonGiaNhap(BigDecimal donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    /**
     * @return the donGiaBan
     */
    public BigDecimal getDonGiaBan() {
        return donGiaBan;
    }

    /**
     * @param donGiaBan the donGiaBan to set
     */
    public void setDonGiaBan(BigDecimal donGiaBan) {
        this.donGiaBan = donGiaBan;
    }

    

    /**
     * @return the loaiSP_id
     */
    public int getLoaiSP_id() {
        return loaiSP_id;
    }

    /**
     * @param loaiSP_id the loaiSP_id to set
     */
    public void setLoaiSP_id(int loaiSP_id) {
        this.loaiSP_id = loaiSP_id;
    }

    /**
     * @return the anh
     */
    public byte[] getAnh() {
        return anh;
    }

    /**
     * @param anh the anh to set
     */
    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
