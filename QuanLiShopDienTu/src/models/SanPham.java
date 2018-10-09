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
    public int ID,Price,Quantity,TypeID,TradeMarkID;
    
    public void SanPham(){
        
    }
    //Id
    public void SetID(int ID){
        this.ID = ID;
    }
    public int GetID(){
        return this.ID;
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
    
}
