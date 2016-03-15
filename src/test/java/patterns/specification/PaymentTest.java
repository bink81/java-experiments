package patterns.specification;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class PaymentTest {

	private Payment thePayment;

	@Before
	public void setUp() throws Exception {
		thePayment = new Payment();
	}

	@Test
	public void testIsOverdueWithoutSpecificationPattern() throws Exception {
		// without specification pattern the payment needs to include business
		// logic which unnecessarily adds complexity
		Assert.assertTrue(thePayment.isOverdue());
	}

	@Test
	public void testIsOverdueWithSpecificationPattern() throws Exception {
		// with specification pattern, the business logic can be decoupled from
		// the entity and made it a responsibility of another class which makes
		// the client code flexible, readable and allow the entity to focus on
		// it's state
		PaymentSpecification projectIsOverdueSpecification = new OverduePaymentSpecification();
		Assert.assertTrue(projectIsOverdueSpecification.isSatisfiedBy(thePayment));
	}
}
