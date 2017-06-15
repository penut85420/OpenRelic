package main.frame.RelicViewer;

import java.awt.*;
import javax.swing.*;

import java.util.*;

import main.data.Lang;
import main.frame.CtrlClass;

public class RelicViewer extends JPanel implements CtrlClass {
	private static final long serialVersionUID = 1L;

	JTable mRelicTable;
	JTextField mSearchField;
	JButton mSearchButton;
	
	ArrayList<JComponent> mObjectArray;
	
	public RelicViewer() {
		init();
		initGUI();
		initObjArr();
	}

	private void init() {
		setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		mRelicTable = new JTable(new RelicViewerTableModel());
		mSearchField = new JTextField();
		mSearchButton = new JButton(Lang.t("search"));
		
		JPanel top = new JPanel(new GridLayout(0, 3));
		JPanel topLeft = new JPanel(new BorderLayout());
		topLeft.add(mSearchField, BorderLayout.CENTER);
		topLeft.add(mSearchButton, BorderLayout.EAST);
		top.add(topLeft);
		
		add(top, BorderLayout.NORTH);
		add(new JScrollPane(mRelicTable), BorderLayout.CENTER);
	}
	
	private void initObjArr() {
		mObjectArray.add(mSearchField);
		mObjectArray.add(mSearchButton);
		mObjectArray.add(mRelicTable);
	}

	@Override
	public void refresh() {
		mSearchButton.setText(Lang.t("search"));
		mRelicTable.setModel(new RelicViewerTableModel());
	}
}
