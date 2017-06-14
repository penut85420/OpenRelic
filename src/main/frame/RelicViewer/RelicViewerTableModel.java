package main.frame.RelicViewer;

import java.util.*;
import javax.swing.table.*;
import main.data.*;
import main.data.dataType.*;

public class RelicViewerTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	String[] mColsName = {"Relic", "ItemName", "Rarity"};
	ArrayList<String> mRelic = new ArrayList<>();
	ArrayList<String> mItemName = new ArrayList<>();
	ArrayList<String> mRarity = new ArrayList<>();
	
	public RelicViewerTableModel() {
		for (String key: RelicDataIO.mRelics.keySet()) {
			for (String kkey: RelicDataIO.mRelics.get(key).keySet()) {
				VoidRelic vr = RelicDataIO.mRelics.get(key).get(kkey);
				for (ItemPart item: vr.getDropItemList()) {
					mRelic.add(vr.getFullName());
					mItemName.add(item.getName());
					mRarity.add(vr.getRarity(item));
				}
			}
		}
	}
	
	@Override
	public int getColumnCount() { return mColsName.length; }
	
	@Override
	public String getColumnName(int col) { return mColsName[col]; }

	@Override
	public int getRowCount() { 
		return mRelic.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0: return mRelic.get(row);
		case 1: return mItemName.get(row);
		case 2: return mRarity.get(row);
		}
		return null;
	}

}
