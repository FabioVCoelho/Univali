package app.mobile.ecommerce.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
	@SequenceGenerator(name="item_generator", sequenceName = "item_id_seq")
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;
	@Column
	private Integer quantity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
