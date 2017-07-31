package main.dialog;

import java.awt.*;

import javax.swing.*;

import main.data.Lang;

public class ItemSetChooser extends JDialog {
	public ItemSetChooser(Window window) {
		super(window);
		setTitle(Lang.t("itemset-chooser"));
		setSize(400, 800);
	}
}
