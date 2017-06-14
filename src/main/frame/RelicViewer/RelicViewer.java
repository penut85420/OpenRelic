package main.frame.RelicViewer;

import java.awt.*;
import javax.swing.*;

public class RelicViewer extends JPanel {
	private static final long serialVersionUID = 1L;

	JTable mRelicTable;
	
	public RelicViewer() {
		init();
		initGUI();
	}
	
	private void init() {
		setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		mRelicTable = new JTable(new RelicViewerTableModel());
		
		add(new JScrollPane(mRelicTable), BorderLayout.CENTER);
	}
}
