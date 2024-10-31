package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "client",orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Request> requests;

    //////////
    public Client(){}

    /////////


}
