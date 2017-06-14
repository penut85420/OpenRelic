package main.data.dataType;

import java.util.ArrayList;

public class ItemSet {
	String mItemName;
	ArrayList<ItemPart> mItemPartList = new ArrayList<>();
	
	public ItemSet(String name) {
		mItemName = name;
	}
	
	public void addItem(ItemPart item) {
		mItemPartList.add(item);
	}

	public String toString() {
		String str = String.format("Item Set Name: %s, Total: %d\n", mItemName, mItemPartList.size());
		for (ItemPart i: mItemPartList)
			str += i.toString() + "\n";
		return str;
	}
}
