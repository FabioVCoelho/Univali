package app.mobile.ecommerce.ecommerce.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.mobile.ecommerce.ecommerce.Http;
import app.mobile.ecommerce.ecommerce.model.Item;

@Service
public class ItemService extends Http<Item,Integer>{

	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<Item> doGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item doGet(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doPost(Item e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPut(Integer k, Item e) {
		
	}

	@Override
	public void doDelete(Integer k) {
		// TODO Auto-generated method stub
		
	}


}
