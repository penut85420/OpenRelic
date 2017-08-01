package main.data.dataType;

import java.util.ArrayList;

import main.data.Lang;

public class ItemSet {
	String mItemName;
	String mKeyName;
	ArrayList<ItemPart> mItemPartList = new ArrayList<>();
	
	public ItemSet(String name) {
		mItemName = name;
	}
	
	public void addItem(ItemPart item) {
		mItemPartList.add(item);
	}
	
	public ItemPart getItemPart(int i) {
		return mItemPartList.get(i);
	}
	
	public void setKeyName(String s) {
		mKeyName = s;
	}
	
	public String getKeyName() {
		return mKeyName;
	}
	
	public int getItemCount() {
		return mItemPartList.size();
	}
	
	public String toString() {
		String[] tmp = mItemName.split(" ");
		String val = "";
		for (String s: tmp) {
			val += Lang.t(s) + " ";
		}
		return val.trim();
	}

	public String getInfo() {
		String str = String.format("Item Set Name: %s, Total: %d\n", mItemName, mItemPartList.size());
		for (ItemPart i: mItemPartList)
			str += i.toString() + "\n";
		return str;
	}
}
