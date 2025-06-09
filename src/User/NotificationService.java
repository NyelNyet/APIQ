package User;
public interface NotificationService {

    default void sendNotification(String name,String msg){
        System.out.println("Notification sent to "+name+": "+msg);
    }

}
