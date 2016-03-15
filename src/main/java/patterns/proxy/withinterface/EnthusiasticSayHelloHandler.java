package patterns.proxy.withinterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnthusiasticSayHelloHandler implements InvocationHandler {

	public static final String REPLY_SUFFIX = "!";
	private final SayHelloInterface sayHelloObject;

	public EnthusiasticSayHelloHandler(SayHelloInterface sayHelloObject) {
		this.sayHelloObject = sayHelloObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("handler starts");
		String reply = (String) method.invoke(sayHelloObject, args);
		System.out.println("handler ends");
		return reply + REPLY_SUFFIX;
	}
}