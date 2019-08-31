package app.mobile.ecommerce.ecommerce.order;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.mobile.ecommerce.ecommerce.model.Order;
import app.mobile.ecommerce.ecommerce.model.User;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Order,Integer> {

	List<Order> findByUser(User user);
}
