/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zds;

/**
 *
 * @author user
 */
public abstract class User implements NotificationService {
    protected String userId; // Not mandatory for all users
    protected String name;
    protected int phoneNumber;
    protected String email;
    protected int age;
    protected String address;

    // Constructor (userId is set only in subclasses that need it)
    public User(String name, String email, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Helper for ID generation
    protected String generateUserId(String prefix, int counter) {
        return String.format("%s%03d", prefix, counter);
    }

    // Getters and setters
    public String getUserId() {
        return userId; // May return null for ZakatRecipient
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int asnafAge) {
        this.age = asnafAge;
    }

    public String getAsnafAddress() {
        return address;
    }

    public void setAsnafAddress(String asnafAddress) {
        this.address = asnafAddress;
    }
}
