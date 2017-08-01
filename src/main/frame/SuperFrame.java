package main.frame;

import java.util.*;

import javax.swing.*;

import main.data.Lang;

public interface SuperFrame {
	ArrayList<JComponent> mObjFont = new ArrayList<>();
	HashMap<JComponent, String> mObjLang = new HashMap<>();
	
	public void refresh();
	public void onClose();
	
	default public void refreshDefault() {
		for (JComponent c: mObjLang.keySet()) {
			if (c instanceof JLabel)
				((JLabel)c).setText(Lang.t(mObjLang.get(c)));
			else if (c instanceof JButton)
				((JButton)c).setText(Lang.t(mObjLang.get(c)));
			else if (c instanceof JCheckBox)
				((JCheckBox)c).setText(Lang.t(mObjLang.get(c)));
		}
		
		for (JComponent c: mObjFont)
			c.setFont(MainFrame.mGlobalFont);
	};
	
	default public void addObj(JComponent c) {
		mObjFont.add(c);
	}
	
	default public void addObj(JComponent c, String s) {
		mObjLang.put(c, s);
		addObj(c);
	}
}
