package main;

import java.io.*;

import main.Data.*;
import main.Frame.MainFrame;

public class OpenRelicMain {
	public static void main(String[] args) {
		init();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { new MainFrame().run(); }
		});
	}
	
	private static void init() {
		setLog();
		Lang.init();
		RelicData.init();
		ColorManager.init();
	}
	
	private static void setLog() {
		if (new File("debug.flag").exists()) return ;
		new File("log").mkdirs();
		String path = String.format("log/error%d.log", System.currentTimeMillis());
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			out = System.err;
			e.printStackTrace();
		}
		System.setErr(out);
	}
}
