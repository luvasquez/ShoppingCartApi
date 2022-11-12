package sv.com.cuscatlan.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import sv.com.cuscatlan.shoppingcart.model.Role;

import java.util.Optional;

public interface RolRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(String name);

}


