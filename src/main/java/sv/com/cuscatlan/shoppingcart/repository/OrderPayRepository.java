package sv.com.cuscatlan.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.com.cuscatlan.shoppingcart.model.OrderPay;

public interface OrderPayRepository extends JpaRepository<OrderPay, Integer> {
}
