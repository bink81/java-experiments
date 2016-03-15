package patterns.specification;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ProjectTest {

	private Project theProject;

	@Before
	public void setUp() throws Exception {
		theProject = new Project();
	}

	@Test
	public void testIsOverdueWithoutSpecificationPattern() throws Exception {
		// without specification pattern the project needs to include business
		// logic which unnecessarily adds complexity
		Assert.assertTrue(theProject.isOverdue());
	}

	@Test
	public void testIsOverdueWithSpecificationPattern() throws Exception {
		// with specification pattern, the business logic can be decoupled from
		// the entity and made it a responsibility of another class which makes
		// the client code flexible, readable and allow the entity to focus on
		// it's state
		ProjectSpecification projectIsOverdueSpecification = new OverdueProjectSpecification();
		Assert.assertTrue(projectIsOverdueSpecification.isSatisfiedBy(theProject));
	}
}
