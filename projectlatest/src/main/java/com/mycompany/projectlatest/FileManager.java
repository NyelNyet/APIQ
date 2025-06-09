/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectlatest;

/**
 *
 * @author muhdf
 */



import java.io.*;
import java.util.*;

import com.mycompany.projectlatest.Asnaf.*;

public class FileManager {

    public List<String[]> allFileReader(){
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

    public void writeToFile(Asnaf asnaf){
        switch (asnaf.getClass().getSimpleName()) {
            case "AlFuqara":
            case "AlMasakin":fuqaraMasakinWrite(asnaf);break;
            case "AlAmilunaAlaiha":amilunaAlaihaWrite(asnaf);break;
            case "AlGharimoon":gharimoonWrite(asnaf);break;
            case "AlMualafaQulubuhum":mualafaQulubuhumWrite(asnaf);break;
            case "AlRiqab":riqabWrite(asnaf);break;
            case "FiSabiLillah":fiSabiLillahWrite(asnaf);break;
            case "IbnAlSabil":ibnAlSabilWrite(asnaf);break;
        } 
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

}
