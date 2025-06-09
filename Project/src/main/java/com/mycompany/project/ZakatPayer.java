/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author syahi
 */
public class ZakatPayer extends User{
    private static int payerCounter = 1;
    private double zakatAmount;
    private String password;

    // Constructor with phone number included
    public Zakatpayer(String name, String email, String phoneNumber, String password) {
        super(name, email, phoneNumber); // Sets phone number correctly
        this.password = password;
        this.userId = generateUserId("P", payerCounter++);
        this.zakatAmount = 0.0;
    }

    // Submit zakat payment
    public boolean submitPayment(double amount) {
        if (amount > 0) {
            this.zakatAmount += amount;
            System.out.println("Zakat payment of RM" + amount + " submitted successfully.");
            return true;
        } else {
            System.out.println("Invalid zakat amount.");
            return false;
        }
    }

    // Get zakat amount
    public double getZakatAmount() {
        return zakatAmount;
    }

    // Set zakat amount
    public void setZakatAmount(double amount) {
        this.zakatAmount = amount;
    }

    // toString method
    @Override
    public String toString() {
        return "ZakatPayer{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", zakatAmount=" + zakatAmount +
                '}';
    }

    // Required by NotificationService
    @Override
    public void sendNotification(String message) {
        System.out.println("Notification to Zakat Payer " + name + ": " + message);
    }
    
}
