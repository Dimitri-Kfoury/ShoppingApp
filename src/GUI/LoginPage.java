package GUI;

import Client.ClientSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class LoginPage {


    @FXML
    private Label topLabel,loginErrorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton, signUpButton;
    @FXML
    private ProgressIndicator loginProgress;


    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public Label getTopLabel() {
        return topLabel;
    }

    public void setTopLabel(Label topLabel) {
        this.topLabel = topLabel;
    }

    @FXML
    public void login() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty())
            return;

        loginErrorLabel.setVisible(false);
        loginButton.setDisable(true);
        signUpButton.setDisable(true);
        loginProgress.setVisible(true);

        new Thread(() -> {

            if (ClientSession.getInstance().login(username, password)) {


                Platform.runLater(() -> {
                    GUIMain.getInstance().changeScene(new FXMLLoader(getClass().getResource("UserPage.fxml")), "Polo");
                });

            } else {

                loginButton.setDisable(false);
                signUpButton.setDisable(false);
                loginProgress.setVisible(false);
                loginErrorLabel.setVisible(true);


            }
        }).start();


    }

    @FXML
    public void signUp() {

        GUIMain.getInstance().changeScene(new FXMLLoader(getClass().getResource("CreateAccountPage.fxml")), "Polo");


    }

}
