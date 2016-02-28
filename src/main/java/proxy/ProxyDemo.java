package proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {
	public static void main(String[] args) {
		OriginalInterface originalObject = new OriginalClass();
		Handler handler = new Handler(originalObject);
		OriginalInterface proxy = (OriginalInterface) Proxy.newProxyInstance(OriginalInterface.class.getClassLoader(),
				new Class[] { OriginalInterface.class }, handler);
		proxy.originalMethod("Hello world");
	}
}