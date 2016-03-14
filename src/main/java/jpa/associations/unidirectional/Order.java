package jpa.associations.unidirectional;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Order {
	@Id
	private Long id;

	@OneToMany
	@JoinColumn(name = "order_id")
	private List<OrderItem> items;
}
