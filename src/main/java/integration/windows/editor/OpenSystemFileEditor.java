package integration.windows.editor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenSystemFileEditor {
	public static void main(String[] args) throws IOException {
		Desktop.getDesktop().open(new File("c:\\windows-version.txt"));
	}
}
