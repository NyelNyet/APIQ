/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectlatest;

/**
 *
 * @author muhdf
 */

import java.time.format.DateTimeFormatter;

public class AlGharimoon extends Asnaf {
    private double debtAmount;

    public AlGharimoon(String name, String phoneNumber, String email, String address, int age,
                       double monthlyIncome, double monthlyExpenses, String familyInformation,
                       double debtAmount, double eligibleAmount) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, eligibleAmount);
        this.debtAmount = debtAmount;
        this.typeOfAsnaf = "Al-Gharimoon";
    }
    public double getDebtAmount() {
        return debtAmount;
    }
    @Override
    public String toString() {
        return "name= " + name +
                ", phoneNumber= " + phoneNumber+
                ", email= " + email +
                ", address= " + address +
                ", age= " + age +
                ", monthlyIncome= " + monthlyIncome +
                ", monthlyExpenses= " + monthlyExpenses +
                ", familyInformation= " + familyInformation +
                ", typeOfAsnaf= " + typeOfAsnaf +
                ", dateOfApplication= " + dateOfApplication.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) +
                ", debtAmount= " + debtAmount;
    }

}
