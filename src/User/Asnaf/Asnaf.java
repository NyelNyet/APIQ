package User.Asnaf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import User.User;

public abstract class Asnaf extends User{
    protected double monthlyIncome;
    protected double monthlyExpenses;
    protected double amountRecieved;
    protected String familyInformation;
    protected String typeOfAsnaf;
    protected LocalDate dateOfApplication;

    public Asnaf(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String typeOfAsnaf){
        super(name,phoneNumber,email,address,age);
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;
        this.familyInformation = familyInformation;
        this.typeOfAsnaf = typeOfAsnaf;
        this.dateOfApplication = LocalDate.now();
    }

    public String getTypeOfAsnaf() {
        return typeOfAsnaf;
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
