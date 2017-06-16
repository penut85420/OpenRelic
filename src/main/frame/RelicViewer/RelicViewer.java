package main.frame.RelicViewer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.util.*;

import main.data.Lang;
import main.frame.SuperFrame;

public class RelicViewer extends JPanel implements SuperFrame {
	private static final long serialVersionUID = 1L;

	RelicViewerTableModel mRelicViewerTableModel;
	TableRowSorter<RelicViewerTableModel> mSorter;
	
	JTable mRelicTable;
	JLabel txtSearchTarget;
	JTextField mSearchField;
	JButton mSearchButton;
	JButton mClearButton;
	JCheckBox mInstantUpdate;
	JCheckBox mDisplayForma;
	JCheckBox mDisplayVaulted;
	
	ArrayList<JComponent> mObjFont = new ArrayList<>();
	HashMap<JComponent, String> mObjLang = new HashMap<>();
	
	public RelicViewer() {
		init();
		initGUI();
		initEvent();
		initObjArr();
	}

	private void init() {
		setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		txtSearchTarget = new JLabel(Lang.t("search-target"));
		mSearchField = new JTextField();
		mSearchButton = new JButton(Lang.t("search"));
		mClearButton = new JButton(Lang.t("clear"));
		mInstantUpdate = new JCheckBox(Lang.t("update-instant"));
		mDisplayForma = new JCheckBox(Lang.t("formabp-display"));
		mDisplayVaulted = new JCheckBox(Lang.t("vaulted-display"));
		
		// Set lable's margin, left 10px
		txtSearchTarget.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		// Set init state
		mInstantUpdate.setSelected(true);
		mDisplayForma.setSelected(true);
		mDisplayVaulted.setSelected(true);
		
		// Init table
		mRelicViewerTableModel = new RelicViewerTableModel();
		mRelicTable = new JTable(mRelicViewerTableModel);
		mSorter = new TableRowSorter<RelicViewerTableModel>(mRelicViewerTableModel);
		
		mSorter.setComparator(0, new RelicNameComparator());
		mSorter.setComparator(2, new RarityComparator());
		mRelicTable.getTableHeader().setReorderingAllowed(false);
		mRelicTable.setRowSorter(mSorter);
		
		JPanel top = new JPanel(new GridLayout(0, 3));
		
			JPanel top1 = new JPanel(new BorderLayout());
				JPanel top1_2 = new JPanel(new GridLayout(0, 2));
					top1_2.add(mSearchButton);
					top1_2.add(mClearButton);
			
			top1.add(txtSearchTarget, BorderLayout.WEST);
			top1.add(mSearchField, BorderLayout.CENTER);
			top1.add(top1_2, BorderLayout.EAST);
			
			JPanel top2 = new JPanel(new GridLayout(0, 2));
			top2.add(mInstantUpdate);
			top2.add(mDisplayForma);

		top.add(top1);
		top.add(top2);
		
		add(top, BorderLayout.NORTH);
		add(new JScrollPane(mRelicTable), BorderLayout.CENTER);
	}
	
	private void initEvent() {
		class Search implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) { search(); }
		}
		mSearchField.addActionListener(new Search());
		mSearchField.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) { ifInstantThenUpdate(); }
		});
		mSearchButton.addActionListener(new Search());
		mClearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { mSearchField.setText(""); }
		});
		mInstantUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { ifInstantThenUpdate(); }
		});
		mDisplayForma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mRelicViewerTableModel.setFormaDisplay(mDisplayForma.isSelected());
			}
		});
	}
	
	private void ifInstantThenUpdate() {
		if (mInstantUpdate.isSelected())
			search(); 
	}
	
	private void search() {
		mRelicViewerTableModel.setSearchTarget(mSearchField.getText());
	}
	
	private void initObjArr() {
		mObjFont.add(mSearchField);
		mObjFont.add(mSearchButton);
		mObjFont.add(mRelicTable);
		
		mObjLang.put(txtSearchTarget, "search-target");
		mObjLang.put(mSearchButton, "search");
		mObjLang.put(mClearButton, "clear");
		mObjLang.put(mInstantUpdate,"update-instant");
		mObjLang.put(mDisplayForma, "formabp-display");
		mObjLang.put(mDisplayVaulted, "vaulted-display");
	}

	@Override
	public void refresh() {
		for (JComponent c: mObjLang.keySet()) {
			if (c instanceof JLabel)
				((JLabel)c).setText(Lang.t(mObjLang.get(c)));
			else if (c instanceof JButton)
				((JButton)c).setText(Lang.t(mObjLang.get(c)));
			else if (c instanceof JCheckBox)
				((JCheckBox)c).setText(Lang.t(mObjLang.get(c)));
		}
		mRelicViewerTableModel.refresh();
	}
	
	class RelicNameComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			String[] ss1 = s1.split(" ");
			String[] ss2 = s2.split(" ");
			
			if (ss1[0].equals(ss2[0])) 
				return ss1[1].compareTo(ss2[1]);
			
			return relicVal(ss1[0]).compareTo(relicVal(ss2[0]));
		}
		
		private Integer relicVal(String s) {
			if (s.equals(Lang.t("Lith"))) return 0;
			if (s.equals(Lang.t("Meso"))) return 1;
			if (s.equals(Lang.t("Neo"))) return 2;
			if (s.equals(Lang.t("Axi"))) return 3;
			return 5;
		}
	}
	
	class RarityComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			return relicVal(s1).compareTo(relicVal(s2));
		}
		
		private Integer relicVal(String s) {
			if (s.equals(Lang.t("common"))) return 0;
			if (s.equals(Lang.t("uncommon"))) return 1;
			if (s.equals(Lang.t("rare"))) return 2;
			return 3;
		}
	}
}