/*
Danial Harith bin Mohd Sukeri 2411467
This class will contains all the data in a list. it will first read from file once the program starts and store it into the list.
this list then will be use by other class to either display or remove anything
*/

import java.util.ArrayList;
import java.util.List;
import User.Asnaf.Asnaf;
import User.ZakatPayer;

public class DataManager {
    private List<Asnaf> asnafList;
    private List<ZakatPayer> zakatPayerList;

    public DataManager() {
        asnafList = new ArrayList<>();
        zakatPayerList = new ArrayList<>();
    }

    public void addAsnaf(Asnaf asnaf) {
        if (asnaf == null) {
            throw new IllegalArgumentException("Cannot add a null Asnaf object.");
        }
        asnafList.add(asnaf);
    }

    public void addZakatPayer(ZakatPayer zakatPayer) {
        if (zakatPayer == null) {
            throw new IllegalArgumentException("Cannot add a null ZakatPayer object.");
        }
        zakatPayerList.add(zakatPayer);
    }

    public void getAllZakatPayerDetail() {
        if (zakatPayerList.isEmpty()) {
            System.out.println("No Zakat Payer data available.");
            return;
        }
        zakatPayerList.forEach(x -> System.out.println(x.toString()));
    }

    public void getAllAsnafDetail() {
        if (asnafList.isEmpty()) {
            System.out.println("No Asnaf data available.");
            return;
        }
        asnafList.forEach(x -> System.out.println(x.toString() + '\n'));
    }

    public List<ZakatPayer> getZakatPayerList() {
        return new ArrayList<>(zakatPayerList); // Return a copy to prevent external modification
    }

    public List<Asnaf> getAsnafList() {
        return new ArrayList<>(asnafList); // Return a copy to prevent external modification
    }
}
