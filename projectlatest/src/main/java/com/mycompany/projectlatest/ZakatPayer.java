package com.mycompany.projectlatest;

public class ZakatPayer extends User {
    private static int payerCounter = 1;
    private double zakatAmount;
    private String password;

    // Corrected constructor with full arguments for superclass
    public ZakatPayer(String name, String phoneNumber, String email, String address, int age, String password) {
        super(name, phoneNumber, email, address, age); // matches User constructor
        this.password = password;
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
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", zakatAmount=" + zakatAmount +
                '}';
    }

    // Notification method (if required by an interface)
    public void sendNotification(String message) {
        System.out.println("Notification to Zakat Payer " + name + ": " + message);
    }
}