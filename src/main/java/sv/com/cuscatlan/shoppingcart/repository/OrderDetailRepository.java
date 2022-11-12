package sv.com.cuscatlan.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.com.cuscatlan.shoppingcart.model.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> findOrderDetailsByIdNotIn(List<Integer> ids);

}
