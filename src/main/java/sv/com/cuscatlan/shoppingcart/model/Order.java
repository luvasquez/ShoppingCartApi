package sv.com.cuscatlan.shoppingcart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "order_definition")
@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String orderDescription;

    @OneToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = OrderDetail.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderDetail> details;

}
