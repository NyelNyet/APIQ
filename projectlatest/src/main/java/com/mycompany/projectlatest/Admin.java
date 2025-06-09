package com.mycompany.projectlatest;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;

public class Admin extends User implements AuthLogIn {
    private static int adminCounter = 1;
    private String password;

    // Corrected constructor
    public Admin(String name, String phoneNumber, String email, String address, int age, String password) {
        super(name, phoneNumber, email, address, age); // Proper argument order
        this.password = password;
    }

    // Review applications (placeholder implementation)
    public List<Application> reviewApplications() {
        System.out.println("Admin " + name + " is reviewing applications...");
        return new ArrayList<>();
    }

    // Approve request (mock logic)
    public boolean approveRequest(int appId) {
        System.out.println("Application ID " + appId + " has been approved by Admin " + name + ".");
        return true;
    }

    // Distribute funds to an Asnaf
    public double distributeFunds(Asnaf asnaf) {
        double amount = asnaf.getEligibleAmount();
        System.out.println("Distributed RM" + amount + " to " + asnaf.getName());
        return amount;
    }

    // Assign Asnaf type
    public Asnaf setAsnafType(Asnaf asnaf, String type) {
    switch (type.toLowerCase()) {
        case "al-fuqara":
            return new AlFuqara(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(), asnaf.getEligibleAmount()
            );
        case "al-masakin":
            return new AlMasakin(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(), asnaf.getEligibleAmount()
            );
        case "al-amiluna-alaiha":
            return new AlAmilunaAlaiha(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(), 
                ((AlAmilunaAlaiha) asnaf).getZakatAgency(),
                asnaf.getEligibleAmount()
            );
        case "al-mualafa-qulubuhum":
            return new AlMualafaQulubuhum(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(),
                ((AlMualafaQulubuhum) asnaf).getDateOfConversion(),
                asnaf.getEligibleAmount()
            );
        case "al-riqab":
            return new AlRiqab(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(),
                ((AlRiqab) asnaf).getTypeOfCaptivity(),
                asnaf.getEligibleAmount()
            );
        case "al-gharimoon":
            return new AlGharimoon(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(),
                ((AlGharimoon) asnaf).getDebtAmount(),
                asnaf.getEligibleAmount()
            );
        case "fi-sabilillah":
            return new FiSabiLillah(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(),
                ((FiSabiLillah) asnaf).getAcitivityInTheCauseOfAllah(),
                asnaf.getEligibleAmount()
            );
        case "ibn-al-sabil":
            return new IbnAlSabil(
                asnaf.getName(), asnaf.getPhoneNumber(), asnaf.getEmail(), asnaf.getAddress(),
                asnaf.getAge(), asnaf.getMonthlyIncome(), asnaf.getMonthlyExpenses(),
                asnaf.getFamilyInformation(),
                ((IbnAlSabil) asnaf).getReasonForBeingStranded(),
                asnaf.getEligibleAmount()
            );
        default:
            System.out.println("Invalid Asnaf type.");
            return null;
    }
}




    @Override
    public boolean login(String inputEmail, String inputPassword) {
        return this.email.equals(inputEmail) && this.password.equals(inputPassword);
    }

    public void sendNotification(String message) {
        System.out.println("Notification to Admin " + name + ": " + message);
    }
}
