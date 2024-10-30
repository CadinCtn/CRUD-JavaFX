package controller;

import connectionFactory.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import service.UserService;

import javax.persistence.NoResultException;

public class ControllerLogin{

    private UserService service;
    @FXML
    private TextField fieldLogin;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private TextField fieldTextPass;
    @FXML
    private Button btnEnter;

    @FXML
    private void initialize(){
        //Atalho para botão enter
        btnEnter.setDefaultButton(true);

        ////Placeholder
        //Login
        setPlaceholder(fieldLogin, "Login...");
        fieldLogin.focusedProperty().addListener((Observable, oldValue, newValue) -> {
            if(newValue){
              clickField(fieldLogin);
            } else {
                setPlaceholder(fieldLogin, "Login...");
            }
        });

        //Password
        //Sincronizar texto do TextField e do PasswordField
        fieldPassword.textProperty().bindBidirectional(fieldTextPass.textProperty());

        setPlaceholder(fieldTextPass, "Password...");
        fieldPassword.setVisible(false);

        fieldTextPass.focusedProperty().addListener((Observable, oldValue, newValue) -> {
            if(newValue){
                clickField(fieldTextPass);

                fieldPassword.setVisible(true);
                fieldTextPass.setVisible(false);
                fieldPassword.requestFocus();
            }
        });

        fieldPassword.focusedProperty().addListener((Observable, oldValue, newValue) -> {
            if(oldValue){
                if(fieldPassword.getText().isEmpty()){
                    setPlaceholder(fieldTextPass, "Password...");

                    fieldPassword.setVisible(false);
                    fieldTextPass.setVisible(true);
                }
            }
        });

    }

    @FXML
    public void login(){
        //Inicializa Service
        service = new UserService();
        try{
            //Busca pelo usuario
            if(service.login(fieldLogin.getText(), fieldPassword.getText()) != null){
                //Vai para o menu
                service.goToMenu((Stage) fieldLogin.getScene().getWindow());
            } else {
                //Não encontrou resultado
                throw new NoResultException();
            }
        }catch (NoResultException e){
            //Não encontrou o usuario
            service.showAlert(Alert.AlertType.WARNING, "Incorreto", "Login e/ou senha incorreto(s).");
        }catch (Exception e){
            service.showAlert(Alert.AlertType.ERROR, "ERRO","Não foi possivel efetuar o login.\nERRO: "+e.getMessage());
            e.printStackTrace();
        }
    }

    //PlaceHolder
    private void setPlaceholder(TextField field, String text){
        if(field.getText().isEmpty()){
            field.setText(text);
            field.setFont(new Font("Consolas",11));
        }
    }
    private void clickField(TextField field){
        if(field.getFont().equals(new Font("Consolas",11))){
            field.setText("");
            field.setFont(new Font("Segoe UI",12));
        }
    }
    //



}
