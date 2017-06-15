package main;

import main.data.*;
import main.frame.MainFrame;

public class OpenRelicMain {
	public static void main(String[] args) {
		Lang.init();
		RelicData.init();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { new MainFrame().run(); }
		});
	}
}
