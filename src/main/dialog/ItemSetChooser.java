package main.Dialog;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import main.Data.*;
import main.Data.DataType.*;
import main.Frame.MainFrame;

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
		mItemSetList.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					dialogCheck();
				}
			}
			
			public void mouseReleased(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
		});
		
		mCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogCheck();
			}
		});
		
		mCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogCancel();
			}
		});
		
		mSearchText.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				mItemSetListModel.setSearchTarget(mSearchText.getText());
			}
		});
	}
	
	private void dialogCheck() {
		mOption = OPTION_CHECK;
		hideDialog();
	}
	
	private void dialogCancel() {
		mOption = OPTION_CANCEL;
		hideDialog();
	}
	
	public void showDialog() { setVisible(true); }
	
	public void hideDialog() { setVisible(false); }
	
	public int getOption() {
		return mOption;
	}
	
	public ItemSet[] getSelectedItemSets() {
		return mItemSetListModel.getSelectedItemSets();
	}
	
	class ItemSetListModel extends AbstractListModel<String> {
		HashMap<String, String> mItemSetName;
		ArrayList<String> mDisplayName;
		ArrayList<String> mOrgList;
		
		public ItemSetListModel() {
			mItemSetName = new HashMap<>();
			for (String key: RelicData.mItemSet.keySet()) {
				ItemSet i = RelicData.mItemSet.get(key);
				mItemSetName.put(i.toString(), key);
			}
			
			mOrgList = new ArrayList<>(mItemSetName.keySet());
			Collections.sort(mOrgList);
			mDisplayName = mOrgList;
		}
		
		@Override
		public String getElementAt(int i) {
			return mDisplayName.get(i);
		}

		@Override
		public int getSize() {
			return mDisplayName.size();
		}
		
		public ItemSet[] getSelectedItemSets() {
			int[] selects = mItemSetList.getSelectedIndices();
			ItemSet[] itemsets = new ItemSet[selects.length];
			
			for (int i = 0; i < selects.length; i++)
				itemsets[i] = RelicData.mItemSet.get(mItemSetName.get(mDisplayName.get(selects[i])));
			
			return itemsets;
		}
		
		public void setSearchTarget(String search) {
			if (search.trim().isEmpty())
				mDisplayName = mOrgList;
			else {
				mDisplayName = new ArrayList<>();
				for (String s: mOrgList)
					if (s.contains(search))
						mDisplayName.add(s);
			}
			repaint();
		}
	}
}
