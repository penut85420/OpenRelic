package main;

import java.io.*;

import main.Data.*;
import main.Frame.MainFrame;

public class OpenRelicMain {
	public static void main(String[] args) throws FileNotFoundException {
		setLog();
		Lang.init();
		RelicData.init();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { new MainFrame().run(); }
		});
	}
	
	private static void setLog() throws FileNotFoundException {
		if (new File("debug.flag").exists()) return ;
		new File("log").mkdirs();
		String path = String.format("log/error%d.log", System.currentTimeMillis());
		PrintStream out = new PrintStream(new FileOutputStream(path));
		System.setErr(out);
	}
}
