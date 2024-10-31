package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(referencedColumnName = "requests")
    private Client client;
    @Column(nullable = false)
    private Date dateRequest;
    @Column(nullable = false)
    private int deadline;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<SaleCart> products;

    ///////////

    public Request(){}

    //////////

}
