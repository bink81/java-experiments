package code.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class CollectionSafeTest {

	private static final Class<CollectionUnsafeClass> UNSAFE_CLASS = CollectionUnsafeClass.class;

	private static final Class<CollectionSafeClass> SAFE_CLASS = CollectionSafeClass.class;

	@Test
	public void testSafeClassForEquals() throws Exception {
		Class<CollectionSafeClass> klass = SAFE_CLASS;

		Class<?> providerClass = checkEqualsMethod(klass);
		Assert.assertEquals(klass, providerClass);
	}

	@Test
	public void testSafeClassForHashCode() throws Exception {
		Class<CollectionSafeClass> klass = SAFE_CLASS;

		Class<?> providerClass = checkHashCodeMethod(klass);
		Assert.assertEquals(klass, providerClass);
	}

	@Test
	public void testUnsafeClassForEquals() throws Exception {
		Class<CollectionUnsafeClass> klass = UNSAFE_CLASS;

		Class<?> providerClass = checkEqualsMethod(klass);
		Assert.assertFalse(klass.equals(providerClass));
	}

	@Test
	public void testUnsafeClassForHashCode() throws Exception {
		Class<CollectionUnsafeClass> klass = UNSAFE_CLASS;

		Class<?> providerClass = checkHashCodeMethod(klass);
		Assert.assertFalse(klass.equals(providerClass));
	}

	private Class<?> checkEqualsMethod(Class<?> klass) {
		String methodName = "equals";
		Class<?> methodParameters[] = { Object.class };

		return checkClass(klass, methodName, methodParameters);
	}

	private Class<?> checkHashCodeMethod(Class<?> klass) {
		String methodName = "hashCode";
		Class<?> methodParameters[] = {};

		return checkClass(klass, methodName, methodParameters);
	}

	private Class<?> checkClass(Class<?> klass, String methodName, Class<?>[] parameters) {
		Annotation annotation = klass.getAnnotation(CollectionSafe.class);
		if (annotation instanceof CollectionSafe) {
			return checkMethod(klass, methodName, parameters);
		}
		else {
			throw new RuntimeException("No annotation in class " + klass);
		}
	}

	private Class<?> checkMethod(Class<?> klass, String methodName, Class<?>[] parameters) {
		try {
			Method method = klass.getMethod(methodName, parameters);
			return method.getDeclaringClass();
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("No method '" + methodName + "' in class " + klass);
		} catch (SecurityException e) {
			throw new RuntimeException("SecurityException", e);
		}
	}
}
