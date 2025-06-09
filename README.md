Of course! Here is a comprehensive README file generated based on the structure and content of your Java project.

---

# Zakat Management System

This is a desktop application built with JavaFX for managing the collection and distribution of Zakat. The system provides a user-friendly graphical interface (GUI) for two types of users: Public Users and a system Administrator. It allows for the registration of Zakat payers and Asnaf (recipients), and provides administrative tools for managing applications and generating reports.

## ✨ Features

- **Dual User Portals:** Separate interfaces and functionalities for Public Users and Administrators.
- **Zakat Payer Management:** Add and view individuals who are paying Zakat.
- **Asnaf (Recipient) Management:**
    - Register new Asnaf applications under the 8 official categories (Al-Fuqara, Al-Masakin, etc.).
    - Input detailed, category-specific information for each applicant.
- **Administrative Functions:**
    - Secure admin login portal.
    - View detailed information for all payers and recipients.
    - Approve or reject Asnaf applications.
    - Generate a comprehensive system report detailing Zakat collection, total recipients, and a breakdown by category.
- **Data Persistence:** User data is saved locally in `.txt` files, acting as a simple database.

## 💻 Technology Stack

- **Java:** The core programming language.
- **JavaFX:** Used for building the graphical user interface.

## 🚀 How to Run the Application

### Prerequisites

1.  **JDK (Java Development Kit):** Version 21 or compatible.
2.  **JavaFX SDK:** Version 21.0.7 or compatible. You can download it from the [GluonHQ website](https://gluonhq.com/products/javafx/).
3.  **Data Files:** Ensure that text files for each Asnaf category (e.g., `AlFuqara.txt`, `AlMasakin.txt`, etc.) and a `ZakatPayer.txt` file exist in the root directory of the project. A `UserCount.txt` file containing the number `0` is also required to initialize user IDs.

### Launch Configuration

1.  **Set up your IDE** (like VS Code, IntelliJ, or Eclipse) for JavaFX development.
2.  **Configure the VM arguments** to point to your JavaFX SDK's library folder. This is a crucial step.
    - *Example VM arguments:*
      ```
      --module-path /path/to/your/javafx-sdk-21.0.7/lib --add-modules javafx.controls,javafx.fxml
      ```
      *Replace `/path/to/your/` with the actual path to your JavaFX SDK directory.*
3.  **Set the Main Class** to `ZakatGUI`.
4.  **Run** the application.

### Admin Credentials

A default administrator account is hardcoded in the system. Use the following credentials to log in to the admin portal:

-   **Email:** `admin@zakat.com`
-   **Password:** `password123`

## 📁 Project Structure

The project source code is organized as follows:

-   `src/ZakatGUI.java`: The main entry point of the application. It handles all UI components and event handling.
-   `src/DataManager.java`: Manages the application's data in-memory using lists of users.
-   `src/FileManager.java`: Handles all file I/O operations, such as reading from and writing to the `.txt` data files.
-   `src/User/`: Contains the core user classes.
    -   `User.java`: An abstract base class for all users.
    -   `Admin.java`: Represents the system administrator.
    -   `ZakatPayer.java`: Represents a user who pays Zakat.
-   `src/User/Asnaf/`: Contains classes related to Zakat recipients.
    -   `Asnaf.java`: An abstract base class for all recipient categories.
    -   **Category Classes** (`AlFuqara.java`, `AlGharimoon.java`, etc.): Concrete implementations for each of the 8 Asnaf categories, with specific attributes relevant to that category.
-   `bin/`: Contains the compiled `.class` files.
-   `*.txt`: Text files in the root directory used for data persistence (e.g., `UserCount.txt`, `ZakatPayer.txt`).
