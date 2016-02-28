package proxy.withoutinterface;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DemoHandler implements MethodInterceptor {

	private final OriginalClass originalObject;

	public DemoHandler(OriginalClass originalObject) {
		this.originalObject = originalObject;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("handler starts");
		method.invoke(originalObject, args);
		System.out.println("handler ends");
		return null;
	}
}