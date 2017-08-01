package main.Dialog.LanguageEditor;

import java.awt.*;

import javax.swing.*;

import main.Data.Lang;
import main.Frame.MainFrame;

public class LanguageEditor extends JDialog {
	
	JTable mLangEdit;
	
	public LanguageEditor() {
		super(MainFrame.mFrame);
		init();
		initGUI();
	}
	
	private void init() {
		setModal(true);
		setSize(800, 600);
		setTitle(Lang.t("lang-editor"));
		getContentPane().setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		
	}
}
