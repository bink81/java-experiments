package patterns.specification;

public interface PaymentSpecification {
	public boolean isSatisfiedBy(Payment payment);
}
