//Danial Harith bin Mohd Sukeri 2411467

package User.Asnaf;

import java.time.format.DateTimeFormatter;

public class AlAmilunaAlaiha extends Asnaf{
    //Zakat distributors/administrators

    private String zakatAgency;

    public AlAmilunaAlaiha(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String zakatAgency, String typeOfAsnaf) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfAsnaf);
        this.zakatAgency = zakatAgency;
    }

    public String getZakatAgency() {
        return zakatAgency;
    }

    public void setZakatAgency(String zakatAgency) {
        this.zakatAgency = zakatAgency;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", phoneNumber: " + phoneNumber+
                ", email: " + email +
                ", address: " + address +
                ", age: " + age +
                ", monthlyIncome: " + monthlyIncome +
                ", monthlyExpenses: " + monthlyExpenses +
                ", familyInformation: " + familyInformation +
                ", typeOfAsnaf: " + typeOfAsnaf +
                ", dateOfApplication: " + dateOfApplication.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) +
                ", zakatAgency: " + zakatAgency;
    }
    
}
