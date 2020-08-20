package GUI;


import Client.ClientSession;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * @author Dimitri
 */
public class CreateAccountPage {

    private boolean passwordsMatch = false;
    @FXML
    private Label passwordMismatchLabel, passwordsMatchLabel, accountCreatedLabel,usernameExistsLabel;
    @FXML
    private PasswordField confirmPasswordTextField, passwordTextField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField usernameField, firstNameField, lastNameField, addressField, telephoneNumberField;

    @FXML
    public void initialize() {

        passwordsMatch = false;

        ChangeListener<String> accountCreationDetailsChangeListener = (observableValue, s, t1) -> enableSubmit(); // change listener for new account's details fields(other than password)
        ChangeListener<String> passwordChangListener = (observableValue, s, t1) -> confirmPassword(); // change listener for password and confirm password fields


        // Adding change listeners to fields
        usernameField.textProperty().addListener(accountCreationDetailsChangeListener);
        firstNameField.textProperty().addListener(accountCreationDetailsChangeListener);
        lastNameField.textProperty().addListener(accountCreationDetailsChangeListener);
        addressField.textProperty().addListener(accountCreationDetailsChangeListener);
        telephoneNumberField.textProperty().addListener(accountCreationDetailsChangeListener);
        passwordTextField.textProperty().addListener(passwordChangListener);
        confirmPasswordTextField.textProperty().addListener(passwordChangListener);
    }

    @FXML
    public void handleSubmitButtonPressed() {


        String username = usernameField.getText();
        String password = passwordTextField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String telephoneNumber = telephoneNumberField.getText();


        new Thread(() -> {

            boolean accountCreationSuccess = ClientSession.getInstance().createAccount(username, password, firstName, lastName, address, telephoneNumber);

            if (accountCreationSuccess) {
                Platform.runLater(() -> {
                    accountCreatedLabel.setVisible(true);
                    goToLoginPage(username);
                });

            }
            else {

                usernameExistsLabel.setVisible(true);

            }


        }).start();


    }


    public void confirmPassword() {

        if (!confirmPasswordTextField.getText().trim().isEmpty() && !passwordTextField.getText().trim().isEmpty()) {


            if (!confirmPasswordTextField.getText().equals(passwordTextField.getText()) && passwordsMatch) {
                passwordsMatchLabel.setVisible(false);
                passwordMismatchLabel.setVisible(true);
                passwordsMatch = false;

            } else if (!confirmPasswordTextField.getText().equals(passwordTextField.getText()) && !passwordsMatch) {
                passwordMismatchLabel.setVisible(true);
            } else if (confirmPasswordTextField.getText().equals(passwordTextField.getText())) {
                passwordsMatch = true;
                passwordsMatchLabel.setVisible(true);
                passwordMismatchLabel.setVisible(false);


            }
        } else {
            if (passwordsMatch) passwordsMatch = false;
            passwordMismatchLabel.setVisible(false);
            passwordsMatchLabel.setVisible(false);
        }
        enableSubmit();

    }

    private void goToLoginPage(String username) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        GUIMain.getInstance().changeScene(loader,"Login");
        LoginPage loginPageController = loader.getController();
        loginPageController.getTopLabel().setText("Your account has been created !");
        loginPageController.getTopLabel().setTextFill(Color.GREEN);
        loginPageController.getUsernameField().setText(username);



    }

    private void enableSubmit() {

        if (!usernameField.getText().isEmpty() && passwordsMatch && !firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty()
                && !addressField.getText().isEmpty() && !telephoneNumberField.getText().isEmpty())
            submitButton.setDisable(false);
        else {
            if (!submitButton.isDisabled())
                submitButton.setDisable(true);
        }
    }

}
