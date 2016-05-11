package translations;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class TranslationProvider {
	private final String bundleName;

	public TranslationProvider(String bundleName) {
		this.bundleName = bundleName;
	}

	public ResourceBundle fetchBundle() throws MalformedURLException {
		File localizationsDirectory = new File("bundles");
		URL[] urls = { localizationsDirectory.toURI().toURL() };
		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, Locale.ENGLISH, loader);
		return bundle;
	}

	public String fetchGeneral(String key) {
		String result = null;
		try {
			ResourceBundle bundle = fetchBundle();
			return bundle.getString(key);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getBundleName() {
		return bundleName;
	}
}
