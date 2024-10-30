package service;

import controller.ControllerStage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class GenericService {

    //Método para mostrar mensagens popup ao usuario
    public void showAlert(Alert.AlertType type, String title, String message){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Vai para o menu
    public void goToMenu(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/windows/menu.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setMaximized(true);
        stage.setTitle("Menu");
    }

    //Vai para a tela de usuarios cadastrados
    public void goToUsers() throws IOException{
        Stage stage = ControllerStage.getPrimaryStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/windows/user/users.fxml"));
        stage.setScene(new Scene(loader.load(), stage.getWidth(), stage.getHeight()));
        stage.setMaximized(true);
        stage.setTitle("Usuários");
    }

    //Retorna item selecionado na tabela
    public Object getItemFromTable(TableView table){
        //item selecionado
        Object object = table.getSelectionModel().getSelectedItem();
        //Verifica se possui item selecionado
        if(object == null){
            showAlert(Alert.AlertType.INFORMATION, "Aviso", "Selecione uma linha");
        }
        //Retorna o item selecionado
        return object;
    }

}
