package jpa.associations.bidirectional;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Order {
	@Id
	private Long id;

	@OneToMany
	private List<OrderItem> items;
}
