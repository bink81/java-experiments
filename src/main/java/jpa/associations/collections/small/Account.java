package jpa.associations.collections.small;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import jpa.associations.collections.huge.AccountTransaction;

@Entity
public class Account {
	@Id
	Long id;

	@OneToMany
	@JoinColumn(name = "account_id")
	private List<AccountTransaction> transactions;
}
