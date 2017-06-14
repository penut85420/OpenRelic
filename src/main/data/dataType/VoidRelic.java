package main.data.dataType;

import java.util.ArrayList;

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
	
	public String getEra() { return mEra; }
	public String getCode() { return mCode; }
	public String getFullName() { return mEra + " " + mCode; }
	public ArrayList<ItemPart> getDropItemList() { return mDropItemList; }
	
	public String getRarity(ItemPart item) {
		for (int i = 0; i < mDropItemList.size(); i++) {
			if (mDropItemList.get(i).equals(item))
				if (i < 3) return "銅";
				else if (i < 5) return "銀";
				else return "金";
		}
		return null;
	}
	
	public String toString() {
		String str = String.format("Era: %s, Code: %s\n", mEra, mCode);
		for (ItemPart item: mDropItemList)
			str += item.toString() + "\n";
		return str;
	}
}
