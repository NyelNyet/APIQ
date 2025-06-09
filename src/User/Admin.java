package User;

import User.Asnaf.*;
//Done by Fauzi (2412657)
public class Admin extends User {
    private String password;

    public Admin(String name, String phoneNumber, String email, String address, int age, String password) {
        super(name, phoneNumber, email, address, age);
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.password = password;
    }

    public boolean approveRequest(Asnaf asnaf) {
        if (asnaf == null) {
            System.err.println("Approval failed: Asnaf object cannot be null.");
            return false;
        }
        System.out.println("User ID " + asnaf.getUserID() + " has been approved by Admin " + name + ".");
        return true;
    }

    public void distributeFunds(Asnaf asnaf, double amount) {
        if (asnaf == null) {
            throw new IllegalArgumentException("Asnaf cannot be null for fund distribution.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Distribution amount cannot be negative.");
        }
        asnaf.setAmountReceived(amount);
    }

    public boolean login(String inputEmail, String inputPassword) {
        if (inputEmail == null || inputPassword == null) {
            return false;
        }
        return this.email.equals(inputEmail) && this.password.equals(inputPassword);
    }

    @Override
    public void sendNotification(String name, String msg) {
        System.out.println("Notification to Admin " + this.name + " regarding " + name + ": " + msg);
    }
}
