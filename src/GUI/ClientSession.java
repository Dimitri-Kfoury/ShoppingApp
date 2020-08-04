package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientSession extends Application {


    public static void changeScene(int i, int i1, String login, FXMLLoader loader) {


    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
