package main.StateRecorder;

import javax.swing.*;
import static main.Library.LibrarySugar.log;
import main.Library.LibraryIO;

public class StateManager {
	public static final String DEFAULT_USER_FOLDER = getDefaultUserDocumentDirectory();
	public static final String OPENRELIC_FOLDER = "/OpenRelic/";
	
	public static final int MainFrameState = 0;
	public static final int RelicViewerState = 1;
	public static final int RelicordingItemHash = 2;
	public static final int RelicordingWishList = 3;
	
	private static final String[] StateFolder = {
		"MainFrameState.dat",
		"RelicViewerState.dat",
		"RelicordingItemHash.dat",
		"RelicordingWishList.dat"
	};
	
	public static void writeState(String content, int code) {
		log(DEFAULT_USER_FOLDER + StateFolder[code]);
		LibraryIO.writeFile(DEFAULT_USER_FOLDER + StateFolder[code], content);
	}
	
	public static String getDefaultUserDocumentDirectory() {
		return new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + OPENRELIC_FOLDER;
	}
}
