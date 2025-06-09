import java.time.LocalDate;
import java.util.Optional;

import User.*;
import User.Asnaf.*;

//Methods Testing Purposes//
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
        User zp = new ZakatPayer("ZAKAT PAYER","0199999999","zakatpayer123@gmail.com","Rumah Zakat Payer",30,3000);
        User zp2 = new ZakatPayer("ZAKAT PAYER 2","0200000000","zakatpayer2@gmail.com","Rumah Zakat Payer 2",32,3500);

        dm.addAsnaf((Asnaf) f1);
        dm.addAsnaf((Asnaf) f2);
        dm.addAsnaf((Asnaf) m);
        dm.addAsnaf((Asnaf) a);
        dm.addAsnaf((Asnaf) mq);
        dm.addAsnaf((Asnaf) ar);
        dm.addAsnaf((Asnaf) ag);
        dm.addAsnaf((Asnaf) fs);
        dm.addAsnaf((Asnaf) i);
        dm.addZakatPayer((ZakatPayer) zp);
        dm.addZakatPayer((ZakatPayer) zp2);

        fm.writeToFile(Optional.of((Asnaf) f1),Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) f2),Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) m), Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) a), Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) mq), Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) ar), Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) ag), Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) fs), Optional.empty());
        fm.writeToFile(Optional.of((Asnaf) i), Optional.empty());
        fm.writeToFile(Optional.empty(), Optional.of((ZakatPayer) zp));
        fm.writeToFile(Optional.empty(), Optional.of((ZakatPayer) zp2));

        dm.getAllAsnafDetail();
        dm.getAllZakatPayerDetail();

        f2.sendNotification(f2.getName(), "Here's your moneyh");
        zp.sendNotification(zp.getName(), "Rich AF");
        
    }
}
