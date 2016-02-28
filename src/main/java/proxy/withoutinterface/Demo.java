package proxy.withoutinterface;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class Demo {
	public static void main(String[] args) {
		OriginalClass originalObject = new OriginalClass();
		MethodInterceptor handler = new DemoHandler(originalObject);
		OriginalClass proxy = (OriginalClass) Enhancer.create(OriginalClass.class, handler);
		proxy.originalMethod("Hello world");
	}
}