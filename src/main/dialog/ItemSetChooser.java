package main.dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import main.data.*;
import main.data.dataType.*;
import main.frame.MainFrame;

public class ItemSetChooser extends JDialog {
	
	public static final int OPTION_CANCEL = 0;
	public static final int OPTION_CHECK = 1;
	
	int mOption = 0;
	
	JTextField mSearchText;
	ItemSetListModel mItemSetListModel;
	JList<String> mItemSetList;
	JButton mCheck;
	JButton mCancel;
	
	public ItemSetChooser() {
		super(MainFrame.mFrame);
		init();
		initGUI();
		initEvent();
	}

	private void init() {
		setTitle(Lang.t("itemset-chooser"));
		setSize(250, 400);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		JLabel t1 = new JLabel(Lang.t("search"));
		mSearchText = new JTextField();
		mItemSetListModel = new ItemSetListModel();
		mItemSetList = new JList<String>(mItemSetListModel);
		JScrollPane spItemSetList = new JScrollPane(mItemSetList);
		mCheck = new JButton(Lang.t("check"));
		mCancel = new JButton(Lang.t("cancel"));
		
		JPanel pTop = new JPanel(new BorderLayout());
		pTop.add(t1, BorderLayout.WEST);
		pTop.add(mSearchText, BorderLayout.CENTER);
		
		JPanel pBottom = new JPanel(new GridLayout(0, 2));
		pBottom.add(mCheck);
		pBottom.add(mCancel);
		
		add(pTop, BorderLayout.NORTH);
		add(spItemSetList, BorderLayout.CENTER);
		add(pBottom, BorderLayout.SOUTH);
	}
	
	private void initEvent() {
		mCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mOption = OPTION_CHECK;
				setVisible(false);
			}
		});
		
		mCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mOption = OPTION_CANCEL;
				setVisible(false);
			}
		});
	}
	
	public int getOption() {
		return mOption;
	}
	
	public ItemSet[] getSelectedItemSets() {
		return mItemSetListModel.getSelectedItemSets();
	}
	
	class ItemSetListModel extends AbstractListModel<String> {
		HashMap<String, String> mItemSetName;
		ArrayList<String> mDisplayName;
		
		public ItemSetListModel() {
			mItemSetName = new HashMap<>();
			for (String key: RelicData.mItemSet.keySet()) {
				ItemSet i = RelicData.mItemSet.get(key);
				mItemSetName.put(i.toString(), key);
			}
			
			mDisplayName = new ArrayList<>(mItemSetName.keySet());
			Collections.sort(mDisplayName);
		}
		
		@Override
		public String getElementAt(int i) {
			return mDisplayName.get(i);
		}

		@Override
		public int getSize() {
			return RelicData.mItemSet.size();
		}
		
		public ItemSet[] getSelectedItemSets() {
			int[] selects = mItemSetList.getSelectedIndices();
			ItemSet[] itemsets = new ItemSet[selects.length];
			
			for (int i = 0; i < selects.length; i++)
				itemsets[i] = RelicData.mItemSet.get(mItemSetName.get(mDisplayName.get(i)));
			
			return itemsets;
		}
	}
}
