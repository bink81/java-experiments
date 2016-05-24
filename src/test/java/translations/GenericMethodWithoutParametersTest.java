package translations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.reflect.ClassPath;

/**
 * Detect suspicious method, e.g.
 * <X extends CharSequence> X getCharSequence() {
 * return (X) "hello";
 * }
 *
 * That java allows to abuse:
 * Integer i = getCharSequence();
 */
public class GenericMethodWithoutParametersTest {
	@Test
	public void test() throws Exception {
		ArrayList<String> errors = new ArrayList<>();
		Consumer<String> errorAction = new Consumer<String>() {
			@Override
			public void accept(String suspectMethodSignature) {
				System.out.println(suspectMethodSignature);
				errors.add(suspectMethodSignature);
			}
		};
		ClassPath
			.from(Thread.currentThread().getContextClassLoader()).getTopLevelClasses().stream()
			.filter(
				info -> !info.getPackageName().startsWith("com.")
						&& !info.getPackageName().startsWith("org.")
						&& !info.getPackageName().startsWith("net.")
						&& !info.getPackageName().startsWith("javax.")
						&& !info.getPackageName().startsWith("java."))
			.flatMap(info -> {
				try {
					return Stream.of(info.load());
				} catch (Throwable ignore) {
					return Stream.empty();
				}
			}).flatMap(c -> {
				try {
					return Stream.of(c.getMethods());
				} catch (Throwable ignore) {
					return Stream.<Method> of();
				}
			}).filter(m -> m.getTypeParameters().length > 0 && m.getParameterCount() == 0)
			.sorted(Comparator.comparing(Method::toString)).map(Method::toGenericString)
			.forEach(errorAction);

		Assert.assertTrue(errors.isEmpty());
	}
}
