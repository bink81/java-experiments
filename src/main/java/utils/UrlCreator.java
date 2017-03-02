package utils;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.base.Preconditions;

public class UrlCreator {
	private final String string;

	public UrlCreator(String string) {
		Preconditions.checkNotNull(string);
		Preconditions.checkArgument(!string.isEmpty());
		this.string = string;
	}

	public String normalize() throws MalformedURLException {
		URL url = new URL(string);
		return url.toExternalForm();
	}

	public String getString() {
		return string;
	}
}
