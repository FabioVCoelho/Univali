package app.mobile.ecommerce.ecommerce.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.mobile.ecommerce.ecommerce.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> doGet(@PathVariable("id") Integer id){
		return new ResponseEntity<User>(userService.doGet(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> doPut(@PathVariable("id") Integer id, @RequestBody User form){
		userService.doPut(id, form);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> doLogin( @RequestBody User form){
		Integer id = userService.login(form.getUsername(), form.getPassword());
		return new ResponseEntity<String>(String.valueOf(id), HttpStatus.OK);
	}
}
