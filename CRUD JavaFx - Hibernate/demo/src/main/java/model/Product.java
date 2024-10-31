package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private int quantityStock;
    @Column()
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SaleCart> requests;

    ///////////

    public Product(){}

    //////////

    public void addStock(int quantity){
        this.quantityStock+=quantity;
    }

    public void removeStock(int quantity){
        this.quantityStock-=quantity;
    }

}
