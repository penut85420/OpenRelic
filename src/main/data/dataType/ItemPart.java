package main.data.dataType;

public class ItemPart {	
	String mName;
	int mDucats;
	
	public ItemPart(String name) {
		mName = name;
	}
	
	public String toString() {
		return "Item Part Name: " + mName;
	}
}
