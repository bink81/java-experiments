package utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.common.base.Preconditions;

public class PathUtils {
	public static String assembleUrl(String... parts) throws URISyntaxException {
		if (parts != null && parts.length > 0) {
			String prefix = "";
			StringBuilder builder = new StringBuilder();
			for (String part : parts) {
				if (!isBlank(part)) {
					builder.append(prefix);
					builder.append(URI.create(part.trim()));
					prefix = "/";
				}
			}
			if (!prefix.isEmpty()) {
				return new URI(builder.toString()).normalize().toString();
			}
		}
		return null;
	}

	public static String assembleFilePath(String... parts) throws URISyntaxException {
		Preconditions.checkNotNull(parts, "parts must not be null");
		Preconditions.checkArgument(parts.length > 0, "Must have at least one part");
		StringBuilder builder = new StringBuilder();
		for (String part : parts) {
			if (!isBlank(part)) {
				builder.append(part.trim()).append(File.separator);
			}
		}
		if (builder.length() > 0) {
			builder.delete(builder.length() - 1, builder.length());
		}
		return builder.toString();
	}

	public static boolean isBlank(String part) {
		if (part == null || part.length() == 0) {
			return true;
		}
		return false;
	}
}
