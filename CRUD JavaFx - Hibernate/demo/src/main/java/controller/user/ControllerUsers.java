package controller.user;

import connectionFactory.Connection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.User;
import service.UserService;

import java.io.IOException;
import java.util.List;

public class ControllerUsers{

    protected UserService service;

    @FXML
    private Button btnBack;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> columnId;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User,String> columnLogin;
    @FXML
    private TableColumn<User, String> columnPassword;


    @FXML
    private void initialize(){
        //Inicializa Service
        service = new UserService();

        //Configurando colunas da tabela
        columnId.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        columnLogin.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLogin()));
        columnName.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getName()));
        columnPassword.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getPassword()));


        try{
            //Lista de usuarios cadastrados no banco
            List<User> list = service.readAll();
            //Inserindo itens na tabela
            table.setItems(FXCollections.observableList(list));
        }catch (Exception e){
            service.showAlert(Alert.AlertType.ERROR,"ERRO","Não foi possivel carregar a tabela de usuarios.\n" +
                                                                        "ERRO: "+e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddUser(){
        try{
            service.goToNewUser(null);
        }catch (IOException e){
            service.showAlert(Alert.AlertType.ERROR, "ERRO", "Erro ao carregar tela de novo usuário.\n" +
                                                                          "Erro: " +e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdUser(){
        User user = (User) service.getItemFromTable(table);
        if(user != null){
            try{
                service.goToNewUser(user);
            }catch (IOException e){
                service.showAlert(Alert.AlertType.ERROR, "ERRO", "Erro ao carregar tela de novo usuário.\n" +
                        "Erro: " +e.getMessage());
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void btnDelUser(){
        User user = (User) service.getItemFromTable(table);
        if(user != null){
            try{
                service.delete(user.getId());
                table.getItems().remove(user); //Remove usuário da tabela

                service.showAlert(Alert.AlertType.INFORMATION,"Sucesso!", "Usuário deletado com sucesso!");
            }catch (Exception e){
                service.showAlert(Alert.AlertType.ERROR, "ERRO", "Erro ao deletar o usuário.\n" +
                                                                              "Erro: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void btnBack(){
        try{
            service.goToMenu((Stage) btnBack.getScene().getWindow());
        }catch (IOException e){
            service.showAlert(Alert.AlertType.ERROR, "ERRO", "Não foi possivel carregar o menu.\n" +
                                                                          "ERRO: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
