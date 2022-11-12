package sv.com.cuscatlan.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.com.cuscatlan.shoppingcart.model.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByClientId(final Integer id);

}
