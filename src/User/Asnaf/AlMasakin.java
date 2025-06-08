package User.Asnaf;

public class AlMasakin extends Asnaf{

    public AlMasakin(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation);
    }

    @Override
    public String toString() {
        return deftoString();
    }
}
