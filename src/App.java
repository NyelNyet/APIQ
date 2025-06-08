import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static final List<ZakatPayer> registeredPayers = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Add dummy data for testing
        ZakatPayer dummy = new ZakatPayer(1, "Ali", "ali@example.com");
        dummy.getPayments().add(new Payment(150.00, "2025-06-08"));
        registeredPayers.add(dummy);

        showLoginRegisterPage(stage, "Admin");
    }

    private void showLoginRegisterPage(Stage stage, String userType) {
        Label titleLabel = new Label(userType + " Login");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {
            String input = usernameField.getText();
            String password = passwordField.getText();

            if (input.equals("APIQ") && password.equals("12345")) {
                Admin admin = new Admin(1, "Admin", "APIQ", "12345");
                showAdminDashboard(stage, admin);
            } else {
                showAlert("Login Failed", "Invalid admin credentials.");
            }
        });

        VBox layout = new VBox(10, titleLabel, usernameField, passwordField, loginBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        stage.setScene(new Scene(layout, 400, 300));
        stage.setTitle("Admin Login");
        stage.show();
    }

    private void showAdminDashboard(Stage stage, Admin admin) {
        Label welcome = new Label("Welcome, Admin!");
        Button manageUsers = new Button("Manage Applicant");
        Button viewReports = new Button("View Zakat Reports");
        Button logoutBtn = createLogoutButton(stage);

        manageUsers.setOnAction(e -> showManageApplicantsPage(stage, admin));
        viewReports.setOnAction(e -> showReportsPage(stage, admin));

        VBox layout = new VBox(10, welcome, manageUsers, viewReports, logoutBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        stage.setScene(new Scene(layout, 400, 300));
    }

    private void showManageApplicantsPage(Stage stage, Admin admin) {
        Label label = new Label("Manage Applicant Page");
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> showAdminDashboard(stage, admin));

        VBox layout = new VBox(20, label, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        stage.setScene(new Scene(layout, 400, 300));
    }

    private void showReportsPage(Stage stage, Admin admin) {
        Label title = new Label("Zakat Reports");
        Button debitBtn = new Button("View Zakat Debit Report");
        Button creditBtn = new Button("View Zakat Credit Report");
        Button backBtn = new Button("Back");

        VBox layout = new VBox(15, title, debitBtn, creditBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        stage.setScene(new Scene(layout, 400, 300));

        debitBtn.setOnAction(e -> showZakatDebitReport(stage, admin));
        creditBtn.setOnAction(e -> showZakatCreditReport(stage, admin));
        backBtn.setOnAction(e -> showAdminDashboard(stage, admin));
    }

    private void showZakatDebitReport(Stage stage, Admin admin) {
        Label title = new Label("Zakat Debit Report (Paid by Payers)");
        VBox reportBox = new VBox(10);
        reportBox.getChildren().add(title);

        if (registeredPayers.isEmpty()) {
            reportBox.getChildren().add(new Label("No registered Zakat Payers."));
        } else {
            for (ZakatPayer payer : registeredPayers) {
                VBox payerBox = new VBox(5);
                payerBox.setStyle("-fx-border-color: #ccc; -fx-padding: 10;");

                Label payerInfo = new Label(
                    "User ID: " + payer.getUserId() + "\n" +
                    "Name: " + payer.getName() + "\n" +
                    "Email: " + payer.getEmail() + "\n" +
                    "Phone: " + payer.getPhone() + "\n" +
                    "Address: " + payer.getAddress() + "\n" +
                    "Monthly Salary: RM " + String.format("%.2f", payer.getSalary())
                );
                payerBox.getChildren().add(payerInfo);

                List<Payment> payments = payer.getPayments();
                if (payments.isEmpty()) {
                    payerBox.getChildren().add(new Label("No zakat payments made."));
                } else {
                    for (Payment payment : payments) {
                        payerBox.getChildren().add(new Label(
                            "- Paid RM " + payment.getAmount() + " on " + payment.getDate()
                        ));
                    }
                }

                reportBox.getChildren().add(payerBox);
            }
        }

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> showReportsPage(stage, admin));

        VBox layout = new VBox(10, reportBox, backBtn);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 550, 600));
    }

    private void showZakatCreditReport(Stage stage, Admin admin) {
        Label title = new Label("Zakat Credit Report (Aid Given to Recipients)");
        VBox reportBox = new VBox(10, title);
        reportBox.getChildren().add(new Label("Feature not implemented yet."));
        reportBox.getChildren().add(new Label("No Zakat credit (aid) records available."));

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> showReportsPage(stage, admin));

        VBox layout = new VBox(10, reportBox, backBtn);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 450, 300));
    }

    private Button createLogoutButton(Stage stage) {
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> showLoginRegisterPage(stage, "Admin"));
        return logoutBtn;
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}