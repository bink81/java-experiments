package proxy.withinterface;

import java.lang.reflect.Proxy;

public class ProxyWithInterfaceDemo {
	public static void main(String[] args) {
		OriginalInterface originalObject = new OriginalClass();
		DemoHandler handler = new DemoHandler(originalObject);
		OriginalInterface proxy = (OriginalInterface) Proxy.newProxyInstance(OriginalInterface.class.getClassLoader(),
				new Class[] { OriginalInterface.class }, handler);
		proxy.originalMethod("Hello world");
	}
}