package utils;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for missing localizations of feature toggles
 */
public class FeatureSwitchLocalizationTest {

	private static ResourceBundle bundle;

	@BeforeClass
	public static void setUp() throws Exception {
		bundle = fetchBundle();
	}

	@Test
	public void testCorrectCode() throws Exception {
		String result = fetchTranslation(CorrectFeatureSwitch.class);

		Assert.assertTrue(result != null);
	}

	@Test(expected = MissingResourceException.class)
	public void testIncorrectCode() throws Exception {
		fetchTranslation(IncorrectFeatureSwitch.class);
	}

	private String fetchTranslation(Class<?> testedClass)
			throws MalformedURLException,
			IllegalAccessException {
		Field[] fields = testedClass.getDeclaredFields();
		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isPublic(field.getModifiers())) {
				if (field.get(null).getClass().equals(testedClass)) {
					@SuppressWarnings("unchecked")
					NamedType<String> property = (NamedType<String>) field.get(field);
					return bundle.getString(property.getId());
				}
			}
		}
		return null;
	}

	private static ResourceBundle fetchBundle() throws MalformedURLException {
		File localizationsDirectory = new File("src/test/resources/localizations");
		URL[] urls = { localizationsDirectory.toURI().toURL() };
		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle bundle = ResourceBundle.getBundle("myResource", Locale.ENGLISH, loader);
		return bundle;
	}
}
