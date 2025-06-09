

import java.io.*;
import java.util.*;

import User.Asnaf.*;

public class FileManager {

    public List<String[]> asnafFileReader(){
        List<String[]> asnafData = new ArrayList<>();
        String[] asnafType = {"AlFuqara","AlMasakin","AlAmilunaAlaiha","AlGharimoon","AlMualafaQulubuhum","AlRiqab","FiSabiLillah","IbnAlSabil"};

        for(String asType : asnafType){
            try{
            BufferedReader reader = new BufferedReader(new FileReader(asType+".txt"));
            String line;
            while((line = reader.readLine()) != null){
                asnafData.add(line.split(";"));
            }
            reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return asnafData;
    }

    public List<String[]> payerFileReader(){
        List<String[]> payerData = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("ZakatPayer.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                payerData.add(line.split(";"));
            }
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return payerData;
    }

    public void writeToFile(Optional<Asnaf> optionalAsnaf/* , Optional<ZakatPayer> optionalZakatPayer*/){
        if(optionalAsnaf.isPresent()){
            Asnaf realAsnaf = optionalAsnaf.get();
            switch (realAsnaf.getClass().getSimpleName()) {
            case "AlFuqara":
            case "AlMasakin":fuqaraMasakinWrite(realAsnaf);break;
            case "AlAmilunaAlaiha":amilunaAlaihaWrite(realAsnaf);break;
            case "AlGharimoon":gharimoonWrite(realAsnaf);break;
            case "AlMualafaQulubuhum":mualafaQulubuhumWrite(realAsnaf);break;
            case "AlRiqab":riqabWrite(realAsnaf);break;
            case "FiSabiLillah":fiSabiLillahWrite(realAsnaf);break;
            case "IbnAlSabil":ibnAlSabilWrite(realAsnaf);break;
            } 
        }/*else if(optionalZakatPayer.isPresent()){
            optionalZakatPayer.get().zakatPayerWrite();
        }*/
        
    }

    private void fuqaraMasakinWrite(Asnaf asnaf){
        try{
            String fileName = asnaf.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(asnaf.getUserID()+";"+asnaf.getName()+";"+asnaf.getPhoneNumber()+";"+asnaf.getEmail()+";"+asnaf.getAddress()+";"+asnaf.getAge()+";"+asnaf.getMonthlyIncome()+";"+asnaf.getMonthlyExpenses()+";"+asnaf.getFamilyInformation()+";"+asnaf.getDateOfApplication()+";"+asnaf.getTypeOfAsnaf());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void amilunaAlaihaWrite(Asnaf asnaf) {
        try{
            String fileName = asnaf.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(asnaf.getUserID()+";"+asnaf.getName()+";"+asnaf.getPhoneNumber()+";"+asnaf.getEmail()+";"+asnaf.getAddress()+";"+asnaf.getAge()+";"+asnaf.getMonthlyIncome()+";"+asnaf.getMonthlyExpenses()+";"+asnaf.getFamilyInformation()+";"+asnaf.getDateOfApplication()+";"+((AlAmilunaAlaiha) asnaf).getZakatAgency()+";"+asnaf.getTypeOfAsnaf());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void gharimoonWrite(Asnaf asnaf){
        try{
            String fileName = asnaf.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(asnaf.getUserID()+";"+asnaf.getName()+";"+asnaf.getPhoneNumber()+";"+asnaf.getEmail()+";"+asnaf.getAddress()+";"+asnaf.getAge()+";"+asnaf.getMonthlyIncome()+";"+asnaf.getMonthlyExpenses()+";"+asnaf.getFamilyInformation()+";"+asnaf.getDateOfApplication()+";"+((AlGharimoon) asnaf).getDebtAmount()+";"+asnaf.getTypeOfAsnaf());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void mualafaQulubuhumWrite(Asnaf asnaf){
        try{
            String fileName = asnaf.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(asnaf.getUserID()+";"+asnaf.getName()+";"+asnaf.getPhoneNumber()+";"+asnaf.getEmail()+";"+asnaf.getAddress()+";"+asnaf.getAge()+";"+asnaf.getMonthlyIncome()+";"+asnaf.getMonthlyExpenses()+";"+asnaf.getFamilyInformation()+";"+asnaf.getDateOfApplication()+";"+((AlMualafaQulubuhum) asnaf).getDateOfConversion()+";"+asnaf.getTypeOfAsnaf());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void riqabWrite(Asnaf asnaf){
        try{
            String fileName = asnaf.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(asnaf.getUserID()+";"+asnaf.getName()+";"+asnaf.getPhoneNumber()+";"+asnaf.getEmail()+";"+asnaf.getAddress()+";"+asnaf.getAge()+";"+asnaf.getMonthlyIncome()+";"+asnaf.getMonthlyExpenses()+";"+asnaf.getFamilyInformation()+";"+asnaf.getDateOfApplication()+";"+((AlRiqab) asnaf).getTypeOfCaptivity()+";"+asnaf.getTypeOfAsnaf());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void fiSabiLillahWrite(Asnaf asnaf){
        try{
            String fileName = asnaf.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(asnaf.getUserID()+";"+asnaf.getName()+";"+asnaf.getPhoneNumber()+";"+asnaf.getEmail()+";"+asnaf.getAddress()+";"+asnaf.getAge()+";"+asnaf.getMonthlyIncome()+";"+asnaf.getMonthlyExpenses()+";"+asnaf.getFamilyInformation()+";"+asnaf.getDateOfApplication()+";"+((FiSabiLillah) asnaf).getAcitivityInTheCauseOfAllah()+";"+asnaf.getTypeOfAsnaf());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void ibnAlSabilWrite(Asnaf asnaf){
        try{
            String fileName = asnaf.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(asnaf.getUserID()+";"+asnaf.getName()+";"+asnaf.getPhoneNumber()+";"+asnaf.getEmail()+";"+asnaf.getAddress()+";"+asnaf.getAge()+";"+asnaf.getMonthlyIncome()+";"+asnaf.getMonthlyExpenses()+";"+asnaf.getFamilyInformation()+";"+asnaf.getDateOfApplication()+";"+((IbnAlSabil) asnaf).getReasonForBeingStranded()+";"+asnaf.getTypeOfAsnaf());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*private void zakatPayerWrite(ZakatPayer zakatPayer){
        try{
            String fileName = zakatPayer.getClass().getSimpleName()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(zakatPayer.getUserID()+";"+zakatPayer.getName()+";"+zakatPayer.getPhoneNumber()+";"+zakatPayer.getEmail()+";"+zakatPayer.getAddress()+";"+zakatPayer.getAge()+";"+zakatPayer.getZakatAmount());
            writer.newLine();
            System.out.println("New "+asnaf.getClass()+" has been added to file "+fileName);

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
}
