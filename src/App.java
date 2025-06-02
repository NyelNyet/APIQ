import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button but1 = new Button("Hello");
        but1.setOnAction(x -> System.out.println(but1.getText()));
        StackPane root = new StackPane();
        root.getChildren().addAll(but1);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
