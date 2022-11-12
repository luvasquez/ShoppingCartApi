package sv.com.cuscatlan.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.com.cuscatlan.shoppingcart.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
