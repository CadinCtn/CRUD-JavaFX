package service;

import connectionFactory.Connection;
import connectionFactory.SimpleEntityManager;
import controller.ControllerStage;
import controller.user.ControllerNewUser;
import dao.user.UserDao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.List;

public class UserService extends GenericService{

    private SimpleEntityManager simpleEntityManager;
    private UserDao dao;

    public UserService(){
        this.simpleEntityManager = Connection.getSimpleEntityManager();
        dao = new UserDao(simpleEntityManager);
    }

    public void create(User user) throws Exception{
        dao.create(user); //INSERT
    }

    public User read(int id){
        return dao.read(id); //Retorna usuario pelo ID
    }

    public List<User> readAll(){
        return dao.readAll(); //SELECT
    }

    public void update(User user) throws Exception{
        dao.update(user); //UPDATE
    }

    public void delete(int id) throws Exception{
        dao.delete(id); // DELETE
    }

    //Busca usuário por login e senha
    public User login(String login, String password){
        return dao.login(login, password);
    }

    ////////////////////

    //Abre tela de novo usuário
    public void goToNewUser(User user) throws IOException{
        Stage stage = ControllerStage.getPrimaryStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/windows/user/newUser.fxml"));
        stage.setScene(new Scene(loader.load(), stage.getWidth(), stage.getHeight()));
        stage.setTitle("Novo usuário");

        if(user != null){
            ControllerNewUser controller = loader.getController();
            controller.fillFields(user);
        }

    }

}
