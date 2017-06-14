package main.frame;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;

import main.data.RelicDataIO;
import main.frame.RelicViewer.RelicViewer;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		init();
		initGUI();
	}

	private void init() {
		setTitle("Warframe Void Helper");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
			Font f = new Font("微軟正黑體", Font.PLAIN, 14);
			Enumeration<Object> keys = UIManager.getDefaults().keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get (key);
				if (value != null && value instanceof FontUIResource)
					UIManager.put(key, f);
			}
			UIManager.put("Table.rowHeight", f.getSize() + f.getSize() / 10);
			UIManager.put("Tree.rowHeight", f.getSize() + f.getSize() / 10);
			UIManager.put("Tree.closedIcon", new ImageIcon());
			UIManager.put("Tree.openIcon", new ImageIcon());
			UIManager.put("Tree.leafIcon", new ImageIcon());
		} catch (Exception e) { e.printStackTrace(); }
		new RelicDataIO();
	}
	
	private void initGUI() {
		JTabbedPane tabPane = new JTabbedPane();
		
		tabPane.addTab("Relic Viewer", new RelicViewer());
		
		add(tabPane);
	}

	public void run() { setVisible(true); }
}
