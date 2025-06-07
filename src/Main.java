import User.User;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("ALI","0123456789","Ali123@gmail.com","AlamatAli",25);
        User u2 = new User("ABU", "0987654321", "ABU123@yahoo.com","AlamatAbu",20);

        System.out.println( "UserID: "+u1.getUserID()+", "+
                            "Name: "+u1.getName()+", "+
                            "Age: "+u1.getAge()+", "+
                            "Phone Number: "+u1.getPhoneNumber()+", "+
                            "Email: "+u1.getEmail()+", "+
                            "Address: "+u1.getAddress());

        System.out.println( "UserID: "+u2.getUserID()+", "+
                            "Name: "+u2.getName()+", "+
                            "Age: "+u2.getAge()+", "+
                            "Phone Number: "+u2.getPhoneNumber()+", "+
                            "Email: "+u2.getEmail()+", "+
                            "Address: "+u2.getAddress());
    }
}
