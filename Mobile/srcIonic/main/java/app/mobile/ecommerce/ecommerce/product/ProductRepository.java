package app.mobile.ecommerce.ecommerce.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.mobile.ecommerce.ecommerce.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{
	
	@Query(nativeQuery=true, value = "select p from ecommerce.product p "
		+ " join (select product_id, sum(quantity) as total from ecommerce.product group by product_id) as sub "
		+ " on (sub.product_id = p.sku) "
		+ " order by sub.total DESC ")
	List<Product> findAllOrderbyTotal();

}
