package main;

import connectionFactory.Connection;
import controller.ControllerStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ControllerStage.setPrimaryStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/windows/login.fxml"));
        stage.setScene(new Scene(loader.load(),410,405));
        stage.setTitle("Login");
        stage.show();

    }

    @Override
    public void stop(){
        Connection.shutdown();
    }

    public static void main(String[] args){
        launch();
    }

}
