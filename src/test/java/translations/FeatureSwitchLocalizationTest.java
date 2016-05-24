package translations;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import translations.testterms.CorrectFeatureSwitches;
import translations.testterms.MissingFeatureSwitches;
import utils.NamedType;

/**
 * Test for missing localizations of feature toggles
 */
public class FeatureSwitchLocalizationTest {

	private static ResourceBundle bundle;

	@BeforeClass
	public static void setUp() throws Exception {
		bundle = new TranslationProvider("testResource").fetchBundle();
	}

	@Test
	public void testCorrectCode() throws Exception {
		String result = fetchTranslation(CorrectFeatureSwitches.class);

		Assert.assertTrue(result != null);
	}

	@Test(expected = MissingResourceException.class)
	public void testIncorrectCode() throws Exception {
		fetchTranslation(MissingFeatureSwitches.class);
	}

	private String fetchTranslation(Class<?> testedClass)
			throws MalformedURLException,
			IllegalAccessException {
		Field[] fields = testedClass.getDeclaredFields();
		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isPublic(field.getModifiers())
					&& field.get(null).getClass().equals(testedClass)) {
				@SuppressWarnings("unchecked")
				NamedType<String> property = (NamedType<String>) field.get(field);
				return bundle.getString(property.getId());
			}
		}
		return null;
	}
}
