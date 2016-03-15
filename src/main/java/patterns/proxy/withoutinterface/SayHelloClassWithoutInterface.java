package patterns.proxy.withoutinterface;

public class SayHelloClassWithoutInterface {
	public static final String REPLY_PREFIX = "Hello ";

	public String sayHello(String name) {
		return REPLY_PREFIX + name;
	}
}
