/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author syahi
 */

public class ZakatPayer extends User{
    private double zakatAmount;

    public ZakatPayer(String name, String phoneNumber, String email, String address, int age, double zakatAmount) {
        super(name, phoneNumber, email, address, age);
        this.zakatAmount = zakatAmount;
    }

    public boolean submitPayment(double amount) {
        if (amount > 0) {
            this.zakatAmount += amount;
            System.out.println("Zakat payment of RM" + amount + " submitted successfully.");
            return true;
        } else {
            System.out.println("Invalid zakat amount.");
            return false;
        }
    }

    public double getZakatAmount() {
        return zakatAmount;
    }

    public void setZakatAmount(double amount) {
        this.zakatAmount = amount;
    }

    @Override
    public String toString() {
        return  "name: " + name  + ", " +
                ", phoneNumber: " + phoneNumber  + ", " +
                ", email: " + email  + ", " +
                ", address: " + address  + ", " +
                ", age: "  + age + ", " +
                ", zakatAmount: " + zakatAmount;
    }

    
}
