//TEMPORARY ONLY JUST TO MAKE ADMIN PART WORKING
//LATER NEED TO PUT THE MAIN PAYMENT CODE 
public class Payment {
    private double amount;
    private String date;

    public Payment(double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() { return amount; }
    public String getDate() { return date; }
}
