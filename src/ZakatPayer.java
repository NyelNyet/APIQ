import java.util.ArrayList;
import java.util.List;

//TEMPORARY ONLY JUST TO MAKE ADMIN PART WORKING
//LATER NEED TO PUT THE MAIN ZAKATPAYER CODE
public class ZakatPayer {
    private int userId;
    private String name;
    private String email;
    private String phone = "N/A";
    private String address = "N/A";
    private double salary = 0.0;
    private List<Payment> payments = new ArrayList<>();

    public ZakatPayer(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public double getSalary() { return salary; }

    public List<Payment> getPayments() { return payments; }
}


