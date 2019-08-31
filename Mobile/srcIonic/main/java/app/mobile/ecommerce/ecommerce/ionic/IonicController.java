package app.mobile.ecommerce.ecommerce.ionic;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/ionic")
public class IonicController {

	private String content = "Proof of concept";
	private int update = 0;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> doGet() {
		return new ResponseEntity<>(this.content + " " + update, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> doPut(){
		this.update++;
		return new ResponseEntity<>("Atualizado com successo.", HttpStatus.OK);
	}
}
