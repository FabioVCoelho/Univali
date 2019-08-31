package app.mobile.ecommerce.ecommerce.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import app.mobile.ecommerce.ecommerce.Http;
import app.mobile.ecommerce.ecommerce.exception.EcommerceException;
import app.mobile.ecommerce.ecommerce.model.User;

@Service
public class UserService extends Http<User,Integer>{
	
	@Autowired
	private UserRepository userRepository;
	
	public Integer login(String username, String password){
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new EcommerceException(HttpStatus.BAD_REQUEST);
		}
		return user.getPassword().equals(password) ? user.getId() : Integer.valueOf(-1);
	}

	@Override
	public List<User> doGet() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	@Override
	public User doGet(Integer k) {
		Optional<User> user = userRepository.findById(k);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public void doPost(User e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * atualiza endereco
	 */
	@Override
	public void doPut(Integer k, User e) {
		Optional<User> user = userRepository.findById(k);
		if(user.isPresent()){
			user.get().getAddress().setStreet(e.getAddress().getStreet());
			user.get().getAddress().setNum(e.getAddress().getNum());
			user.get().getAddress().setExtra(e.getAddress().getExtra());
			user.get().getAddress().setZipCode(e.getAddress().getZipCode());
			user.get().getAddress().setCity(e.getAddress().getCity());
			user.get().getAddress().setState(e.getAddress().getState());
			user.get().getAddress().setCountry(e.getAddress().getCountry());
			userRepository.save(user.get());
		}
	}

	@Override
	public void doDelete(Integer k) {
		// TODO Auto-generated method stub	
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
