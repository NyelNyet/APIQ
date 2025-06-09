/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectlatest;

/**
 *
 * @author muhdf
 */
public class AlMasakin extends Asnaf {
    public AlMasakin(String name, String phoneNumber, String email, String address, int age,
                     double monthlyIncome, double monthlyExpenses, String familyInformation, double eligibleAmount) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, eligibleAmount);
        this.typeOfAsnaf = "Al-Masakin";
    }


    @Override
    public String toString() {
        return deftoString();
    }
}
