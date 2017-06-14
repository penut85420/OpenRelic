package main;

import main.frame.MainFrame;

public class WarframeVoidHelperMain {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { new MainFrame().run(); }
		});
	}
}
