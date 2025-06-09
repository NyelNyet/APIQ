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

public class IbnAlSabil extends Asnaf {
    private String reasonForBeingStranded;

    public IbnAlSabil(String name, String phoneNumber, String email, String address, int age, 
                  double monthlyIncome, double monthlyExpenses, String familyInformation, 
                  String reasonForBeingStranded, double eligibleAmount) {
    super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, eligibleAmount);
    this.reasonForBeingStranded = reasonForBeingStranded;
    this.typeOfAsnaf = "Ibn Al Sabil";  // set type explicitly here
}



    @Override
    public String toString() {
        return "name= " + name +
                ", phoneNumber= " + phoneNumber +
                ", email= " + email +
                ", address= " + address +
                ", age= " + age +
                ", monthlyIncome= " + monthlyIncome +
                ", monthlyExpenses= " + monthlyExpenses +
                ", familyInformation= " + familyInformation +
                ", typeOfAsnaf= " + typeOfAsnaf +
                ", dateOfApplication= " + dateOfApplication.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) +
                ", reasonForBeingStranded= " + reasonForBeingStranded;
    }

    public String getReasonForBeingStranded() {
        return reasonForBeingStranded;
    }

    public void setReasonForBeingStranded(String reasonForBeingStranded) {
        this.reasonForBeingStranded = reasonForBeingStranded;
    }

}
