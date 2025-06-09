package User;

import User.Asnaf.*;

public class Admin extends User{
    private String password;

    public Admin(String name, String phoneNumber, String email, String address, int age, String password) {
        super(name, phoneNumber, email, address, age);
        this.password = password;
    }

    // Approve request (mock logic)
    public boolean approveRequest(Asnaf asnaf) {
        System.out.println("User ID " + asnaf.getUserID() + " has been approved by Admin " + name + ".");
        return true;
    }

    public void distributeFunds(Asnaf asnaf) {
        asnaf.setAmountReceived(500);// will be change
    }

    public boolean login(String inputEmail, String inputPassword) {
        return this.email.equals(inputEmail) && this.password.equals(inputPassword);
    }

    public void sendNotification(String message) {
        System.out.println("Notification to Admin " + name + ": " + message);
    }
}