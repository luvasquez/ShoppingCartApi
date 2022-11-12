package sv.com.cuscatlan.shoppingcart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import sv.com.cuscatlan.shoppingcart.controller.request.RoleRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.UserRequest;
import sv.com.cuscatlan.shoppingcart.service.UserService;

import java.util.ArrayList;

@SpringBootApplication ( exclude = {SecurityAutoConfiguration.class} )
public class ShoppingCartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApiApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRol(new RoleRequest(null, "ROLE_USER"));
			userService.saveRol(new RoleRequest(null, "ROLE_ADMIN"));

			userService.save(new UserRequest(null, "cusca.admin", "2022$", new ArrayList<>()));

			userService.addRoleToUser("cusca.admin", "ROLE_ADMIN");
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
