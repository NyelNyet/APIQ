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

public class AlAmilunaAlaiha extends Asnaf {
    private String zakatAgency;

    public AlAmilunaAlaiha(String name, String phoneNumber, String email, String address, int age,
                           double monthlyIncome, double monthlyExpenses, String familyInformation,
                           String zakatAgency, double eligibleAmount) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, eligibleAmount);
        this.zakatAgency = zakatAgency;
        this.typeOfAsnaf = "Al-Amiluna Alaiha";
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
                ", zakatAgency= " + zakatAgency;
    }
    
    public String getZakatAgency() {
        return zakatAgency;
    }

    public void setZakatAgency(String zakatAgency) {
        this.zakatAgency = zakatAgency;
    }
}