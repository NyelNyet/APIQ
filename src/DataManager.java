import java.util.ArrayList;

import User.Asnaf.*;

public class DataManager {
    private ArrayList<Asnaf> asnafList;
    //private ArrayList<Payment> paymentList; class waiting to pull from main


    public DataManager(){
        asnafList = new ArrayList();
        //paymentList = new ArrayLisy();
    }

    public void addAsnaf(Asnaf asnaf){
        asnafList.add(asnaf);
    }

    public void getAllAsnafDetail(){
        asnafList.forEach(x -> System.out.println(x.toString()+'\n'));
    }
}
