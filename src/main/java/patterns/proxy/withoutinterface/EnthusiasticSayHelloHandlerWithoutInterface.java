package patterns.proxy.withoutinterface;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class EnthusiasticSayHelloHandlerWithoutInterface implements MethodInterceptor {
	public static final String REPLY_SUFFIX = "!";

	private final SayHelloClassWithoutInterface originalObject;

	public EnthusiasticSayHelloHandlerWithoutInterface(SayHelloClassWithoutInterface originalObject) {
		this.originalObject = originalObject;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("handler starts");
		String reply = (String) method.invoke(originalObject, args);
		System.out.println("handler ends");
		return reply + REPLY_SUFFIX;
	}
}