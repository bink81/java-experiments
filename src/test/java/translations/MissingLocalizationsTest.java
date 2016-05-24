package translations;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.reflect.ClassPath;

import utils.NamedType;

public class MissingLocalizationsTest {
	@Test
	public void test() throws Exception {
		Consumer<Class> action = new Consumer<Class>() {
			@Override
			public void accept(Class klass) {
				try {
					checkTranslatableFields(klass);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		};
		ClassPath
			.from(Thread.currentThread().getContextClassLoader()).getTopLevelClasses().stream()
			.filter(
				classPath -> classPath.getPackageName().startsWith("translations")
						&& !classPath.getPackageName().endsWith("testterms"))
			.flatMap(classPath -> {
				try {
					Class<?> klass = classPath.load();
					return Stream.of(klass);
				} catch (Throwable t) {
					return Stream.empty();
				}
			}).filter(clazz -> !clazz.isAnnotation()).forEach(action);
	}

	private String checkTranslatableFields(Class<?> testedClass)
			throws MalformedURLException,
			IllegalAccessException {
		Field[] fields = testedClass.getDeclaredFields();
		if (fields.length > 0) {
			for (Field field : fields) {
				String name = determineBundleName(testedClass, field);
				if (java.lang.reflect.Modifier.isPublic(field.getModifiers())
						&& field.get(null).getClass().equals(testedClass)) {
					ResourceBundle bundle = new TranslationProvider(name).fetchBundle();
					@SuppressWarnings("unchecked")
					NamedType<String> property = (NamedType<String>) field.get(field);
					String internalString = property.getId();
					// FIXME RM:internalString NOCOMMIT
					System.err.println("#internalString=" + internalString);
					String externalText = bundle.getString(internalString);
					// FIXME RM:externalText NOCOMMIT
					System.err.println("#externalText=" + externalText);
					return externalText;
				}
			}
		}
		return null;
	}

	private String determineBundleName(Class<?> testedClass, Field field)
			throws IllegalAccessException {
		String name = testedClass.getSimpleName();
		if (java.lang.reflect.Modifier.isPublic(field.getModifiers())
				&& field.getName().equals("NAME")
				&& field.get(null).getClass().equals(String.class)) {
			name = (String) field.get(field);
		}
		return name;
	}
}
