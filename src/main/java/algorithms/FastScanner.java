package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastScanner {
	private BufferedReader bufferedReader;
	private StringTokenizer stringTokenizer;

	public FastScanner(InputStream stream) {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(stream));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String popNextToken() {
		while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
			try {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stringTokenizer.nextToken();
	}

	public int popNextInt() {
		return Integer.parseInt(popNextToken());
	}
}