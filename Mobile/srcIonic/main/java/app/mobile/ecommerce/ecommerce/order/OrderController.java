package app.mobile.ecommerce.ecommerce.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.mobile.ecommerce.ecommerce.model.Order;

@RestController
@RequestMapping("/pedido")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Order>> getOrders(@RequestParam(value="user") String userId){
		return new ResponseEntity<>( userId == null ? 
				orderService.doGetByUser(Integer.valueOf(userId)) :
					orderService.doGet(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Order> getOrders(@PathVariable("id") Integer id){
		return new ResponseEntity<>(orderService.doGet(id), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> postOrder(@RequestBody Order form){
		orderService.doPost(form);
		return new ResponseEntity<>(String.valueOf(true), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id){
		orderService.doDelete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
