package main.StateRecorder;

import javax.swing.*;
import static main.Library.LibrarySugar.log;
import main.Library.LibraryIO;

@SuppressWarnings("unused")
public class StateManager {
	public static final String DEFAULT_USER_FOLDER = getDefaultUserDocumentDirectory();
	public static final String OPENRELIC_FOLDER = "\\OpenRelic\\";
	
	public static final int MainFrameState = 0;
	public static final int RelicViewerState = 1;
	public static final int RelicordingItemHash = 2;
	public static final int RelicordingWishList = 3;
	
	private static final String[] StateFile = {
		"MainFrameState.dat",
		"RelicViewerState.dat",
		"RelicordingItemHash.dat",
		"RelicordingWishList.dat"
	};
	
	public static void writeState(String content, int code) {
		LibraryIO.writeFile(getStateFile(code), content);
	}
	
	public static String[] loadState(int code) {
		String raw = LibraryIO.readFile(getStateFile(code));
		if (raw == null || raw.isEmpty()) return new String[0];
		
		return raw.replaceAll("\r", "").split("\n");
	}
	
	public static String getStateFile(int code) {
		return DEFAULT_USER_FOLDER + StateFile[code];
	}
	
	public static String getDefaultUserDocumentDirectory() {
		return new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + OPENRELIC_FOLDER;
	}
}
