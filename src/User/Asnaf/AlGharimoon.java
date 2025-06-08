package User.Asnaf;

import java.time.format.DateTimeFormatter;

public class AlGharimoon extends Asnaf {
    // Those who are in debt and need help to pay it off
    private double debtAmount;

    public AlGharimoon(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, double debtAmount) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation);
        this.debtAmount = debtAmount;
    }

    public double getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(double debtAmount) {
        this.debtAmount = debtAmount;
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
