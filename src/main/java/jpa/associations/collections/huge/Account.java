package jpa.associations.collections.huge;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	Long id;

	// Replace OneToMany witgh service that supports pagination
	// @OneToMany
	// @JoinColumn(name = "account_id")
	// private List<AccountTransaction> transactions;
}
