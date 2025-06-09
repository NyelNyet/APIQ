import java.util.*;

import User.*;
import User.Asnaf.*;

public class DataManager {
    private List<Asnaf> asnafList;
    private List<ZakatPayer> zakatPayerList;

    public DataManager(){
        asnafList = new ArrayList<>();
        zakatPayerList = new ArrayList<>();
    }

    public void addAsnaf(Asnaf asnaf){
        asnafList.add(asnaf);
    }

    public void addZakatPayer(ZakatPayer zakatPayer){
        zakatPayerList.add(zakatPayer);
    }

    public void getAllZakatPayerDetail(){
        zakatPayerList.forEach(x -> System.out.println(x.toString()));
    }

    public void getAllAsnafDetail(){
        asnafList.forEach(x -> System.out.println(x.toString()+'\n'));
    }

    public List<ZakatPayer> getZakatPayerList() {
        return zakatPayerList;
    }

    public List<Asnaf> getAsnafList(){
        return asnafList;
    }   
}