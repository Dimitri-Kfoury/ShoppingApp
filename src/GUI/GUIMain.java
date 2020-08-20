package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIMain extends Application {

    private static final int WIDTH = 850;
    private static final int HEIGHT = 800;
    private static GUIMain instance = new GUIMain();
    private static Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
        GUIMain.primaryStage = primaryStage;

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeScene(FXMLLoader fxmlLoader, String headerMessage) {


        try {

            Parent root = fxmlLoader.load();
            primaryStage.setTitle(headerMessage);
            primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static GUIMain getInstance() {
        return instance;
    }


}
