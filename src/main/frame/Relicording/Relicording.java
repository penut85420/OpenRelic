package main.Frame.Relicording;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.Custom.Listener.MouseSmoothClickListener;
import main.Data.ColorManager;
import main.Data.Lang;
import main.Data.RelicData;
import main.Data.DataType.ItemSet;
import main.Dialog.ItemSetChooser;
import main.Frame.MainFrame;
import main.Frame.SuperFrame;
import main.Library.LibraryIO;
import main.Library.LibrarySugar;

public class Relicording extends JPanel implements SuperFrame {
	
	public static final String STATE_PATH = "user/relicording.dat";
	
	HashMap<ItemSet, ArrayList<Boolean>> mItemCheck;
	
	WishListModel mWishListModel;
	RelicordingTableModel mRelicordingTableModel;
	
	JList<String> mWishList;
	JTable mRelicording;
	JButton mChoose;
	JButton mDelete;
	JCheckBox mShowVaulted;
	JLabel t1;
	JLabel t2;
	
	public Relicording() {
		init();
		initGUI();
		initEvent();
		initObjectArr();
		loadState();
	}
	
	public ArrayList<Boolean> getItemCheck(ItemSet i) {
		return mItemCheck.get(i);
	}

	private void init() {
		mItemCheck = new HashMap<>();
		setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		mWishListModel = new WishListModel();
		mRelicordingTableModel = new RelicordingTableModel(this);
		mWishList = new JList<>(mWishListModel);
		mRelicording = new JTable(mRelicordingTableModel);
		mChoose = new JButton(Lang.t("choose-itemset"));
		mDelete = new JButton(Lang.t("delete"));
		mShowVaulted = new JCheckBox(Lang.t("show-vaulted"));
		t1 = new JLabel(Lang.t("target-itemset"));
		t2 = new JLabel("<html>"
			+ ColorManager.getForeBackgroundTaggedText(Lang.tt("Common", 1), ColorManager.CommonFG, ColorManager.CommonBG) + "  "
			+ ColorManager.getForeBackgroundTaggedText(Lang.tt("Uncommon", 1), ColorManager.UncommonFG, ColorManager.UncommonBG) + "  "
			+ ColorManager.getForeBackgroundTaggedText(Lang.tt("Rare", 1), ColorManager.RareFG, ColorManager.RareBG) + "  "
			+ "</html>");
		JScrollPane spItemList = new JScrollPane(mWishList);
		JScrollPane spRelicording = new JScrollPane(mRelicording);
		
		t1.setBorder(new EmptyBorder(5, 5, 0, 0));
		t2.setBorder(new EmptyBorder(5, 5, 0, 0));
		mShowVaulted.setBorder(new EmptyBorder(2, 0, 0, 10));
		
		setTableHeaderWidth();
		LibrarySugar.setTableHeaderAlignment(mRelicording, JLabel.CENTER);
		mRelicording.getTableHeader().setReorderingAllowed(false);
		mRelicording.getColumnModel().getColumn(0).setCellRenderer(new RelicordingTableRenderer());
		
		JPanel pLeft = new JPanel(new BorderLayout());
			JPanel pLeftBottom = new JPanel(new GridLayout(0, 2));
			pLeftBottom.add(mChoose);
			pLeftBottom.add(mDelete);
			pLeftBottom.setBorder(new EmptyBorder(0, 0, 3, 0));
		pLeft.add(t1, BorderLayout.NORTH);
		pLeft.add(spItemList, BorderLayout.CENTER);
		pLeft.add(pLeftBottom, BorderLayout.SOUTH);
		
		JPanel pRight = new JPanel(new BorderLayout());
			JPanel pRightTop = new JPanel(new BorderLayout());
			pRightTop.add(t2, BorderLayout.WEST);
			pRightTop.add(mShowVaulted, BorderLayout.EAST);
		pRight.add(pRightTop, BorderLayout.NORTH);
		pRight.add(spRelicording, BorderLayout.CENTER);
		
		add(pLeft, BorderLayout.WEST);
		add(pRight, BorderLayout.CENTER);
	}
	
	private void setTableHeaderWidth() {
		mRelicording.getColumnModel().getColumn(0).setMaxWidth(MainFrame.mGlobalFont.getSize() * 5);
		mRelicording.getColumnModel().getColumn(0).setMinWidth(MainFrame.mGlobalFont.getSize() * 5);
	}

	private void initEvent() {
		mChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemSetChooser isc = new ItemSetChooser();
				isc.showDialog();
				
				if (isc.getOption() == ItemSetChooser.OPTION_CHECK) {
					ItemSet[] itemSets = isc.getSelectedItemSets();
					for (ItemSet i: itemSets) {
						mWishListModel.addItem(i);
						if (mItemCheck.get(i) == null) {
							ArrayList<Boolean> b = new ArrayList<>();
							for (int j = 0; j < i.getItemCount(); j++)
								b.add(false);
							mItemCheck.put(i, b);
						}
					}
					mWishListModel.refreshList();
				}
			}
		});
		
		mDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mWishListModel.removeItem(mWishList.getSelectedIndices());
				mWishListModel.refreshList();
				setDisplayItem();
			}
		});
		
		mWishList.addMouseListener(new MouseSmoothClickListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) { setDisplayItem(); }
		});
		
		mShowVaulted.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mRelicordingTableModel.setVaultedDisplay(mShowVaulted.isSelected());
			}
		});
	}
	
	private void setDisplayItem() {
		int select = mWishList.getSelectedIndex();

		if (select < 0 || mWishListModel.getSize() < 1) {
			mRelicordingTableModel.clearItem();
			return ;
		}
		
		ItemSet i = mWishListModel.getItem(select);
		mRelicordingTableModel.setItem(i);
	}
	
	private void initObjectArr() {
		addObj(t1, "target-itemset");
		addObj(mChoose, "choose-itemset");
		addObj(mDelete, "delete");
		addObj(mShowVaulted, "show-vaulted");
		
		addObj(mWishList);
		addObj(mRelicording);
		addObj(mRelicording.getTableHeader());
	}

	@Override
	public void refresh() {
		refreshDefault();
		for (int i = 0; i < mRelicordingTableModel.getColumnCount(); i++)
			mRelicording.getColumnModel().getColumn(i)
				.setHeaderValue(mRelicordingTableModel.getColumnName(i));
		setTableHeaderWidth();
		t2.setText("<html>"
				+ ColorManager.getForeBackgroundTaggedText(Lang.tt("Common", 1), ColorManager.CommonFG, ColorManager.CommonBG) + "  "
				+ ColorManager.getForeBackgroundTaggedText(Lang.tt("Uncommon", 1), ColorManager.UncommonFG, ColorManager.UncommonBG) + "  "
				+ ColorManager.getForeBackgroundTaggedText(Lang.tt("Rare", 1), ColorManager.RareFG, ColorManager.RareBG) + "  "
				+ "</html>");
	}
	
	public void writeState() {
		String content = "";
		for (ItemSet i: mItemCheck.keySet()) {
			content += i.getKeyName() + "\t";
			
			for (Boolean b: mItemCheck.get(i))
				content += b.toString() + "\t";
			content += "\n";
		}
		LibraryIO.writeFile(STATE_PATH, content);
		mWishListModel.writeState();
	}
	
	public void loadState() {
		String raw = LibraryIO.readFile(STATE_PATH);
		if (raw == null || raw.isEmpty()) return ;
		
		String[] content = raw.split("\n");
		
		for (String line: content) {
			String[] seg = line.split("\t");
			ArrayList<Boolean> b = new ArrayList<>();
			for (int i = 1; i < seg.length; i++)
				b.add(Boolean.valueOf(seg[i]));
			mItemCheck.put(RelicData.mItemSet.get(seg[0]), b);
		}
		
		mWishListModel.loadState();
	}

	@Override
	public void onClose() {
		writeState();
	}
}
