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
public class Staff {
    private int id, typeStaffId;
    private String accountName,password,address,phoneNumber,birthdate,name,cmnd;
    private boolean gender;
    

    public Staff() {
    }

    public Staff(int id, String name,String accountName, String password, String address, String phoneNumber, String birthdate, boolean gender) {
        this.id = id;
        this.name= name;
        this.accountName = accountName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getTypeStaffId() {
        return typeStaffId;
    }

    public void setTypeStaffId(int typeStaffId) {
        this.typeStaffId = typeStaffId;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", accountName=" + accountName + ", password=" + password + ", address=" + address + ", phoneNumber=" + phoneNumber + ", birthdate=" + birthdate + ", name=" + name + ", gender=" + gender + '}';
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

   

    
}
