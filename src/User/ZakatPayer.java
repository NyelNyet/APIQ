package User;//done by 2418289_ikhwan

public class ZakatPayer extends User {
    private double zakatAmount;

    // Constructor to initialize user details and zakat amount
    public ZakatPayer(String name, String phoneNumber, String email, String address, int age, double zakatAmount) {
        super(name, phoneNumber, email, address, age);
        if (zakatAmount < 0) {
            throw new IllegalArgumentException("Initial Zakat amount cannot be negative.");
        }
        this.zakatAmount = zakatAmount;
    }

    // Method to submit a zakat payment and update the total
    public boolean submitPayment(double amount) {
        if (amount <= 0) {
            System.err.println("Invalid payment amount. Amount must be positive.");
            return false;
        }
        this.zakatAmount += amount;
        System.out.println("Zakat payment of RM" + amount + " submitted successfully.");
        return true;
    }

    public double getZakatAmount() {
        return zakatAmount;
    }

    public void setZakatAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Zakat amount cannot be negative.");
        }
        this.zakatAmount = amount;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", phoneNumber: " + phoneNumber +
                ", email: " + email +
                ", address: " + address +
                ", age: " + age +
                ", zakatAmount: " + zakatAmount;
    }
}
