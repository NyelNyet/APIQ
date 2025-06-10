import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ZakatGUI extends Application {

    private DataManager dataManager = new DataManager();
    private FileManager fileManager = new FileManager();
    
    private Admin hardcodedAdmin;
    private boolean isAdminLoggedIn = false;

    private Stage primaryStage;
    private Scene homepageScene, loginScene, mainScene;
    
    private ListView<Asnaf> asnafListView = new ListView<>();
    private ListView<ZakatPayer> payerListView = new ListView<>();

    private VBox detailsPanel = new VBox(15);
    
    private PasswordField passwordField = new PasswordField();
    private Button backButton = new Button("Back to Homepage");
    private Button logoutButton = new Button("Admin Logout");
    private Button generateReportButton = new Button("Generate System Report");
    private Label reportLabel = new Label("SYSTEM REPORTS");

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

        primaryStage.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != mainScene) {
                asnafListView.getSelectionModel().clearSelection();
                payerListView.getSelectionModel().clearSelection();
            }
        });

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
        adminButton.setOnAction(e -> primaryStage.setScene(loginScene));

        Button publicButton = new Button("Public User");
        publicButton.setPrefSize(150, 40);
        publicButton.setOnAction(e -> {
            isAdminLoggedIn = false;
            primaryStage.setScene(mainScene);
            updateControlsVisibility();
        });
        
        layout.getChildren().addAll(title, adminButton, publicButton);
        return new Scene(layout, 600, 400);
    }

    private Scene createLoginScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        
        Label title = new Label("Admin Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        TextField emailField = new TextField("admin@zakat.com");
        passwordField.setPromptText("Password");
        
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (hardcodedAdmin.login(emailField.getText(), passwordField.getText())) {
                isAdminLoggedIn = true;
                primaryStage.setScene(mainScene);
                updateControlsVisibility();
            } else {
                errorLabel.setText("Invalid email or password.");
            }
        });

        Button backBtn = new Button("Back to Homepage");
        backBtn.setOnAction(e -> primaryStage.setScene(homepageScene));
        
        layout.getChildren().addAll(title, emailField, passwordField, loginButton, errorLabel, backBtn);
        return new Scene(layout, 400, 350);
    }
    
    private Scene createMainScene() {
        readAllData();
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10));

        asnafListView.setItems(FXCollections.observableArrayList(dataManager.getAsnafList()));
        payerListView.setItems(FXCollections.observableArrayList(dataManager.getZakatPayerList()));

        asnafListView.setCellFactory(p -> new ListCell<>() {
            @Override protected void updateItem(Asnaf item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName() + " (" + item.getTypeOfAsnaf() + ")");
            }
        });
        payerListView.setCellFactory(p -> new ListCell<>() {
             @Override protected void updateItem(ZakatPayer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });
        
        VBox leftPanel = new VBox(10, 
            new Label("Asnaf (Recipients)"), asnafListView, 
            new Label("Zakat Payers"), payerListView,
            reportLabel, generateReportButton
        );
        leftPanel.setPrefWidth(350);
        mainLayout.setLeft(leftPanel);

        detailsPanel.setPadding(new Insets(0, 10, 0, 20));
        mainLayout.setCenter(new ScrollPane(detailsPanel));
        updateDetailsPanel(null); 

        asnafListView.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
            if (val != null) payerListView.getSelectionModel().clearSelection();
            updateDetailsPanel(val);
        });
        payerListView.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
            if (val != null) asnafListView.getSelectionModel().clearSelection();
            updateDetailsPanel(val);
        });
        generateReportButton.setOnAction(e -> {
            asnafListView.getSelectionModel().clearSelection();
            payerListView.getSelectionModel().clearSelection();
            displayReportInDetailsPanel();
        });
        
        Button addAsnafButton = new Button("Add New Asnaf");
        addAsnafButton.setOnAction(e -> handleAddAsnaf());
        Button addPayerButton = new Button("Add New Payer");
        addPayerButton.setOnAction(e -> handleAddPayer());
        
        HBox bottomPanel = new HBox(10, addAsnafButton, addPayerButton);
        bottomPanel.setAlignment(Pos.CENTER_RIGHT);
        bottomPanel.setPadding(new Insets(10, 0, 0, 0));
        mainLayout.setBottom(bottomPanel);
        
        backButton.setOnAction(e -> primaryStage.setScene(homepageScene));
        logoutButton.setOnAction(e -> {
            isAdminLoggedIn = false;
            passwordField.clear();
            primaryStage.setScene(homepageScene);
        });
        
        HBox topPanel = new HBox(10, backButton, logoutButton);
        mainLayout.setTop(topPanel);

        return new Scene(mainLayout, 950, 700);
    }
    
    private void updateControlsVisibility() {
        logoutButton.setVisible(isAdminLoggedIn);
        backButton.setVisible(!isAdminLoggedIn);
        reportLabel.setVisible(isAdminLoggedIn);
        generateReportButton.setVisible(isAdminLoggedIn);
    }

    private void updateDetailsPanel(User user) {
        detailsPanel.getChildren().clear();

        if (user == null) {
            detailsPanel.getChildren().add(new Label("Select a person from a list to see their details."));
            return;
        }

        Label title = new Label(user.getName());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        
        int rowIndex = 0;
        grid.add(new Label("ID:"), 0, rowIndex); grid.add(new Label(String.valueOf(user.getUserID())), 1, rowIndex++);
        grid.add(new Label("Phone:"), 0, rowIndex); grid.add(new Label(user.getPhoneNumber()), 1, rowIndex++);
        grid.add(new Label("Email:"), 0, rowIndex); grid.add(new Label(user.getEmail()), 1, rowIndex++);
        grid.add(new Label("Address:"), 0, rowIndex); grid.add(new Label(user.getAddress()), 1, rowIndex++);
        grid.add(new Label("Age:"), 0, rowIndex); grid.add(new Label(String.valueOf(user.getAge())), 1, rowIndex++);
        
        VBox mainContent = new VBox(10, title, grid);
        detailsPanel.getChildren().add(mainContent);

        if (user instanceof Asnaf) {
            Asnaf asnaf = (Asnaf) user;
            grid.add(new Label("Category:"), 0, rowIndex); grid.add(new Label(asnaf.getTypeOfAsnaf()), 1, rowIndex++);
            grid.add(new Label("Monthly Income:"), 0, rowIndex); grid.add(new Label(String.format("RM %.2f", asnaf.getMonthlyIncome())), 1, rowIndex++);
            grid.add(new Label("Monthly Expenses:"), 0, rowIndex); grid.add(new Label(String.format("RM %.2f", asnaf.getMonthlyExpenses())), 1, rowIndex++);
            grid.add(new Label("Family Info:"), 0, rowIndex); grid.add(new Label(asnaf.getFamilyInformation()), 1, rowIndex++);
            grid.add(new Label("Application Date:"), 0, rowIndex); grid.add(new Label(asnaf.getDateOfApplication().format(DateTimeFormatter.ofPattern("dd MMM uuuu"))), 1, rowIndex++);
            
            if (asnaf instanceof AlAmilunaAlaiha) {grid.add(new Label("Zakat Agency:"), 0, rowIndex); grid.add(new Label(((AlAmilunaAlaiha) asnaf).getZakatAgency()), 1, rowIndex++);}
            else if (asnaf instanceof AlGharimoon) {grid.add(new Label("Debt Amount:"), 0, rowIndex); grid.add(new Label(String.format("RM %.2f", ((AlGharimoon) asnaf).getDebtAmount())), 1, rowIndex++);}
            else if (asnaf instanceof AlMualafaQulubuhum) {grid.add(new Label("Conversion Date:"), 0, rowIndex); grid.add(new Label(((AlMualafaQulubuhum) asnaf).getDateOfConversion().format(DateTimeFormatter.ofPattern("dd MMM uuuu"))), 1, rowIndex++);}
            else if (asnaf instanceof AlRiqab) {grid.add(new Label("Type of Captivity:"), 0, rowIndex); grid.add(new Label(((AlRiqab) asnaf).getTypeOfCaptivity()), 1, rowIndex++);}
            else if (asnaf instanceof FiSabiLillah) {grid.add(new Label("Activity:"), 0, rowIndex); grid.add(new Label(((FiSabiLillah) asnaf).getAcitivityInTheCauseOfAllah()), 1, rowIndex++);}
            else if (asnaf instanceof IbnAlSabil) {grid.add(new Label("Reason Stranded:"), 0, rowIndex); grid.add(new Label(((IbnAlSabil) asnaf).getReasonForBeingStranded()), 1, rowIndex++);}
            
            if (isAdminLoggedIn) {
                detailsPanel.getChildren().add(createAdminActionPanel(asnaf));
            }
        } else if (user instanceof ZakatPayer) {
            grid.add(new Label("Zakat Paid:"), 0, rowIndex); grid.add(new Label(String.format("RM %.2f", ((ZakatPayer) user).getZakatAmount())), 1, rowIndex++);
        }
    }
    
    private void displayReportInDetailsPanel() {
        detailsPanel.getChildren().clear();
        Label title = new Label("System Data Report");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        VBox reportContent = new VBox(10);
        reportContent.setPadding(new Insets(10, 0, 0, 0));

        Label payerHeader = new Label("Payer Summary");
        payerHeader.setFont(Font.font(null, FontWeight.BOLD, 14));
        Label payerCountLabel = new Label("Total Zakat Payers: " + dataManager.getZakatPayerList().size());
        Label totalZakatLabel = new Label(String.format("Total Zakat Collected: RM %.2f", dataManager.getZakatPayerList().stream().mapToDouble(ZakatPayer::getZakatAmount).sum()));
        
        Label asnafHeader = new Label("Asnaf (Recipient) Summary");
        asnafHeader.setFont(Font.font(null, FontWeight.BOLD, 14));
        Label asnafCountLabel = new Label("Total Asnaf Registered: " + dataManager.getAsnafList().size());
        
        VBox asnafBreakdownBox = new VBox(5);
        asnafBreakdownBox.getChildren().add(new Label("Breakdown by Category:"));
        
        Map<String, Long> asnafByCategory = dataManager.getAsnafList().stream().collect(Collectors.groupingBy(Asnaf::getTypeOfAsnaf, Collectors.counting()));
        asnafByCategory.forEach((category, count) -> asnafBreakdownBox.getChildren().add(new Label("   - " + category + ": " + count)));

        reportContent.getChildren().addAll(payerHeader, payerCountLabel, totalZakatLabel, new Label(), asnafHeader, asnafCountLabel, asnafBreakdownBox);
        detailsPanel.getChildren().addAll(title, reportContent);
    }

    private VBox createAdminActionPanel(Asnaf asnaf) {
        VBox adminPanel = new VBox(10);
        adminPanel.setPadding(new Insets(20, 0, 0, 0));
        Label adminTitle = new Label("Admin Actions");
        adminTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount received");
        
        Button approveButton = new Button("Approve Application");
        approveButton.setOnAction(e -> {
            if (isInvalidDouble(amountField, "Amount") || isInvalidPositive(amountField, "Amount")) {
                return;
            }
            double amount = Double.parseDouble(amountField.getText());
            hardcodedAdmin.approveRequest(asnaf);
            asnaf.setAmountReceived(amount);
            showAlert(AlertType.INFORMATION, "Success", "Application for " + asnaf.getName() + " approved.");
            asnafListView.getItems().remove(asnaf);
            dataManager.getAsnafList().remove(asnaf);
            updateDetailsPanel(null);
        });
        
        Button rejectButton = new Button("Reject Application");
        rejectButton.setOnAction(e -> {
            asnaf.sendNotification(asnaf.getName(), "Your application has been rejected by an administrator.");
            showAlert(AlertType.WARNING, "Rejected", "Application for " + asnaf.getName() + " has been rejected.");
            asnafListView.getItems().remove(asnaf);
            dataManager.getAsnafList().remove(asnaf);
            updateDetailsPanel(null);
        });
        
        adminPanel.getChildren().addAll(adminTitle, new Label("Fund Distribution:"), amountField, new HBox(10, approveButton, rejectButton));
        return adminPanel;
    }

    private void handleAddPayer() {
        Dialog<ZakatPayer> dialog = new Dialog<>();
        dialog.setTitle("Add New Zakat Payer");
        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10); grid.setPadding(new Insets(20));
        
        TextField nameField=new TextField(), phoneField=new TextField(), emailField=new TextField(), 
                  addressField=new TextField(), ageField=new TextField(), zakatField=new TextField();
        grid.add(new Label("Name:"), 0, 0); grid.add(nameField, 1, 0);
        grid.add(new Label("Phone:"), 0, 1); grid.add(phoneField, 1, 1);
        grid.add(new Label("Email:"), 0, 2); grid.add(emailField, 1, 2);
        grid.add(new Label("Address:"), 0, 3); grid.add(addressField, 1, 3);
        grid.add(new Label("Age:"), 0, 4); grid.add(ageField, 1, 4);
        grid.add(new Label("Zakat Amount:"), 0, 5); grid.add(zakatField, 1, 5);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                if (isInvalidNonEmpty(nameField, "Name") || isInvalidNonEmpty(phoneField, "Phone") ||
                    isInvalidInteger(ageField, "Age") || isInvalidPositive(ageField, "Age") ||
                    isInvalidDouble(zakatField, "Zakat Amount") || isInvalidPositive(zakatField, "Zakat Amount")) {
                    return null; // Validation failed, return null to keep dialog open
                }
                try {
                    return new ZakatPayer(nameField.getText(), phoneField.getText(), emailField.getText(), addressField.getText(), Integer.parseInt(ageField.getText()), Double.parseDouble(zakatField.getText()));
                } catch (IllegalArgumentException ex) {
                    showAlert(AlertType.ERROR, "Creation Failed", ex.getMessage());
                }
            }
            return null;
        });
        Optional<ZakatPayer> result = dialog.showAndWait();
        result.ifPresent(payer -> {
            fileManager.writeToFile(Optional.empty(), Optional.of(payer));
            payerListView.getItems().add(payer);
            dataManager.addZakatPayer(payer);
        });
    }

    private void handleAddAsnaf() {
        Dialog<Asnaf> dialog = new Dialog<>();
        dialog.setTitle("Add New Asnaf");
        dialog.setHeaderText("Enter details for the new recipient.");
        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10); grid.setPadding(new Insets(20));

        TextField nameField=new TextField(), phoneField=new TextField(), emailField=new TextField(),
                  addressField=new TextField(), ageField=new TextField(), incomeField=new TextField(),
                  expensesField=new TextField(), familyInfoField=new TextField();
        grid.add(new Label("Name:"), 0, 0); grid.add(nameField, 1, 0);
        grid.add(new Label("Phone:"), 0, 1); grid.add(phoneField, 1, 1);
        grid.add(new Label("Email:"), 0, 2); grid.add(emailField, 1, 2);
        grid.add(new Label("Address:"), 0, 3); grid.add(addressField, 1, 3);
        grid.add(new Label("Age:"), 0, 4); grid.add(ageField, 1, 4);
        grid.add(new Label("Monthly Income:"), 0, 5); grid.add(incomeField, 1, 5);
        grid.add(new Label("Monthly Expenses:"), 0, 6); grid.add(expensesField, 1, 6);
        grid.add(new Label("Family Info:"), 0, 7); grid.add(familyInfoField, 1, 7);

        ComboBox<String> typeComboBox = new ComboBox<>(FXCollections.observableArrayList(
            "AlFuqara", "AlMasakin", "AlAmilunaAlaiha", "AlGharimoon", 
            "AlMualafaQulubuhum", "AlRiqab", "FiSabiLillah", "IbnAlSabil"
        ));
        grid.add(new Label("Category:"), 0, 8); grid.add(typeComboBox, 1, 8);
        
        VBox specificFieldsBox = new VBox(10);
        grid.add(specificFieldsBox, 0, 9, 2, 1);

        TextField zakatAgencyField=new TextField(), debtAmountField=new TextField(),
                  captivityField=new TextField(), activityField=new TextField(), reasonField=new TextField();
        DatePicker conversionDateField = new DatePicker();

        typeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            specificFieldsBox.getChildren().clear();
            if (newVal == null) return;
            switch (newVal) {
                case "AlAmilunaAlaiha": specificFieldsBox.getChildren().addAll(new Label("Zakat Agency:"), zakatAgencyField); break;
                case "AlGharimoon": specificFieldsBox.getChildren().addAll(new Label("Debt Amount:"), debtAmountField); break;
                case "AlMualafaQulubuhum": specificFieldsBox.getChildren().addAll(new Label("Date of Conversion:"), conversionDateField); break;
                case "AlRiqab": specificFieldsBox.getChildren().addAll(new Label("Type of Captivity:"), captivityField); break;
                case "FiSabiLillah": specificFieldsBox.getChildren().addAll(new Label("Activity in Cause of Allah:"), activityField); break;
                case "IbnAlSabil": specificFieldsBox.getChildren().addAll(new Label("Reason for being Stranded:"), reasonField); break;
            }
        });
        typeComboBox.getSelectionModel().selectFirst();

        dialog.getDialogPane().setContent(new ScrollPane(grid));
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                if (isInvalidNonEmpty(nameField, "Name") || isInvalidInteger(ageField, "Age") || 
                    isInvalidDouble(incomeField, "Income") || isInvalidDouble(expensesField, "Expenses")) {
                    return null;
                }

                String type = typeComboBox.getValue();
                if (type.equals("AlGharimoon") && isInvalidDouble(debtAmountField, "Debt Amount")) return null;
                if (type.equals("AlMualafaQulubuhum") && isInvalidDate(conversionDateField, "Conversion Date")) return null;

                try {
                    String name=nameField.getText(), phone=phoneField.getText(), email=emailField.getText(),
                           address=addressField.getText(), familyInfo=familyInfoField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    double income = Double.parseDouble(incomeField.getText()), expenses = Double.parseDouble(expensesField.getText());

                    switch(type) {
                        case "AlFuqara": return new AlFuqara(name, phone, email, address, age, income, expenses, familyInfo, type);
                        case "AlMasakin": return new AlMasakin(name, phone, email, address, age, income, expenses, familyInfo, type);
                        case "AlAmilunaAlaiha": return new AlAmilunaAlaiha(name, phone, email, address, age, income, expenses, familyInfo, zakatAgencyField.getText(), type);
                        case "AlGharimoon": return new AlGharimoon(name, phone, email, address, age, income, expenses, familyInfo, Double.parseDouble(debtAmountField.getText()), type);
                        case "AlMualafaQulubuhum": return new AlMualafaQulubuhum(name, phone, email, address, age, income, expenses, familyInfo, conversionDateField.getValue(), type);
                        case "AlRiqab": return new AlRiqab(name, phone, email, address, age, income, expenses, familyInfo, captivityField.getText(), type);
                        case "FiSabiLillah": return new FiSabiLillah(name, phone, email, address, age, income, expenses, familyInfo, activityField.getText(), type);
                        case "IbnAlSabil": return new IbnAlSabil(name, phone, email, address, age, income, expenses, familyInfo, reasonField.getText(), type);
                    }
                } catch (Exception ex) {
                    showAlert(AlertType.ERROR, "Creation Failed", "An unexpected error occurred: " + ex.getMessage());
                }
            }
            return null;
        });

        Optional<Asnaf> result = dialog.showAndWait();
        result.ifPresent(asnaf -> {
            fileManager.writeToFile(Optional.of(asnaf), Optional.empty());
            asnafListView.getItems().add(asnaf);
            dataManager.addAsnaf(asnaf);
        });
    }
    
    private void readAllData() {
        if(!dataManager.getAsnafList().isEmpty()) return;
        List<String[]> allData = fileManager.allFileReader();
        for (String[] aData : allData) {
            try {
                if (aData.length < 7) {
                    throw new IllegalArgumentException("Data row has insufficient columns.");
                }
                
                if(aData.length >= 11 && (aData[10].equals("AlFuqara") || aData[10].equals("AlMasakin"))) {
                    if (aData[10].equals("AlFuqara")) dataManager.addAsnaf(new AlFuqara(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10]));
                    else if (aData[10].equals("AlMasakin")) dataManager.addAsnaf(new AlMasakin(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10]));
                } else if(aData.length == 12) {
                     switch (aData[11]) {
                         case "AlAmilunaAlaiha": dataManager.addAsnaf(new AlAmilunaAlaiha(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10], aData[11])); break;
                         case "AlGharimoon": dataManager.addAsnaf(new AlGharimoon(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], Double.parseDouble(aData[10]), aData[11])); break;
                         case "AlMualafaQulubuhum": dataManager.addAsnaf(new AlMualafaQulubuhum(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], LocalDate.parse(aData[10]), aData[11])); break;
                         case "AlRiqab": dataManager.addAsnaf(new AlRiqab(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10], aData[11])); break;
                         case "FiSabiLillah": dataManager.addAsnaf(new FiSabiLillah(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10], aData[11])); break;
                         case "IbnAlSabil": dataManager.addAsnaf(new IbnAlSabil(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6]), Double.parseDouble(aData[7]), aData[8], aData[10], aData[11])); break;
                    }
                } else if (aData.length == 7) {
                    dataManager.addZakatPayer(new ZakatPayer(aData[1], aData[2], aData[3], aData[4], Integer.parseInt(aData[5]), Double.parseDouble(aData[6])));
                }
            } catch (NumberFormatException | DateTimeParseException e) {
                System.err.println("Skipping corrupted data row due to parsing error: " + String.join(";", aData) + " | " + e.getMessage());
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Skipping invalid data row: " + e.getMessage() + " | Data: " + String.join(";", aData));
            }
        }
    }

    // --- HELPER METHODS FOR VALIDATION AND ALERTS ---
    
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private boolean isInvalidNonEmpty(TextField field, String fieldName) {
        if (field.getText() == null || field.getText().trim().isEmpty()) {
            showAlert(AlertType.ERROR, "Validation Error", fieldName + " cannot be empty.");
            return true;
        }
        return false;
    }

    private boolean isInvalidInteger(TextField field, String fieldName) {
        try {
            Integer.parseInt(field.getText());
            return false;
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Validation Error", fieldName + " must be a valid whole number.");
            return true;
        }
    }

    private boolean isInvalidDouble(TextField field, String fieldName) {
        try {
            Double.parseDouble(field.getText());
            return false;
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Validation Error", fieldName + " must be a valid number.");
            return true;
        }
    }

    private boolean isInvalidPositive(TextField field, String fieldName) {
        try {
            if (Double.parseDouble(field.getText()) <= 0) {
                showAlert(AlertType.ERROR, "Validation Error", fieldName + " must be a positive number.");
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return true; // The invalid number format error will be caught by isInvalidDouble/Integer
        }
    }
    
    private boolean isInvalidDate(DatePicker field, String fieldName) {
        if (field.getValue() == null) {
            showAlert(AlertType.ERROR, "Validation Error", fieldName + " must be selected.");
            return true;
        }
        return false;
    }
}
