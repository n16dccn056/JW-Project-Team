/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author admin
 */
public class SanPham {
    public String Name;
    public int ID,Price,Quantity,TypeID,TradeMarkID,state;
    
    public void SanPham(){
        
    }
    //Id
    public void SetID(int ID){
        this.ID = ID;
    }
    public int GetID(){
        return this.ID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
     //Name
    public void SetName(String Name){
        this.Name = Name;
    }
    public String GetName(){
        return this.Name;
    }
     //Price
    public void SetPrice(int Price){
        this.Price = Price;
    }
    public int GetPrice(){
        return this.Price;
    }
     //Quantity
    public void SetQuantity(int Quantity){
        this.Quantity = Quantity;
    }
    public int GetQuantity(){
        return this.Quantity;
    }
     //TypeID
    public void SetTypeID(int TypeID){
        this.TypeID = TypeID;
    }
    public int GetTypeID(){
        return this.TypeID;
    }
     //TradeMarkID
    public void SetTradeMarkID(int TradeMarkID){
        this.TradeMarkID = TradeMarkID;
    }
    public int GetTradeMarkID(){
        return this.TradeMarkID;
    }

    public void setDon(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void GetID(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void GetPrice(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void GetTypeID(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void GetQuantity(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
