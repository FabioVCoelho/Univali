package app.mobile.ecommerce.ecommerce.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.mobile.ecommerce.ecommerce.Http;
import app.mobile.ecommerce.ecommerce.model.Product;

@Service
public class ProductService extends Http<Product, Integer>{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	protected List<Product> doGet() {
		List<Product> products = new ArrayList<>();
		productRepository.findAllOrderbyTotal().forEach(products::add);
		return products;
	}

	@Override
	protected Product doGet(Integer k) {
		Optional<Product> product = productRepository.findById(k);
		return product.get();
	}

	@Override
	protected void doPost(Product e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPut(Integer k, Product e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doDelete(Integer k) {
		// TODO Auto-generated method stub
		
	}

}
