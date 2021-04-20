/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlbanhang;

import javafx.scene.control.TextField;

/**
 *
 * @author CasMap
 */
public class NumberText extends TextField {
    public NumberText() {
        this.setPromptText("Nhập số");
    }
    @Override
    public void replaceText(int i, int i1, String string) {
        if (string.matches("[0-9]") || string.isEmpty()) {
            super.replaceText(i, i1, string); 
        }
    }
}
