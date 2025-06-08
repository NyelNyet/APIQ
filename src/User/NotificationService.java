package User;
public interface NotificationService {

    default void sendEmail(String name){
        System.out.println("Email sent to "+name+".");
    };

    default void sendSMS(String name){
        System.out.println("Email sent to "+name+".");
    };

}
