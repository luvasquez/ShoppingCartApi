package sv.com.cuscatlan.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import sv.com.cuscatlan.shoppingcart.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findByUserName(String userName);


}
