package User.Asnaf;

public class AlMasakin extends Asnaf{

    public AlMasakin(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String typeOfAsnaf) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfAsnaf);
    }

    @Override
    public String toString() {
        return deftoString();
    }
}
