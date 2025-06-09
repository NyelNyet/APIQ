/*
Danial Harith bin Mohd Sukeri 2411467
abstract Parent as for 8 type of Asnaf. this class will contain the common information of the asnaf.
*/

package User.Asnaf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import User.User;

public abstract class Asnaf extends User {
    protected double monthlyIncome;
    protected double monthlyExpenses;
    protected double amountRecieved;
    protected String familyInformation;
    protected String typeOfAsnaf;
    protected LocalDate dateOfApplication;

    public Asnaf(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String typeOfAsnaf) {
        super(name, phoneNumber, email, address, age);
        if (monthlyIncome < 0) {
            throw new IllegalArgumentException("Monthly income cannot be negative.");
        }
        if (monthlyExpenses < 0) {
            throw new IllegalArgumentException("Monthly expenses cannot be negative.");
        }
        if (familyInformation == null || familyInformation.trim().isEmpty()) {
            throw new IllegalArgumentException("Family information cannot be null or empty.");
        }
        if (typeOfAsnaf == null || typeOfAsnaf.trim().isEmpty()) {
            throw new IllegalArgumentException("Type of Asnaf cannot be null or empty.");
        }
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
        if (monthlyIncome < 0) {
            throw new IllegalArgumentException("Monthly income cannot be negative.");
        }
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(double monthlyExpenses) {
        if (monthlyExpenses < 0) {
            throw new IllegalArgumentException("Monthly expenses cannot be negative.");
        }
        this.monthlyExpenses = monthlyExpenses;
    }

    public String getFamilyInformation() {
        return familyInformation;
    }

    public void setFamilyInformation(String familyInformation) {
        if (familyInformation == null || familyInformation.trim().isEmpty()) {
            throw new IllegalArgumentException("Family information cannot be null or empty.");
        }
        this.familyInformation = familyInformation;
    }

    public LocalDate getDateOfApplication() {
        return dateOfApplication;
    }

    public void setAmountReceived(double amountRecieved) {
        if (amountRecieved < 0) {
            throw new IllegalArgumentException("Amount received cannot be negative.");
        }
        this.amountRecieved = amountRecieved;
    }

    @Override
    public abstract String toString();

    public String deftoString() {
        return "name: " + name +
                ", phoneNumber: " + phoneNumber +
                ", email: " + email +
                ", address: " + address +
                ", age: " + age +
                ", monthlyIncome: " + monthlyIncome +
                ", monthlyExpenses: " + monthlyExpenses +
                ", familyInformation: " + familyInformation +
                ", typeOfAsnaf: " + typeOfAsnaf +
                ", dateOfApplication: " + dateOfApplication.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
