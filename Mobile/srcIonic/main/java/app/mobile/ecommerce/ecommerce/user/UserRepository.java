package app.mobile.ecommerce.ecommerce.user;

import org.springframework.data.repository.CrudRepository;

import app.mobile.ecommerce.ecommerce.model.User;

public interface UserRepository extends CrudRepository<User,Integer>{
	
	User findByUsername(String username);

}
