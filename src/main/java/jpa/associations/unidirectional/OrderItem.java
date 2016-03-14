package jpa.associations.unidirectional;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItem {
	@Id
	private Long id;

	// @ManyToOne
	// private Order order;
}
