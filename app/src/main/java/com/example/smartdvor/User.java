package com.example.smartdvor;

public class User {

    public String id;
    public String phoneNumber;
    public String password;
    public String street;
    public String houseNumber;
    public String apartNumber;

    public User(String id, String phoneNumber, String password, String street, String houseNumber, String apartNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartNumber = apartNumber;
    }
}
