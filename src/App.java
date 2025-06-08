import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import User.Asnaf.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    DataManager dm = new DataManager();
    FileManager fm = new FileManager();
    @Override
    public void start(Stage primaryStage) {
        readAllData();
        dm.getAllAsnafDetail();

        // ListView to display existing users
        ListView<Asnaf> userListView = new ListView<>();
        userListView.getItems().setAll(dm.getAsnafList()); // Populate ListView

        // Layout
        VBox layout = new VBox(10, new Label("Existing Users:"), userListView);
        layout.setSpacing(10);

        // Scene setup
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Display");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void readAllData(){

        List<String[]> allData = fm.allFileReader();

        for(String[] aData : allData){
            switch (aData.length) {
                case 11:{
                    if(aData[10].equals("AlFuqara")){
                        dm.addAsnaf(new AlFuqara(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9]));
                    }else if(aData[10].equals("AlMasakin")){
                        dm.addAsnaf(new AlMasakin(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9]));
                    }
                }break;

                case 12:{
                    switch (aData[11]) {
                        case "AlAmilunaAlaiha":dm.addAsnaf(new AlAmilunaAlaiha(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[10]));break;
                        case "AlGharimoon":dm.addAsnaf(new AlGharimoon(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], Double.parseDouble(aData[10]), aData[11]));break;
                        case "AlMualafaQulubuhum":dm.addAsnaf(new AlMualafaQulubuhum(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], LocalDate.parse(aData[10]), aData[11]));break;
                        case "AlRiqab":dm.addAsnaf(new AlRiqab(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[10]));break;
                        case "FiSabiLillah":dm.addAsnaf(new FiSabiLillah(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[10]));break;
                        case "IbnAlSabil":dm.addAsnaf(new IbnAlSabil(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[10]));break;
                    }
                }break;
            }
        }
    }
}
