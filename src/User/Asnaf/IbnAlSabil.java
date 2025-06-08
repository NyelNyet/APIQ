package User.Asnaf;

import java.time.format.DateTimeFormatter;

public class IbnAlSabil extends Asnaf {
    // Those who are stranded travelers

    private String reasonForBeingStranded;

    public IbnAlSabil(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String reasonForBeingStranded, String typeOfAsnaf) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfAsnaf);
        this.reasonForBeingStranded = reasonForBeingStranded;
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
                ", reasonForBeingStranded= " + reasonForBeingStranded;
    }

    public String getReasonForBeingStranded() {
        return reasonForBeingStranded;
    }

    public void setReasonForBeingStranded(String reasonForBeingStranded) {
        this.reasonForBeingStranded = reasonForBeingStranded;
    }

}
