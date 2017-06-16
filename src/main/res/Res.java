package main.res;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Res {
	public static Image getIcon() {
		return new ImageIcon(Res.class.getResource("icon.png")).getImage();
	}
}
