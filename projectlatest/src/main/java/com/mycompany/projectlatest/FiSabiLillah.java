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

public class FiSabiLillah extends Asnaf {
    private String acitivityInTheCauseOfAllah;

    public FiSabiLillah(String name, String phoneNumber, String email, String address, int age,
                   double monthlyIncome, double monthlyExpenses, String familyInformation,
                   String activityInTheCauseOfAllah, double eligibleAmount) {
    super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, eligibleAmount);
    this.acitivityInTheCauseOfAllah = activityInTheCauseOfAllah;
    this.typeOfAsnaf = "Fi Sabilillah";  // Set type here explicitly
}



    public String getAcitivityInTheCauseOfAllah() {
        return acitivityInTheCauseOfAllah;
    }

    public void setAcitivityInTheCauseOfAllah(String acitivityInTheCauseOfAllah) {
        this.acitivityInTheCauseOfAllah = acitivityInTheCauseOfAllah;
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
                ", acitivityInTheCauseOfAllah= " + acitivityInTheCauseOfAllah;
    }

}