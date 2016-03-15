package patterns.proxy.withinterface;

public class SayHelloClass implements SayHelloInterface {
	public static final String REPLY_PREFIX = "Hello ";

	@Override
	public String sayHello(String name) {
		return REPLY_PREFIX + name;
	}
}
