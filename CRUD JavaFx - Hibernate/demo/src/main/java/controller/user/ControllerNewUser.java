package controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;
import service.UserService;

import java.io.IOException;

public class ControllerNewUser{

    private UserService service;
    private User user;

    @FXML
    private TextField nameField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passField;

    @FXML
    private void initialize(){
        service = new UserService();
    }

    @FXML
    private void btnConfirm(){
        if(user == null) user = new User();
        user.setName(nameField.getText());
        user.setLogin(loginField.getText());
        user.setPassword(passField.getText());
        //Verifica se é válido
        if(user.isValid()){
            try{
                String acao;
                if(user.getId() != 0){
                    acao = "atualizado";
                    service.update(user);
                } else {
                    acao = "inserido";
                    service.create(user);
                }

                //Volta a tela de usuários
                btnCancel();

                service.showAlert(Alert.AlertType.INFORMATION, "Sucesso!", "Usuário "+acao+" com sucesso!");
            }catch (Exception e){
                service.showAlert(Alert.AlertType.ERROR, "ERRO", "Erro ao inserir/atualizar usuário.\n" +
                                                                              "Erro: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //Deixa campos pre-preenchidos
    public void fillFields(User user){
        this.user = user;
        nameField.setText(user.getName());
        loginField.setText(user.getLogin());
        passField.setText(user.getPassword());
    }


    @FXML
    private void btnCancel(){
        try{
            service.goToUsers();
        }catch (IOException e){
            service.showAlert(Alert.AlertType.ERROR, "ERRO","Erro ao carregar tela de usuários.\n" +
                                                                        "Erro: "+e.getMessage());
            e.printStackTrace();
        }
    }

}
