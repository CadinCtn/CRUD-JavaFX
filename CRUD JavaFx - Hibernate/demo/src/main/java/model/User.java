package model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    /////////////////////////

    public User() {}

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    ///////////////

    public boolean isValid(){
        return name != null &&
               login != null &&
               password != null;
    }


    /////////////
    //GET
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    //SET
    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
