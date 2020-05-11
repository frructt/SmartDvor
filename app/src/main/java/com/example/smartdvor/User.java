package com.example.smartdvor;

public class User {

    public String id;
    public String phoneNumber;
    public String password;
    public String street;
    public String houseNumber;
    public String apartNumber;

    public void setRegistration(String phoneNumber, String password){
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getPassword(){
        return password;
    }
    public User(String phoneNumber, String password, String street, String houseNumber, String apartNumber) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartNumber = apartNumber;
    }
}
