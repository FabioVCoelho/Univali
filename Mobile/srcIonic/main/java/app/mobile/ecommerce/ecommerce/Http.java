package app.mobile.ecommerce.ecommerce;

import java.util.List;

public abstract class Http <Entity,Key>{
	
	protected abstract List<Entity> doGet();
	protected abstract Entity doGet(Key k);
	protected abstract void doPost(Entity e);
	protected abstract void doPut(Key k, Entity e);
	protected abstract void doDelete(Key k);
	
	

}
