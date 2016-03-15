package patterns.proxy.withoutinterface;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class EnthusiasticSayHelloHandlerWithoutInterfaceTest {
	private static final String NAME = "Robert";

	private SayHelloClassWithoutInterface originalObject;

	@Before
	public void setUp() throws Exception {
		originalObject = new SayHelloClassWithoutInterface();
	}

	@Test
	public void testSayHelloWithoutDemoHandler() throws Exception {
		String actual = originalObject.sayHello(NAME);

		Assert.assertEquals(SayHelloClassWithoutInterface.REPLY_PREFIX + NAME, actual);
	}

	@Test
	public void testSayHelloWithDemoHandler() throws Exception {
		MethodInterceptor handler = new EnthusiasticSayHelloHandlerWithoutInterface(originalObject);
		SayHelloClassWithoutInterface proxy = (SayHelloClassWithoutInterface) Enhancer
				.create(SayHelloClassWithoutInterface.class, handler);

		String actual = proxy.sayHello(NAME);

		Assert.assertEquals(
				SayHelloClassWithoutInterface.REPLY_PREFIX + NAME + EnthusiasticSayHelloHandlerWithoutInterface.REPLY_SUFFIX,
				actual);
	}
}
