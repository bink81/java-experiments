package proxy.withinterface;

public class OriginalClass implements OriginalInterface {
	@Override
	public void originalMethod(String s) {
		System.out.println(s);
	}
}
