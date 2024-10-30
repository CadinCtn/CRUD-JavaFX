package controller;

import javafx.stage.Stage;

public class ControllerStage {

    private static Stage primaryStage;

    public static Stage getPrimaryStage(){return primaryStage;}
    public static void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }


}
