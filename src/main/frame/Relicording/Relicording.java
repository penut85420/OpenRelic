package main.frame.Relicording;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.data.Lang;
import main.data.dataType.ItemSet;
import main.dialog.ItemSetChooser;
import main.frame.SuperFrame;

public class Relicording extends JPanel implements SuperFrame {
	
	RelicordingTableModel mRelicordingTableModel;
	
	JList<String> mItemList;
	JTable mRelicording;
	JButton mChoose;
	JButton mDelete;
	JLabel t1;
	
	public Relicording() {
		init();
		initGUI();
		initEvent();
		initObjectArr();
	}

	private void init() {
		setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		mRelicordingTableModel = new RelicordingTableModel();
		mItemList = new JList<>();
		mRelicording = new JTable(mRelicordingTableModel);
		mChoose = new JButton(Lang.t("choose-itemset"));
		mDelete = new JButton(Lang.t("delete"));
		
		t1 = new JLabel(Lang.t("target-itemset"));
		JScrollPane spItemList = new JScrollPane(mItemList);
		JScrollPane spRelicording = new JScrollPane(mRelicording);
		
		t1.setBorder(new EmptyBorder(5, 5, 0, 0));
		
		JPanel pLeft = new JPanel(new BorderLayout());
			JPanel pLeftBottom = new JPanel(new GridLayout(0, 2));
			pLeftBottom.add(mChoose);
			pLeftBottom.add(mDelete);
			pLeftBottom.setBorder(new EmptyBorder(0, 0, 3, 0));
		pLeft.add(t1, BorderLayout.NORTH);
		pLeft.add(spItemList, BorderLayout.CENTER);
		pLeft.add(pLeftBottom, BorderLayout.SOUTH);
		
		add(pLeft, BorderLayout.WEST);
		add(spRelicording, BorderLayout.CENTER);
	}
	
	private void initEvent() {
		mChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemSetChooser isc = new ItemSetChooser();
				isc.setVisible(true);
				
				if (isc.getOption() == ItemSetChooser.OPTION_CHECK) {
					ItemSet[] itemSets = isc.getSelectedItemSets();
					for (ItemSet is: itemSets)
						System.out.println(is);
				}
			}
		});
	}
	
	private void initObjectArr() {
		addObj(t1, "target-itemset");
		addObj(mChoose, "choose-itemset");
		addObj(mDelete, "delete");
		
		addObj(mItemList);
		addObj(mRelicording);
	}

	@Override
	public void refresh() {
		refreshDefault();
	}

}
