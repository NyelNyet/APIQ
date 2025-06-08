package User.Asnaf;

import java.time.format.DateTimeFormatter;

public class AlRiqab extends Asnaf{
    //Those who are enslaved and need help to free themselves
    private String typeOfCaptivity;

    public AlRiqab(String name, String phoneNumber, String email, String address, int age, double monthlyIncome, double monthlyExpenses, String familyInformation, String typeOfCaptivity) {
        super(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation);
        this.typeOfCaptivity = typeOfCaptivity;
    }

    /* -- Types Of Captivity --
    Prisoners of War – Captives taken in war could be enslaved, though Islam encouraged their release through ransom or manumission.

    Born into Slavery – Children of enslaved individuals were considered slaves unless freed.

    Purchased Slaves – Slavery existed through trade, though Islam encouraged freeing slaves as an act of virtue.

    Debt Slavery (Pre-Islamic Practice) – Before Islam, debtors could be enslaved if they couldn't repay, but Islam abolished this practice.

    Concubinage – Female slaves could become concubines, but their children were considered free.
     */

    public String getTypeOfCaptivity() {
        return typeOfCaptivity;
    }

    public void setTypeOfCaptivity(String typeOfCaptivity) {
        this.typeOfCaptivity = typeOfCaptivity;
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
                ", dateOfConversion= " + typeOfCaptivity;
    }
}
