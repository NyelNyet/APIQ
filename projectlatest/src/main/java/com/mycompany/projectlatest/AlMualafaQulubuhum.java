/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectlatest;

/**
 *
 * @author muhdf
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AlMualafaQulubuhum extends Asnaf {
    private LocalDate dateOfConversion;

    public AlMualafaQulubuhum(String name, String phoneNumber, String email, String address, int age,
                              double monthlyIncome, double monthlyExpenses, String familyInformation,
                              LocalDate dateOfConversion, double eligibleAmount) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, eligibleAmount);
        this.dateOfConversion = dateOfConversion;
        this.typeOfAsnaf = "Al-Mualafa Qulubuhum";
    }


    
    public LocalDate getDateOfConversion() {
        return dateOfConversion;
    }

    public void setDateOfConversion(LocalDate dateOfConversion) {
        this.dateOfConversion = dateOfConversion;
    }

    @Override
    public String toString(){
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
                ", dateOfConversion= " + dateOfConversion.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
