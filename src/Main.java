import java.time.LocalDate;

import User.User;
import User.Asnaf.*;

public class Main {
    public static void main(String[] args) {
        User f = new AlFuqara("MINAH","0111111111","minah123@gmail.com","Rumah Minah",25,1500,1000,"5 total family members");
        User m = new AlMasakin("AHMAD","0122222222","ahmad123@gmail.com","Rumah Ahmad",30,2000,1500,"3 total family members");
        User a = new AlAmilunaAlaiha("ABU","0133333333","zakatagency123@gmail.com","Rumah Abu",40,3000,2500,"5 total family members","IIUM Zakat Agency");
        User mq = new AlMualafaQulubuhum("ALI","0144444444","mualafa123@gmail.com","Rumah Ali",35,2500,2000,"4 total family members",LocalDate.now());
        User ar = new AlRiqab("KHAIRUL","0155555555","riqab123@gmail.com","Rumah Khairul",28,2200,1800,"2 total family members","Debt Slavery");
        User ag = new AlGharimoon("ZAIN","0166666666","gharimoon123@gmail.com","Rumah Zain",32,2700,2200,"6 total family members",15000);
        User fs = new FiSabiLillah("SITI","0177777777","fi_sabilillah123@gmail.com","Rumah Siti",29,2500,2000,"4 total family members","Dakwah");

        System.out.println(f.toString()+'\n');
        System.out.println(m.toString()+'\n');
        System.out.println(a.toString()+'\n');
        System.out.println(mq.toString()+'\n');
        System.out.println(ar.toString()+'\n');
        System.out.println(ag.toString()+'\n');
        System.out.println(fs.toString()+'\n');
    }
}
