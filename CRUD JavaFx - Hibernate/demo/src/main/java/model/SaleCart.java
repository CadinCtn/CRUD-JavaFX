package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sale_cart")
public class SaleCart {

    @EmbeddedId()
    private CartId cartId;

    @OneToMany(cascade = CascadeType.ALL)
    @MapsId("idProduct")
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("idRequest")
    private Request request;

    @Column(nullable = false)
    private int quantity;

}

@Embeddable
class CartId{

    long idProduct;
    long idRequest;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartId = (CartId) o;
        return idProduct == cartId.idProduct && idRequest == cartId.idRequest;
    }

    @Override
    public int hashCode(){
        return Objects.hash(idProduct, idRequest);
    }
}
