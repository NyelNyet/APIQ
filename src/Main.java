import java.time.LocalDate;

import User.*;
import User.Asnaf.*;

public class Main implements NotificationService{
    public static void main(String[] args) {
        DataManager dm = new DataManager();
        FileManager fm = new FileManager();
        User f1 = new AlFuqara("MINAH","0111111111","minah123@gmail.com","Rumah Minah",25,1500,1000,"5 total family members","AlFuqara");
        User f2 = new AlFuqara("AHMAD","0111111112","ahmad123@gmail.com","Rumah Ahmad",30,2000,1500,"3 total family members","AlFuqara");
        User m = new AlMasakin("AHMAD","0122222222","ahmad123@gmail.com","Rumah Ahmad",30,2000,1500,"3 total family members","AlMasakin");
        User a = new AlAmilunaAlaiha("ABU","0133333333","zakatagency123@gmail.com","Rumah Abu",40,3000,2500,"5 total family members","IIUM Zakat Agency","AlAmilunaAlaiha");
        User mq = new AlMualafaQulubuhum("ALI","0144444444","mualafa123@gmail.com","Rumah Ali",35,2500,2000,"4 total family members",LocalDate.now(),"AlMualafaQulubuhum");
        User ar = new AlRiqab("KHAIRUL","0155555555","riqab123@gmail.com","Rumah Khairul",28,2200,1800,"2 total family members","Debt Slavery","AlRiqab");
        User ag = new AlGharimoon("ZAIN","0166666666","gharimoon123@gmail.com","Rumah Zain",32,2700,2200,"6 total family members",15000,"AlGharimoon");
        User fs = new FiSabiLillah("SITI","0177777777","fi_sabilillah123@gmail.com","Rumah Siti",29,2500,2000,"4 total family members","Dakwah","FiSabiLillah");
        User i = new IbnAlSabil("FAIZ","0188888888","faiz123@gmail.com","Rumah Faiz",27,2400,1900,"4 total family members","Travel","IbnAlSabil");

        dm.addAsnaf((Asnaf) f1);
        dm.addAsnaf((Asnaf) f2);
        dm.addAsnaf((Asnaf) m);
        dm.addAsnaf((Asnaf) a);
        dm.addAsnaf((Asnaf) mq);
        dm.addAsnaf((Asnaf) ar);
        dm.addAsnaf((Asnaf) ag);
        dm.addAsnaf((Asnaf) fs);
        dm.addAsnaf((Asnaf) i);

        fm.writeToFile((Asnaf) f1);
        fm.writeToFile((Asnaf) f2);
        fm.writeToFile((Asnaf) m);
        fm.writeToFile((Asnaf) a);
        fm.writeToFile((Asnaf) mq);
        fm.writeToFile((Asnaf) ar);
        fm.writeToFile((Asnaf) ag);
        fm.writeToFile((Asnaf) fs);
        fm.writeToFile((Asnaf) i);

        dm.getAllAsnafDetail();

        f2.sendEmail(f2.getName());
        
    }
}
