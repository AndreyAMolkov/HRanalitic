package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainGUI extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("StaffGUI.fxml"));
        primaryStage.setTitle("HR analytic demo");
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
    }

}
