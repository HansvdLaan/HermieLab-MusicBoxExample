package sample;

import annotations.setup.Start;
import instancemanager.InstanceManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Start(automaton = "MealyMachine")
    @Override
    public void start(Stage primaryStage) throws Exception{

        InstanceManager.getInstance().addClassInstance(this);

        Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        primaryStage.setTitle("TT-Example");
        primaryStage.setScene(new Scene(root, 238, 409));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
