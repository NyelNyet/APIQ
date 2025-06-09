//Danial Harith bin Mohd Sukeri 2411467

package User.Asnaf;

public class AlFuqara extends Asnaf{

    public AlFuqara(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String typeOfAsnaf) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfAsnaf);
    }

    @Override
    public String toString() {
        return deftoString();
    }
}
