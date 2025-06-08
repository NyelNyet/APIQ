import java.util.*;

import User.Asnaf.*;

public class DataManager {
    private List<Asnaf> asnafList;
    private List<String[]> allData;
    //private ArrayList<Payment> paymentList; class waiting to pull from main


    public DataManager(){
        asnafList = new ArrayList<>();
        //paymentList = new ArrayLisy();
    }

    //Code below will be implemented on GUI
    /*public void readAllData(List<String[]> allData){
        for(String[] aData : allData){
            switch (aData.length) {
                case 9:{
                    if(aData[8].equals("AlFuqara")){
                        addAsnaf(new AlFuqara(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfAsnaf));
                    }else if(aData[8].equals("AlMasakin")){
                        addAsnaf(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfAsnaf);
                    }
                }break;

                case 10:{
                    switch (aData[9]) {
                        case "AlAmilunaAlaiha":addAsnaf(new AlAmilunaAlaiha(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, zakatAgency, typeOfAsnaf));break;
                        case "AlGharimoon":addAsnaf(new AlGharimoon(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, debtAmount, typeOfAsnaf));break;
                        case "AlMualafaQulubuhum":addAsnaf(new AlMualafaQulubuhum(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, dateOfConversion, typeOfAsnaf));break;
                        case "AlRiqab":addAsnaf(new AlRiqab(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, typeOfCaptivity, typeOfAsnaf));break;
                        case "FiSabiLillah":addAsnaf(new FiSabiLillah(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, acitivityInTheCauseOfAllah, typeOfAsnaf));break;
                        case "IbnAlSabil":addAsnaf(new IbnAlSabil(name, phoneNumber, email, address, age, monthlyIncome, monthlyExpenses, familyInformation, reasonForBeingStranded, typeOfAsnaf))break;
                        }
                    }
                }break;
            }
        }
    }*/

    public void addAsnaf(Asnaf asnaf){
        asnafList.add(asnaf);
    }

    public void getAllAsnafDetail(){
        asnafList.forEach(x -> System.out.println(x.toString()+'\n'));
    }

    public List<Asnaf> getAsnafList(){
        return asnafList;
    }
    
}
