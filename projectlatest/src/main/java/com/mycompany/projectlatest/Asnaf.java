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

import com.mycompany.projectlatest.User;

public abstract class Asnaf extends User{
    protected double monthlyIncome;
    protected double monthlyExpenses;
    protected double amountRecieved;
    protected String familyInformation;
    protected String typeOfAsnaf;
    protected LocalDate dateOfApplication;
    protected double eligibleAmount;

    public Asnaf(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, double eligibleAmount){
        super(name,phoneNumber,email,address,age);
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;
        this.familyInformation = familyInformation;
        this.typeOfAsnaf = "Undetermined";
        this.dateOfApplication = LocalDate.now();
        this.eligibleAmount = eligibleAmount;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public String getFamilyInformation() {
        return familyInformation;
    }

    public void setFamilyInformation(String familyInformation) {
        this.familyInformation = familyInformation;
    }

    public LocalDate getDateOfApplication() {
        return dateOfApplication;
    }
    
    public String getTypeOfAsnaf() {
        return typeOfAsnaf;
    }
    
    public double getEligibleAmount(){
        return eligibleAmount;
    }

    @Override
    public abstract String toString();

    public String deftoString(){
        return "name= " + name + ", " +
                ", phoneNumber= " + phoneNumber + ", " +
                ", email= " + email + ", " +
                ", address= " + address + ", " +
                ", age= " + age + ", " +
                ", monthlyIncome= " + monthlyIncome + ", " +
                ", monthlyExpenses= " + monthlyExpenses + ", " +
                ", familyInformation= " + familyInformation + ", " +
                ", typeOfAsnaf= " + typeOfAsnaf + ", " +
                ", dateOfApplication= " + dateOfApplication.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    };
}