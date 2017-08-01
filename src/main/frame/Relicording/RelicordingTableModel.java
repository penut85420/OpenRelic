package main.frame.Relicording;

import javax.swing.table.*;

import main.data.Lang;
import main.data.dataType.ItemSet;

public class RelicordingTableModel extends AbstractTableModel {
	
	Relicording mRelicording;
	ItemSet mItemSet;
	boolean isVaultedDisplay;
	
	public RelicordingTableModel(Relicording r) {
		mRelicording = r;
	}
	
	public void setItem(ItemSet i) {
		mItemSet = i;
		fireTableDataChanged();
	}
	
	public void clearItem() {
		mItemSet = null;
		fireTableDataChanged();
	}
	
	public void setVaultedDisplay(boolean b) {
		isVaultedDisplay = b;
		fireTableDataChanged();
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 0) return true;
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		String[] colName = {Lang.t("already-get"), Lang.t("itempart-name"), Lang.t("source-relic")};
		return colName[col];
	}
	
	@Override
	public Class<?> getColumnClass(int col) {
		Class<?>[] c = {Boolean.class, String.class, String.class};
		return c[col];
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if (mItemSet == null) return 0;
		return mItemSet.getItemCount();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
			case 0: return mRelicording.getItemCheck(mItemSet).get(row);
			case 1: return mItemSet.getItemPart(row);
			case 2: return mItemSet.getItemPart(row).getSourceRelic(isVaultedDisplay);
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		mRelicording.getItemCheck(mItemSet).set(row, (Boolean) value);
	}
}
