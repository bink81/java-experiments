package jpa.associations.bidirectional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {
	@Id
	private Long id;

	@ManyToOne
	private Order order;
}
