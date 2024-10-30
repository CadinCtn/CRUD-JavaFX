package controller;

import connectionFactory.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;

public class ControllerMenu{

    @FXML
    private Button btnUsers;

    @FXML
    private void btnUsers(){
        UserService service = new UserService();
        try{
            //Mostra tela de usuários
            service.goToUsers();
        }catch (IOException e){
            service.showAlert(Alert.AlertType.ERROR,"ERRO","Não foi possivel carregar a tela de usuários!\n" +
                                                                        "ERRO: "+e.getMessage());
            e.printStackTrace();
        }
    }

}
