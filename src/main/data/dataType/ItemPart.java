package main.data.dataType;

public class ItemPart {	
	String mName;
	int mDucats;
	
	public ItemPart(String name) {
		mName = name;
	}
	
	public String getName() { return mName; }
	
	public boolean equals(ItemPart item) {
		return mName.equals(item.mName);
	}
	
	public String toString() {
		return "Item Part Name: " + mName;
	}
}
