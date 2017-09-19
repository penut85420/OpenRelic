package main.Frame.RelicViewer;

import java.util.*;

import javax.swing.table.*;

import main.Data.*;
import main.Data.DataType.*;

public class RelicViewerTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	String[] mColsName = {Lang.t("relic-name"), Lang.t("item-name"), Lang.t("rarity")};
	String mFilter;
	
	Boolean isDisplayForma = false;
	Boolean isDisplayVaulted = false;
	
	ArrayList<String> mRelic = new ArrayList<>();
	ArrayList<String> mItemName = new ArrayList<>();
	ArrayList<String> mRarity = new ArrayList<>();
	
	public RelicViewerTableModel() {
		refresh();
	}
	
	public void refresh() {
		mColsName = new String[]{Lang.t("relic-name"), Lang.t("item-name"), Lang.t("rarity")};
		clear();
		for (String key: RelicData.mRelics.keySet()) {
			for (String kkey: RelicData.mRelics.get(key).keySet()) {
				VoidRelic vr = RelicData.mRelics.get(key).get(kkey);
				for (ItemPart item: vr.getDropItemList()) {
					boolean addFlag = true;
					
					if (mFilter != null)
						for (String ss: mFilter.split(" ")) {
							String search = ss.toLowerCase();
							if (vr.getFullName().toLowerCase().contains(search)) continue;
							if (item.getName().toLowerCase().contains(search)) continue;
							if (vr.getRarity(item).toLowerCase().contains(search)) continue;
							
							addFlag = false;
							break;
						}
					
					if (!isDisplayForma && item.getName().contains(Lang.t("forma")))	
						addFlag = false;
					
					if (!isDisplayVaulted && vr.isVaulted())
						addFlag = false;
					
					if (addFlag) {
						mRelic.add(vr.getFullName());
						mItemName.add(item.getName());
						mRarity.add(vr.getRarity(item));
					}
				}
			}
		}
		fireTableDataChanged();
	}
	
	public void setSearchTarget(String searchStr) {
		if (searchStr.trim().isEmpty())
			mFilter = null;
		else mFilter = searchStr;
		refresh();
	}
	
	public void setFormaDisplay(boolean b) {
		isDisplayForma = b;
		refresh();
	}
	
	public void setVaultedDisplay(boolean b) {
		isDisplayVaulted = b;
		refresh();
	}
	
	private void clear() {
		mRelic.clear();
		mItemName.clear();
		mRarity.clear();
	}
	
	@Override
	public int getColumnCount() { return mColsName.length; }
	
	@Override
	public String getColumnName(int col) { return mColsName[col]; }

	@Override
	public int getRowCount() { return mRelic.size(); }

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
