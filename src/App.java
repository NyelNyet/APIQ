import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

// Define a simple ZakatPayer class to hold payer information and payments
class ZakatPayer {
    private String name;
    private String phone;
    private String address;
    private double salary;
    private final List<Payment> payments; // List to store payments made by this payer

    public ZakatPayer() {
        this.payments = new ArrayList<>();
    }

    // Getters and Setters for payer details
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    // Method to add a payment
    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }

    // Method to get all payments (if needed for display later)
    public List<Payment> getPayments() {
        return new ArrayList<>(payments); // Return a copy to prevent external modification
    }
}

// Define a simple Payment class to store payment details (converted from record for broader compatibility)
class Payment {
    private final double amount;
    private final String date;

    public Payment(double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

// Define a ZakatApplicant class to hold applicant information
class ZakatApplicant {
    private final String asnafType;
    private final String name;
    private final String number;
    private final String email;
    private final String address;
    private final double monthlyIncome;
    private final double monthlyExpense;
    private final String familyInfo;

    public ZakatApplicant(String asnafType, String name, String number, String email, String address,
                          double monthlyIncome, double monthlyExpense, String familyInfo) {
        this.asnafType = asnafType;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpense = monthlyExpense;
        this.familyInfo = familyInfo;
    }

    // Getters for ZakatApplicant properties
    public String getAsnafType() { return asnafType; }
    public String getName() { return name; }
    public String getNumber() { return number; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public double getMonthlyExpense() { return monthlyExpense; }
    public String getFamilyInfo() { return familyInfo; }
}


public class App extends Application {

    // ObservableList to store all Zakat Applicant data, accessible application-wide
    private final ObservableList<ZakatApplicant> allApplicants = FXCollections.observableArrayList();

    // Reference to the Admin tab to dynamically change its content
    private Tab adminTab;

    @Override
    public void start(Stage primaryStage) {
        // Create a TabPane to hold all the tabs
        TabPane tabPane = new TabPane();

        // Initialize Admin tab and keep a reference
        adminTab = new Tab("Admin");
        adminTab.setContent(createAdminForm()); // Initial content is the login form
        adminTab.setClosable(false); // Make the tab non-closable

        Tab zakatPayerTab = new Tab("Zakat Payer");
        zakatPayerTab.setContent(createZakatPayerForm()); // Content for Zakat Payer tab, now including calculation logic
        zakatPayerTab.setClosable(false);

        Tab zakatApplicationTab = new Tab("Zakat Application");
        zakatApplicationTab.setContent(createZakatApplicationForm()); // Content for Zakat Application tab
        zakatApplicationTab.setClosable(false);

        Tab displayTab = new Tab("Report");
        displayTab.setContent(createDisplayForm()); // Content for Display tab
        displayTab.setClosable(false);

        // Add all tabs to the TabPane
        tabPane.getTabs().addAll(
                adminTab,
                zakatPayerTab,
                zakatApplicationTab,
                displayTab
        );
        //tabPane.setPadding(new Insets(10,10,10,10));
        

        // Set the root layout for the scene
        BorderPane root = new BorderPane(tabPane);
       
        Scene scene = new Scene(root, 600, 500); // Set initial scene size based on user's latest input
        primaryStage.setTitle("Zakat Management System"); // Set the window title
        primaryStage.setScene(scene); // Set the scene on the primary stage
        primaryStage.show(); // Display the stage
    }

    /**
     * Creates the form for the Zakat Payer tab, incorporating the Zakat calculation and payment logic.
     * @return A VBox containing the Zakat Payer form elements.
     */
    private VBox createZakatPayerForm() {
        // Instantiate a new ZakatPayer object for this session
        ZakatPayer payer = new ZakatPayer();

        // Labels and TextFields for the Zakat Payer form
        Label titleLabel = new Label("Pay Zakat"); // Title for the form

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name"); // Placeholder for full name

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number"); // Placeholder for phone number

        TextField addressField = new TextField();
        addressField.setPromptText("Address"); // Placeholder for address

        TextField salaryField = new TextField();
        salaryField.setPromptText("Monthly Income/Salary (RM)"); // Placeholder for monthly income/salary

        Label zakatAmountLabel = new Label("Zakat amount: RM 0.00"); // Label to display calculated zakat amount

        // Buttons for actions
        Button calculateBtn = new Button("Calculate Zakat");
        Button payBtn = new Button("Pay");
        Button clearFieldsBtn = new Button("Clear Fields"); // Changed "Back" to "Clear Fields" for this context

        payBtn.setDisable(true); // Initially disable the Pay button until zakat is calculated

        // --- Event Handlers ---

        // Enable Enter key navigation between text fields
        nameField.setOnAction(e -> phoneField.requestFocus());
        phoneField.setOnAction(e -> addressField.requestFocus());
        addressField.setOnAction(e -> salaryField.requestFocus());
        salaryField.setOnAction(e -> calculateBtn.fire()); // Pressing Enter in salary field triggers calculate button

        // Action for the Calculate Zakat button
        calculateBtn.setOnAction(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String salaryText = salaryField.getText();

            // Validate if all fields are filled
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || salaryText.isEmpty()) {
                showAlert("Missing Data", "Please fill in all fields.");
                return; // Exit if data is missing
            }

            try {
                double salary = Double.parseDouble(salaryText); // Parse salary to double
                // Update the payer object with collected data
                payer.setName(name);
                payer.setPhone(phone);
                payer.setAddress(address);
                payer.setSalary(salary);

                // Zakat eligibility check (salary below RM4000 is not subject to zakat)
                if (salary < 4000) {
                    showAlert("Not Eligible", "Salary below RM4000 is not subject to zakat.");
                    zakatAmountLabel.setText("Zakat amount: RM 0.00");
                    payBtn.setDisable(true); // Disable pay button
                    return;
                }

                // Calculate zakat percentage based on salary tiers
                double zakatPercent;
                if (salary <= 5000) {
                    zakatPercent = 0.03; // 3%
                } else if (salary <= 7000) {
                    zakatPercent = 0.05; // 5%
                } else if (salary <= 9000) {
                    zakatPercent = 0.07; // 7%
                } else {
                    zakatPercent = 0.10; // 10%
                }

                double zakatAmount = salary * zakatPercent; // Calculate total zakat amount
                // Display the calculated zakat amount
                zakatAmountLabel.setText(String.format(
                        "Zakat amount: RM %.2f (%.0f%% of RM %.2f)",
                        zakatAmount, zakatPercent * 100, salary
                ));
                payBtn.setUserData(zakatAmount); // Store zakat amount in the button's user data for payment
                payBtn.setDisable(false); // Enable the Pay button
            } catch (NumberFormatException ex) {
                // Handle invalid number input for salary
                showAlert("Invalid Input", "Please enter a valid number for monthly income/salary.");
                zakatAmountLabel.setText("Zakat amount: RM 0.00");
                payBtn.setDisable(true); // Disable pay button on invalid input
            }
        });

        // Action for the Pay button
        payBtn.setOnAction(e -> {
            Object data = payBtn.getUserData();
            if (data instanceof Double) {
                double amount = (Double) data;
                String date = LocalDate.now().toString(); // Get current date
                Payment payment = new Payment(amount, date); // Create a new Payment object
                payer.addPayment(payment); // Add payment to the payer's list

                showAlert("Payment Successful", "Zakat of RM " + String.format("%.2f", amount) + " has been paid.");
                payBtn.setDisable(true); // Disable pay button after successful payment to prevent re-payment
                // Optionally, clear fields or reset the form after payment
                clearZakatPayerFields(nameField, phoneField, addressField, salaryField, zakatAmountLabel);
            }
        });

        // Action for the Clear Fields button
        clearFieldsBtn.setOnAction(e -> {
            clearZakatPayerFields(nameField, phoneField, addressField, salaryField, zakatAmountLabel);
            payBtn.setDisable(true); // Disable pay button when fields are cleared
        });

        // Layout for the Zakat Payer form
        VBox layout = new VBox(10, // Spacing between elements
                titleLabel,
                nameField,
                phoneField,
                addressField,
                salaryField,
                calculateBtn,
                zakatAmountLabel,
                payBtn,
                clearFieldsBtn
        );
        layout.setPadding(new Insets(20)); // Padding around the layout
        layout.setAlignment(Pos.CENTER); // Center align the form elements
        layout.setMaxWidth(400); // Set a maximum width for the form for better aesthetics

        VBox container = new VBox(layout); // Container to center the form within the tab
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(600, 500); // Set preferred size for the container
        return container;
    }

    /**
     * Clears the input fields and resets the zakat amount label.
     */
    private void clearZakatPayerFields(TextField nameField, TextField phoneField, TextField addressField,
                                       TextField salaryField, Label zakatAmountLabel) {
        nameField.clear();
        phoneField.clear();
        addressField.clear();
        salaryField.clear();
        zakatAmountLabel.setText("Zakat amount: RM 0.00");
    }

    /**
     * Creates the initial login form for the Admin tab.
     * On successful login, it switches to the Manage Applicant view.
     * @return A VBox containing the Admin login form elements.
     */
    private VBox createAdminForm() {
        // Create text fields for username and password
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username"); // Placeholder text for username

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password"); // Placeholder text for password

        // Create login button
        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {
            // Hardcoded login for demonstration
            if (usernameField.getText().equals("admin") && passwordField.getText().equals("password")) {
                showAlert("Login Successful", "Welcome, Admin!");
                // Switch the content of the Admin tab to the Manage Applicant form
                adminTab.setContent(createManageApplicantForm());
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        });

        // Layout for the Admin login form
        VBox form = new VBox(10, // Spacing between elements
                usernameField,
                passwordField,
                loginBtn
        );
        form.setPadding(new Insets(20)); // Padding around the form
        form.setAlignment(Pos.CENTER); // Center align the form elements
        form.setMaxWidth(300); // Set maximum width for the form

        VBox container = new VBox(form); // Container to center the form within the tab
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(600, 500); // Set preferred size for the container
        return container;
    }

    /**
     * Creates the form for the Zakat Application tab.
     * When applied, the applicant data is stored in a shared list.
     * @return A VBox containing the Zakat Application form elements.
     */
    private VBox createZakatApplicationForm() {
        // Create Combo Box for "Type of Asnaf"
        ComboBox<String> asnafComboBox = new ComboBox<>();
        asnafComboBox.setPromptText("Select Type of Asnaf"); // Placeholder text
        asnafComboBox.getItems().addAll("AlFuqara", "AlMasakin", "AlAmilunaAlaiha","AlMualafaQulubuhum", "AlRiqab", "AlGharimoon", "FiSabiLillah", "IbnAlSabil"); // Example items

        // Create form fields
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField numberField = new TextField();
        numberField.setPromptText("Number");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextArea addressArea = new TextArea();
        addressArea.setPromptText("Address");
        addressArea.setPrefRowCount(2);

        TextField incomeField = new TextField();
        incomeField.setPromptText("Monthly Income");

        TextField expenseField = new TextField();
        expenseField.setPromptText("Monthly Expense");

        TextArea familyInfoArea = new TextArea();
        familyInfoArea.setPromptText("Family Information");
        familyInfoArea.setPrefRowCount(3);


        // Create buttons
        Button applyBtn = new Button("Apply");
        Button clearFormBtn = new Button("Clear Form"); // Renamed for clarity

        applyBtn.setOnAction(e -> {
            // Validate if all fields are filled
            if (asnafComboBox.getValue() == null || nameField.getText().isEmpty() || numberField.getText().isEmpty() ||
                    emailField.getText().isEmpty() || addressArea.getText().isEmpty() ||
                    incomeField.getText().isEmpty() || expenseField.getText().isEmpty() || familyInfoArea.getText().isEmpty()) {
                showAlert("Missing Data", "Please fill in all fields.");
                return;
            }

            try {
                // Parse income and expense
                double monthlyIncome = Double.parseDouble(incomeField.getText());
                double monthlyExpense = Double.parseDouble(expenseField.getText());

                // Create a new ZakatApplicant object
                ZakatApplicant newApplicant = new ZakatApplicant(
                        asnafComboBox.getValue(),
                        nameField.getText(),
                        numberField.getText(),
                        emailField.getText(),
                        addressArea.getText(),
                        monthlyIncome,
                        monthlyExpense,
                        familyInfoArea.getText()
                );

                // Add the new applicant to the shared list
                allApplicants.add(newApplicant);
                showAlert("Application Submitted", "Your Zakat application has been submitted successfully!");
                // Clear the form after submission
                clearZakatApplicationFields(asnafComboBox, nameField, numberField, emailField, addressArea, incomeField, expenseField, familyInfoArea);

            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid numbers for Monthly Income and Monthly Expense.");
            }
        });

        clearFormBtn.setOnAction(e -> {
            clearZakatApplicationFields(asnafComboBox, nameField, numberField, emailField, addressArea, incomeField, expenseField, familyInfoArea);
        });

        // Layout for the Zakat Application form
        VBox form = new VBox(10, // Spacing between elements
                asnafComboBox,
                nameField,
                numberField,
                emailField,
                addressArea,
                incomeField,
                expenseField,
                familyInfoArea,
                applyBtn,
                clearFormBtn
        );
        form.setPadding(new Insets(20)); // Padding around the form
        form.setAlignment(Pos.CENTER); // Center align the form elements
        form.setMaxWidth(300); // Set maximum width for the form

        VBox container = new VBox(form); // Container to center the form within the tab
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(600, 500); // Set preferred size for the container
        return container;
    }

    /**
     * Clears the input fields for the Zakat Application form.
     */
    private void clearZakatApplicationFields(ComboBox<String> asnafComboBox, TextField nameField,
                                             TextField numberField, TextField emailField, TextArea addressArea,
                                             TextField incomeField, TextField expenseField, TextArea familyInfoArea) {
        asnafComboBox.getSelectionModel().clearSelection();
        nameField.clear();
        numberField.clear();
        emailField.clear();
        addressArea.clear();
        incomeField.clear();
        expenseField.clear();
        familyInfoArea.clear();
    }


    /**
     * Creates the content for the Display tab.
     * It dynamically displays either a placeholder for Zakat Payer data or a TableView for Zakat Applicant data.
     * @return A VBox containing the Display elements.
     */
    private VBox createDisplayForm() {
        // Create Combo Box for selecting what to display
        ComboBox<String> displayComboBox = new ComboBox<>();
        displayComboBox.setPromptText("Zakat Payer"); // Placeholder text
        displayComboBox.getItems().addAll("Zakat Payer", "Zakat Applicant"); // Updated items as per your request
        displayComboBox.setPrefWidth(200); // Set preferred width for the combo box

        // Placeholder for Zakat Payer data (since we don't store a list of all payers)
        Label zakatPayerPlaceholder = new Label("Information for Zakat Payer will appear here.");
        zakatPayerPlaceholder.setStyle("-fx-font-size: 14px; -fx-text-fill: grey;");

        // TableView for Zakat Applicant data
        TableView<ZakatApplicant> applicantTableView = new TableView<>();
        applicantTableView.setItems(allApplicants); // Bind table to the global observable list of applicants

        // Define columns for Zakat Applicant TableView
        TableColumn<ZakatApplicant, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nameCol.setPrefWidth(120);

        TableColumn<ZakatApplicant, String> asnafCol = new TableColumn<>("Type of Asnaf");
        asnafCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsnafType()));
        asnafCol.setPrefWidth(100);

        TableColumn<ZakatApplicant, String> incomeCol = new TableColumn<>("Monthly Income (RM)");
        incomeCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getMonthlyIncome())));
        incomeCol.setPrefWidth(120);

        TableColumn<ZakatApplicant, String> expenseCol = new TableColumn<>("Monthly Expense (RM)");
        expenseCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getMonthlyExpense())));
        expenseCol.setPrefWidth(120);

        applicantTableView.getColumns().addAll(nameCol, asnafCol, incomeCol, expenseCol);
        VBox.setVgrow(applicantTableView, Priority.ALWAYS); // Make the table grow vertically to fill space

        // StackPane to hold and switch between the placeholder and the table view
        StackPane contentPane = new StackPane();
        contentPane.getChildren().addAll(zakatPayerPlaceholder, applicantTableView); // Add both components to the stack pane

        // Listener for combo box selection to toggle visibility
        displayComboBox.setOnAction(e -> {
            String selectedType = displayComboBox.getSelectionModel().getSelectedItem();
            if ("Zakat Payer".equals(selectedType)) {
                zakatPayerPlaceholder.setVisible(true); // Show placeholder
                zakatPayerPlaceholder.toFront(); // Bring to front
                applicantTableView.setVisible(false); // Hide table
            } else if ("Zakat Applicant".equals(selectedType)) {
                zakatPayerPlaceholder.setVisible(false); // Hide placeholder
                applicantTableView.setVisible(true); // Show table
                applicantTableView.toFront(); // Bring to front
            }
        });

        // Set initial visibility and selection
        displayComboBox.getSelectionModel().selectFirst(); // Select "Zakat Payer" by default
        zakatPayerPlaceholder.setVisible(true);
        applicantTableView.setVisible(false);

        // Layout for the Display tab (VBox)
        VBox form = new VBox(10, // Spacing between elements
                displayComboBox,
                contentPane // Add the StackPane containing dynamic content
        );
        form.setPadding(new Insets(20)); // Padding around the form
        form.setAlignment(Pos.CENTER); // Align the VBox content to the center as per your request
        VBox.setVgrow(contentPane, Priority.ALWAYS); // Make content pane grow vertically

        // Container to center the form within the tab
        VBox container = new VBox(form);
        container.setAlignment(Pos.TOP_CENTER); // Align the container to top center as per your request
        container.setPadding(new Insets(20)); // Add padding to the container
        container.setPrefSize(600, 500); // Set preferred size for the container
        return container;
    }


    /**
     * Creates the "Manage Applicant" form for the Admin tab.
     * Displays a table of applicants and allows approval/rejection feedback.
     * @return A VBox containing the Manage Applicant form elements.
     */
    private VBox createManageApplicantForm() {
        Label titleLabel = new Label("Manage Zakat Applicants");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TableView<ZakatApplicant> applicantTable = new TableView<>();
        applicantTable.setItems(allApplicants); // Bind table to the observable list

        // Define columns
        TableColumn<ZakatApplicant, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nameCol.setPrefWidth(150);
        nameCol.setReorderable(false);

        TableColumn<ZakatApplicant, String> asnafCol = new TableColumn<>("Type of Asnaf");
        asnafCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsnafType()));
        asnafCol.setPrefWidth(120);
        asnafCol.setReorderable(false);

        TableColumn<ZakatApplicant, String> incomeCol = new TableColumn<>("Monthly Income (RM)");
        incomeCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getMonthlyIncome())));
        incomeCol.setPrefWidth(150);
        incomeCol.setReorderable(false);

        TableColumn<ZakatApplicant, String> expenseCol = new TableColumn<>("Monthly Expense (RM)");
        expenseCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getMonthlyExpense())));
        expenseCol.setPrefWidth(150);
        expenseCol.setReorderable(false);

        TableColumn<ZakatApplicant, String> infoCol = new TableColumn<>("Family Information"); // Renamed for clarity
        infoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFamilyInfo())); // Bind to family info
        infoCol.setPrefWidth(300);
        infoCol.setReorderable(false);

        applicantTable.getColumns().addAll(nameCol, asnafCol, incomeCol, expenseCol, infoCol);

        // Input for amount transfer
        TextField amountTransferField = new TextField();
        amountTransferField.setPromptText("Amount to Transfer (RM)");
        amountTransferField.setPrefWidth(200);
        amountTransferField.setDisable(true); // Disable until an applicant is selected

        // Buttons for actions
        Button approveBtn = new Button("Approve Applicant");
        Button rejectBtn = new Button("Reject Applicant");
        Button backToLoginBtn = new Button("Logout"); // New button to return to login page

        approveBtn.setDisable(true); // Disable initially
        rejectBtn.setDisable(true); // Disable initially

        // Listener for table selection
        applicantTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isSelected = newSelection != null;
            approveBtn.setDisable(!isSelected);
            rejectBtn.setDisable(!isSelected);
            amountTransferField.setDisable(!isSelected); // Enable/disable amount field based on selection
            if (isSelected) {
                amountTransferField.setText("");
            }
        });

        // Action for Approve button
        approveBtn.setOnAction(e -> {
            ZakatApplicant selectedApplicant = applicantTable.getSelectionModel().getSelectedItem();
            if (selectedApplicant != null) {
                if (amountTransferField.getText().isEmpty()) {
                    showAlert("Missing Amount", "Please enter an amount to transfer for approval.");
                    return;
                }
                try {
                    double transferAmount = Double.parseDouble(amountTransferField.getText());
                    if (transferAmount <= 0) {
                        showAlert("Invalid Amount", "Transfer amount must be positive.");
                        return;
                    }
                    // This now only prints feedback and does not change a status
                    showAlert("Feedback Recorded", String.format("Applicant '%s' has been approved for RM %.2f.", selectedApplicant.getName(), transferAmount));
                    amountTransferField.clear(); // Clear amount field
                    applicantTable.getSelectionModel().clearSelection(); // Clear selection
                } catch (NumberFormatException ex) {
                    showAlert("Invalid Input", "Please enter a valid number for the transfer amount.");
                }
            }
            allApplicants.remove(0);
        });

        // Action for Reject button
        rejectBtn.setOnAction(e -> {
            ZakatApplicant selectedApplicant = applicantTable.getSelectionModel().getSelectedItem();
            if (selectedApplicant != null) {
                // This now only prints feedback and does not change a status
                applicantTable.getSelectionModel().clearSelection(); // Clear selection
                allApplicants.remove(0);
                showAlert("Feedback Recorded", String.format("Applicant '%s' has been rejected.", selectedApplicant.getName()));
                amountTransferField.clear(); // Clear amount field
                
            }
        });

        // Action for Logout button
        backToLoginBtn.setOnAction(e -> {
            // Go back to the admin login page
            adminTab.setContent(createAdminForm());
            allApplicants.clear(); // Optionally clear applicants on logout
        });

        // Layout for buttons and amount field
        HBox actionControls = new HBox(10, amountTransferField, approveBtn, rejectBtn);
        actionControls.setAlignment(Pos.CENTER_LEFT); // Align controls to the left

        // Main layout for the Manage Applicant form
        VBox layout = new VBox(15, // Spacing between elements
                titleLabel,
                applicantTable,
                actionControls,
                backToLoginBtn
        );
        layout.setPadding(new Insets(20)); // Padding around the layout
        layout.setAlignment(Pos.TOP_CENTER); // Align content to top center
        VBox.setVgrow(applicantTable, Priority.ALWAYS); // Table takes available vertical space

        // Container to ensure centering and proper sizing
        VBox container = new VBox(layout);
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(800, 600);
        return container;
    }


    /**
     * Displays an alert dialog with the given title and message.
     * @param title The title of the alert.
     * @param message The message content of the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an information alert
        alert.setTitle(title); // Set the title
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set the message content
        alert.showAndWait(); // Show the alert and wait for user interaction
    }

    public static void main(String[] args) {
        launch(args);
    }
}