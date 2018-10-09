/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Admin
 */
public class LoaiSanPham {
    private int ID;
    private String name;

    public LoaiSanPham(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public LoaiSanPham(){
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
