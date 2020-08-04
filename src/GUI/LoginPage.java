package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginPage {


    @FXML
    private Label topLabel;
    @FXML
    private TextField usernameField;

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
}
