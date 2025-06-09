package User;
import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private double amount;
    private LocalDate date;

    public Payment(int iD, int userID, double amount) {
        this.paymentId = "PAY" + userID;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public double getZakatAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return date;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
