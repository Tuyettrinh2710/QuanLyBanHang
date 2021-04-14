/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author CasMap
 */
public class LoaiSP {
    private int idLoaiSP;
    private String tenLoaiSP;

    @Override
    public String toString() {
        return this.tenLoaiSP;
    }

    /**
     * @return the idLoaiSP
     */
    public int getIdLoaiSP() {
        return idLoaiSP;
    }

    /**
     * @param idLoaiSP the idLoaiSP to set
     */
    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    /**
     * @return the tenLoaiSP
     */
    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    /**
     * @param tenLoaiSP the tenLoaiSP to set
     */
    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }
}
