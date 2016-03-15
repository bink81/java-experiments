package patterns.proxy.withinterface;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class EnthusiasticSayHelloHandlerTest {

	private static final String NAME = "Robert";
	private SayHelloInterface originalObject;

	@Before
	public void setUp() throws Exception {
		originalObject = new SayHelloClass();
	}

	@Test
	public void testSayHelloWithoutDemoHandler() throws Exception {
		String actual = originalObject.sayHello(NAME);

		Assert.assertEquals(SayHelloClass.REPLY_PREFIX + NAME, actual);
	}

	@Test
	public void testSayHelloWithDemoHandler() throws Exception {
		EnthusiasticSayHelloHandler handler = new EnthusiasticSayHelloHandler(originalObject);
		SayHelloInterface proxy = (SayHelloInterface) Proxy.newProxyInstance(SayHelloInterface.class.getClassLoader(),
				new Class[] { SayHelloInterface.class }, handler);

		String actual = proxy.sayHello(NAME);

		Assert.assertEquals(SayHelloClass.REPLY_PREFIX + NAME + EnthusiasticSayHelloHandler.REPLY_SUFFIX, actual);
	}
}
