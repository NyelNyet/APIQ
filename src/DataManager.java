import java.util.*;

import User.Asnaf.*;

public class DataManager {
    private List<Asnaf> asnafList;
    /*private List<ZakatPayer> zakatPayerList;
    private ArrayList<Payment> paymentList;*/


    public DataManager(){
        asnafList = new ArrayList<>();
        /*zakatPayerList = new ArrayList<>();
        paymentList = new ArrayList<>();*/
    }

    public void addAsnaf(Asnaf asnaf){

        asnafList.add(asnaf);
    }

    /*public void addZakatPayer(ZakatPayer zakatPayer){
        zakatPayerList.add(zakatPayer);
    }*/

    public void getAllAsnafDetail(){
        asnafList.forEach(x -> System.out.println(x.toString()+'\n'));
    }

    public List<Asnaf> getAsnafList(){
        return asnafList;
    }
    
}
