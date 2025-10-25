package emailclient.controller;

import emailclient.model.User;
import emailclient.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class UserDetailController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private final UserService userService = new UserService();
    private User user;

    public void setUser(User user) {
        this.user = user;
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        passwordField.setText(user.getPassword());
    }

    @FXML
    private void handleEditUser() {
        if (user == null) return;

        String newName = nameField.getText();
        String newEmail = emailField.getText();
        String newPassword = passwordField.getText();

        if (newName.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
            showAlert("Помилка", "Усі поля повинні бути заповнені!");
            return;
        }

        user.setName(newName);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        userService.updateUser(user);
        showAlert("Успіх", "Користувач оновлений!");
    }

    @FXML
    private void handleDeleteUser() {
        if (user == null) return;

        userService.deleteUser(user);
        showAlert("Успіх", "Користувач видалений!");
        handleBack();
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


