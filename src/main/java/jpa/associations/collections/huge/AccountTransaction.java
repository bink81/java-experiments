package jpa.associations.collections.huge;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccountTransaction {
	@Id
	Long id;

	Date date;

	@ManyToOne
	private Account account;

	public AccountTransaction(Account account, Date date) {
		this.account = account;
		this.date = date;
	}

	protected AccountTransaction() {
		/* as required by ORM/JPA */ }
}
