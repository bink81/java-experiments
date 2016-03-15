package patterns.specification;

public class OverduePaymentSpecification implements PaymentSpecification {
	@Override
	public boolean isSatisfiedBy(Payment payment) {
		// Business rules extracted from Payment.isOverdue() method
		return true;
	}
}
