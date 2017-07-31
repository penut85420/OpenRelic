package main.dialog;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

import main.data.Lang;
import main.library.LibraryIO;
import main.res.Res;

public class AboutDev extends JDialog {
	public AboutDev(Window window) {
		super(window);
		init();
		initGUI();
	}
	
	private void initGUI() {
		String content = LibraryIO.readFile(new File("data/about-dev.txt"));
		
		JLabel titleImg = new JLabel(Res.getTitle());
		JLabel txt = new JLabel(content);
		
		titleImg.setBorder(new EmptyBorder(20, 0, 0, 0));
		txt.setHorizontalAlignment(JLabel.CENTER);
		
		add(titleImg, BorderLayout.NORTH);
		add(txt, BorderLayout.CENTER);
	}

	private void init() {
		setModal(true);
		setTitle(Lang.t("about-dev"));
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
	}
}
