package User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {
    protected int userID;
    protected String name;;
    protected String phoneNumber;
    protected String email;
    protected String address;
    protected int age;

    public User(String name, String phoneNumber, String email, String address, int age){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.age = age;
        setUserID();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    private void setUserID(){
        try{
            File d = new File("UserCount.txt");
            Scanner find = new Scanner(d);
            
            int num = Integer.parseInt(find.next());
            this.userID = num++;
            find.close();

            PrintWriter out = new PrintWriter(d);            

            out.println(num);
            
            out.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found!");
        }
    }

    public int getUserID() {
        return userID;
    }

}