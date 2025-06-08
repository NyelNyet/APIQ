package User.Asnaf;

import java.time.format.DateTimeFormatter;

public class FiSabiLillah extends Asnaf{
    // Those who are striving in the way of Allah

    private String acitivityInTheCauseOfAllah;

    public FiSabiLillah(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String acitivityInTheCauseOfAllah) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation);
        this.acitivityInTheCauseOfAllah = acitivityInTheCauseOfAllah;
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
