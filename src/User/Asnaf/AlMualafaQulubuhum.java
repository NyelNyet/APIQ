//Danial Harith bin Mohd Sukeri 2411467

package User.Asnaf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlMualafaQulubuhum extends Asnaf {
    //Those who are inclined towards Islam

    private LocalDate dateOfConversion;

    public AlMualafaQulubuhum(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, LocalDate dateOfConversion, String typeOfAsnaf) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfAsnaf);
        this.dateOfConversion = dateOfConversion;
    }
    
    public LocalDate getDateOfConversion() {
        return dateOfConversion;
    }

    public void setDateOfConversion(LocalDate dateOfConversion) {
        this.dateOfConversion = dateOfConversion;
    }

    @Override
    public String toString(){
        return "name: " + name +
                ", phoneNumber: " + phoneNumber +
                ", email: " + email +
                ", address: " + address +
                ", age: " + age +
                ", monthlyIncome: " + monthlyIncome +
                ", monthlyExpenses: " + monthlyExpenses +
                ", familyInformation: " + familyInformation +
                ", typeOfAsnaf: " + typeOfAsnaf +
                ", dateOfApplication: " + dateOfApplication.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) +
                ", dateOfConversion: " + dateOfConversion.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
