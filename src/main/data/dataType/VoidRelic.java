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
	
	public String toString() {
		String str = String.format("Era: %s, Code: %s\n", mEra, mCode);
		for (ItemPart item: mDropItemList)
			str += item.toString() + "\n";
		return str;
	}
}
