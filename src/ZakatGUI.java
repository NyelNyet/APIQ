import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import User.User;
import User.Admin;
import User.Asnaf.*;
import User.ZakatPayer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ZakatGUI extends Application {

    private DataManager dm = new DataManager();
    private FileManager fm = new FileManager();
    private Admin hardcodedAdmin;
    private boolean isAdminLoggedIn = false;

    private Stage primaryStage;
    private Scene homepageScene, loginScene, mainScene;
    
    private ListView<Asnaf> asnafListView = new ListView<>();
    private ListView<ZakatPayer> payerListView = new ListView<>();

    private VBox detailsPanel = new VBox(10);
    private Button addAsnafButton = new Button("Add Asnaf (Recipient)");
    private Button addPayerButton = new Button("Add Payer");
    private Button logoutButton = new Button("Admin Logout");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Zakat Management System");
        
        hardcodedAdmin = new Admin("Admin User", "0123456789", "admin@zakat.com", "Admin Office", 40, "password123");

        homepageScene = createHomepageScene();
        loginScene = createLoginScene();
        mainScene = createMainScene();

        primaryStage.setScene(homepageScene);
        primaryStage.show();
    }
    
    private Scene createHomepageScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        
        Label title = new Label("Welcome to the Zakat Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Button adminButton = new Button("Admin Portal");
        adminButton.setPrefSize(150, 40);
        adminButton.setOnAction(e -> changeScene(loginScene));

        Button publicButton = new Button("Public User");
        publicButton.setPrefSize(150, 40);
        publicButton.setOnAction(e -> {
            isAdminLoggedIn = false;
            changeScene(mainScene);
            updateControlsVisibility();
        });
        
        layout.getChildren().addAll(title, adminButton, publicButton);
        return new Scene(layout, 600, 400);
    }

    private Scene createLoginScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        
        Label title = new Label("Admin Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        TextField emailField = new TextField("admin@zakat.com");
        PasswordField passwordField = new PasswordField();
        
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (hardcodedAdmin.login(emailField.getText(), passwordField.getText())) {
                isAdminLoggedIn = true;
                changeScene(mainScene);
                updateControlsVisibility();
            } else {
                errorLabel.setText("Invalid email or password.");
            }
        });

        Button backButton = new Button("Back to Homepage");
        backButton.setOnAction(e -> changeScene(homepageScene));
        
        layout.getChildren().addAll(title, emailField, passwordField, loginButton, errorLabel, backButton);
        return new Scene(layout, 400, 350);
    }
    
    private Scene createMainScene() {
        readAllData();

        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10));

        asnafListView.setItems(FXCollections.observableArrayList(dm.getAsnafList()));
        payerListView.setItems(FXCollections.observableArrayList(dm.getZakatPayerList()));

        asnafListView.setCellFactory(p -> new ListCell<>() {
            @Override
            protected void updateItem(Asnaf item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName() + " (" + item.getTypeOfAsnaf() + ")");
            }
        });
        
        payerListView.setCellFactory(p -> new ListCell<>() {
             @Override
            protected void updateItem(ZakatPayer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });
        
        VBox leftPanel = new VBox(10, new Label("Asnaf (Recipients)"), asnafListView, new Label("Zakat Payers"), payerListView);
        mainLayout.setLeft(leftPanel);

        detailsPanel.setPadding(new Insets(0, 0, 0, 20));
        mainLayout.setCenter(detailsPanel);
        updateDetailsPanel(null); 

        asnafListView.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
            if (val != null) payerListView.getSelectionModel().clearSelection();
            updateDetailsPanel(val);
        });

        payerListView.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
            if (val != null) asnafListView.getSelectionModel().clearSelection();
            updateDetailsPanel(val);
        });
        
        addPayerButton.setOnAction(e -> handleAddPayer());
        addAsnafButton.setOnAction(e -> handleAddAsnaf());
        
        HBox bottomPanel = new HBox(10, addAsnafButton, addPayerButton);
        mainLayout.setBottom(bottomPanel);
        
        Button backButton = new Button("Back to Homepage");
        backButton.setOnAction(e -> changeScene(homepageScene));
        
        logoutButton.setOnAction(e -> {
            isAdminLoggedIn = false;
            changeScene(homepageScene);
            updateControlsVisibility();
        });
        
        HBox topPanel = new HBox(10, backButton, logoutButton);
        mainLayout.setTop(topPanel);

        return new Scene(mainLayout, 800, 600);
    }
    
    private void changeScene(Scene scene) {
        asnafListView.getSelectionModel().clearSelection();
        payerListView.getSelectionModel().clearSelection();
        primaryStage.setScene(scene);
    }
    
    private void updateControlsVisibility() {
        logoutButton.setVisible(isAdminLoggedIn);
    }

    private void updateDetailsPanel(User user) {
        detailsPanel.getChildren().clear();

        if (user == null) {
            detailsPanel.getChildren().add(new Label("Select a person to see details."));
            return;
        }

        Label title = new Label(user.getName());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        detailsPanel.getChildren().add(title);
        
        GridPane grid = new GridPane();
        grid.add(new Label("ID:"), 0, 0); grid.add(new Label(String.valueOf(user.getUserID())), 1, 0);
        grid.add(new Label("Phone:"), 0, 1); grid.add(new Label(user.getPhoneNumber()), 1, 1);
        detailsPanel.getChildren().add(grid);

        if (user instanceof Asnaf) {
            Asnaf asnaf = (Asnaf) user;
            grid.add(new Label("Category:"), 0, 2); grid.add(new Label(asnaf.getTypeOfAsnaf()), 1, 2);
            if (isAdminLoggedIn) {
                detailsPanel.getChildren().add(createAdminActionPanel(asnaf));
            }
        } else if (user instanceof ZakatPayer) {
            grid.add(new Label("Zakat Paid:"), 0, 2); 
            grid.add(new Label("RM " + ((ZakatPayer) user).getZakatAmount()), 1, 2);
        }
    }
    
    private VBox createAdminActionPanel(Asnaf asnaf) {
        VBox adminPanel = new VBox(15);
        Label adminTitle = new Label("Admin Actions");
        adminTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount to transfer");
        
        Button approveButton = new Button("Approve & Transfer");
        approveButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                hardcodedAdmin.approveRequest(asnaf);
                hardcodedAdmin.distributeFunds(asnaf, amount);
                new Alert(AlertType.INFORMATION, "Application for " + asnaf.getName() + " approved.").show();
            } catch (NumberFormatException ex) {
                new Alert(AlertType.ERROR, "Please enter a valid amount.").show();
            } catch (Exception ex) {
                new Alert(AlertType.ERROR, "An unexpected error occurred: " + ex.getMessage()).show();
            }
        });
        
        Button rejectButton = new Button("Reject Application");
        rejectButton.setOnAction(e -> {
            asnaf.sendNotification(asnaf.getName(), "Your application has been rejected.");
            new Alert(AlertType.WARNING, "Application for " + asnaf.getName() + " has been rejected.").show();
        });
        
        adminPanel.getChildren().addAll(adminTitle, new Label("Fund Distribution:"), amountField, new HBox(10, approveButton, rejectButton));
        return adminPanel;
    }

    private void handleAddPayer() {
        Dialog<ZakatPayer> dialog = new Dialog<>();
        GridPane grid = new GridPane();
        TextField nameField = new TextField(), phoneField = new TextField(), emailField = new TextField(), addressField = new TextField(), ageField = new TextField(), zakatField = new TextField();
        grid.add(new Label("Name:"), 0, 0); grid.add(nameField, 1, 0);
        grid.add(new Label("Phone:"), 0, 1); grid.add(phoneField, 1, 1);
        grid.add(new Label("Age:"), 0, 2); grid.add(ageField, 1, 2);
        grid.add(new Label("Zakat Amount:"), 0, 3); grid.add(zakatField, 1, 3);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    return new ZakatPayer(nameField.getText(), phoneField.getText(), emailField.getText(), addressField.getText(), Integer.parseInt(ageField.getText()), Double.parseDouble(zakatField.getText()));
                } catch (NumberFormatException e) {
                    new Alert(AlertType.ERROR, "Invalid number format for Age or Zakat Amount.").showAndWait();
                    return null;
                } catch (IllegalArgumentException e) {
                    new Alert(AlertType.ERROR, "Invalid input: " + e.getMessage()).showAndWait();
                    return null;
                }
            }
            return null;
        });
        
        Optional<ZakatPayer> result = dialog.showAndWait();
        result.ifPresent(payer -> {
            fm.writeToFile(Optional.empty(), Optional.of(payer));
            payerListView.getItems().add(payer);
            dm.addZakatPayer(payer);
        });
    }

    private void handleAddAsnaf() {
        Dialog<Asnaf> dialog = new Dialog<>();
        TextField nameField = new TextField("New Asnaf");
        dialog.getDialogPane().setContent(new VBox(10, new Label("Name: (Simplified for demo)"), nameField));
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(btn -> (btn == ButtonType.OK) ? new AlFuqara(nameField.getText(), "0100000000", "new@email.com", "address", 30, 1000, 800, "family", "AlFuqara") : null);

        Optional<Asnaf> result = dialog.showAndWait();
        result.ifPresent(asnaf -> {
            fm.writeToFile(Optional.of(asnaf), Optional.empty());
            asnafListView.getItems().add(asnaf);
            dm.addAsnaf(asnaf);
        });
    }

    private void readAllData() {
        if (!dm.getAsnafList().isEmpty()) return;

        List<String[]> allData = fm.allFileReader();
        for (String[] aData : allData) {
            try {
                if (aData.length < 7) throw new IllegalArgumentException("Data row has insufficient columns.");

                switch (aData.length) {
                    case 11:
                        if (aData[10].equals("AlFuqara")) dm.addAsnaf(new AlFuqara(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10]));
                        else if (aData[10].equals("AlMasakin")) dm.addAsnaf(new AlMasakin(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10]));
                        break;
                    case 12:
                        switch (aData[11]) {
                            case "AlAmilunaAlaiha": dm.addAsnaf(new AlAmilunaAlaiha(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[11])); break;
                            case "AlGharimoon": dm.addAsnaf(new AlGharimoon(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], Double.parseDouble(aData[10]), aData[11])); break;
                            case "AlMualafaQulubuhum": dm.addAsnaf(new AlMualafaQulubuhum(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], LocalDate.parse(aData[10]), aData[11])); break;
                            case "AlRiqab": dm.addAsnaf(new AlRiqab(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[11])); break;
                            case "FiSabiLillah": dm.addAsnaf(new FiSabiLillah(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[11])); break;
                            case "IbnAlSabil": dm.addAsnaf(new IbnAlSabil(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[9], aData[11])); break;
                        }
                        break;
                    case 7:
                        dm.addZakatPayer(new ZakatPayer(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6])));
                        break;
                }
            } catch (NumberFormatException | DateTimeParseException e) {
                System.err.println("Skipping corrupted data row due to parsing error: " + String.join(";", aData));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Skipping invalid data row: " + e.getMessage() + " | Data: " + String.join(";", aData));
            }
        }
    }
}
