package main.data.dataType;

import main.data.Lang;

public class ItemPart {	
	String mName;
	int mDucats;
	
	public ItemPart(String name) {
		mName = name;
	}
	
	public String getName() {
		String[] part = mName.split(" ");
		String val = "";
		for (String s: part)
			val += Lang.t(s) + " "; 

		return val.trim(); 
	}
	
	public boolean equals(ItemPart item) {
		return mName.equals(item.mName);
	}
	
	public String toString() {
		return "Item Part Name: " + mName;
	}
}
