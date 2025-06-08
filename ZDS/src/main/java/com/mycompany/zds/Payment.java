package com.mycompany.zds;

import java.util.Date;

public class Payment {
    private String paymentId;
    private double amount;
    private Date date;

    // Constructor
    public Payment(int iD, int userID, double amount, Date date) {
        this.paymentId = "PAY" + userID + "-" + String.format("%03d", iD);
        this.amount = amount;
        this.date = date;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }

    // Getter for date
    public Date getDate() {
        return date;
    }

    //Getter for payment ID
    public String getPaymentId() {
        return paymentId;
    }
}
