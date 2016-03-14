package jpa.associations.collections.huge;

public interface AccountTransactionRepository {
	Page<AccountTransaction> findByAccount(Long accountId, int offset, int pageSize);
}
