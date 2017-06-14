package main.data.dataType;

import java.util.ArrayList;

import main.data.Lang;

public class VoidRelic {
	String mEra;
	String mCode;
	boolean isVaulted;
	ArrayList<ItemPart> mDropItemList = new ArrayList<>();
	
	public VoidRelic(String str) {
		String[] s = str.split("\t");
		mEra = s[0];
		mCode = s[1];
	}
	
	public void addItem(ItemPart item) {
		mDropItemList.add(item);
	}
	
	public String getEra() { return t(mEra); }
	public String getCode() { return mCode; }
	public String getFullName() { return getEra() + " " + getCode(); }
	public ArrayList<ItemPart> getDropItemList() { return mDropItemList; }
	
	public String getRarity(ItemPart item) {
		for (int i = 0; i < mDropItemList.size(); i++) {
			if (mDropItemList.get(i).equals(item))
				if (i < 3) return t("common");
				else if (i < 5) return t("uncommon");
				else return t("rare");
		}
		return null;
	}
	
	public String toString() {
		String str = String.format("Era: %s, Code: %s\n", mEra, mCode);
		for (ItemPart item: mDropItemList)
			str += item.toString() + "\n";
		return str;
	}
	
	private String t(String key) {
		return Lang.t(key);
	}
}
