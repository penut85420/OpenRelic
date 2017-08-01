package main.Data.DataType;

import java.util.ArrayList;

import main.Data.Lang;

public class VoidRelic {
	String mEra;
	String mCode;
	Boolean isVaulted;
	ArrayList<ItemPart> mDropItemList = new ArrayList<>();
	
	public VoidRelic(String str) {
		String[] s = str.split("\t");
		mEra = s[0];
		mCode = s[1];
	}
	
	public void addItem(ItemPart item) {
		mDropItemList.add(item);
	}
	
	public void setVaulted(Boolean b) { isVaulted = b; }
	
	public String getRawEra() { return mEra; }
	public String getEra() { return t(mEra); }
	public String getCode() { return mCode; }
	public String getFullName() { return getEra() + " " + getCode(); }
	public Boolean isVaulted() { return isVaulted; }
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
