package User;//done by 2413801_FARIS
public interface NotificationService {

    default void sendNotification(String name,String msg){
        System.out.println("Notification sent to "+name+": "+msg);
    }

}
