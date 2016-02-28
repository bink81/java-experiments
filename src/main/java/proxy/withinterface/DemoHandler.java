package proxy.withinterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DemoHandler implements InvocationHandler {

	private final OriginalInterface originalObject;

	public DemoHandler(OriginalInterface originalObject) {
		this.originalObject = originalObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("handler starts");
		method.invoke(originalObject, args);
		System.out.println("handler ends");
		return null;
	}
}