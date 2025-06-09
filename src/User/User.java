package User;//done by 2413801_FARIS

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.NoSuchElementException;

public abstract class User implements NotificationService {
    protected int userID;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected String address;
    protected int age;

    public User(String name, String phoneNumber, String email, String address, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be a positive number.");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.age = age;
        setUserID();
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be a positive number.");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    private void setUserID() {
        File userCountFile = new File("UserCount.txt");
        Scanner find = null;
        PrintWriter out = null;
        try {
            find = new Scanner(userCountFile);
            int num = Integer.parseInt(find.next());
            this.userID = num++;
            
            out = new PrintWriter(userCountFile);
            out.println(num);

        } catch (FileNotFoundException e) {
            System.err.println("Error: UserCount.txt not found. Cannot set User ID.");
            this.userID = -1; // Assign a default/error ID
        } catch (NumberFormatException e) {
            System.err.println("Error: UserCount.txt is corrupted. Contains invalid number format.");
            this.userID = -1;
        } catch (NoSuchElementException e) {
            System.err.println("Error: UserCount.txt is empty.");
            this.userID = -1;
        } finally {
            if (find != null) {
                find.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public int getUserID() {
        return userID;
    }
}
