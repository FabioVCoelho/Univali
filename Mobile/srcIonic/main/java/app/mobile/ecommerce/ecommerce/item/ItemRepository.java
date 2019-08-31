package app.mobile.ecommerce.ecommerce.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.mobile.ecommerce.ecommerce.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>{

}
