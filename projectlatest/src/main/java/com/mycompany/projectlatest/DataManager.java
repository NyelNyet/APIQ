/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectlatest;

/**
 *
 * @author muhdf
 */
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class DataManager {
    private List<User> asnafList = new ArrayList<>();

    public DataManager() {
        // Add sample data
        asnafList.add(new AlFuqara("MINAH", "0111111111", "minah123@gmail.com", "Rumah Minah", 25, 1500.0, 1000.0, "5 total family members", 0.0));
        asnafList.add(new AlMasakin("AHMAD", "0122222222", "ahmad123@gmail.com", "Rumah Ahmad", 30, 2000.0, 1500.0, "3 total family members", 0.0));
        asnafList.add(new AlAmilunaAlaiha("ABU", "0133333333", "zakatagency123@gmail.com", "Rumah Abu", 40, 3000.0, 2500.0, "5 total family members", "IIUM Zakat Agency", 0.0));
        asnafList.add(new AlMualafaQulubuhum("ALI", "0144444444", "mualafa123@gmail.com", "Rumah Ali", 35, 2500.0, 2000.0, "4 total family members", LocalDate.now(), 0.0));
        asnafList.add(new AlRiqab("KHAIRUL", "0155555555", "riqab123@gmail.com", "Rumah Khairul", 28, 2200.0, 1800.0, "2 total family members", "Debt Slavery", 0.0));
        asnafList.add(new AlGharimoon("ZAIN", "0166666666", "gharimoon123@gmail.com", "Rumah Zain", 32, 2700.0, 2200.0, "6 total family members", 15000.0, 0.0));
        asnafList.add(new FiSabiLillah("SITI", "0177777777", "fi_sabilillah123@gmail.com", "Rumah Siti", 29, 2500.0, 2000.0, "4 total family members", "Dakwah", 0.0));

    }

    public List<User> getAllAsnaf() {
        return asnafList;
    }
}
