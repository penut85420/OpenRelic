package main.Data.DataType;

import java.util.ArrayList;

import main.Data.Lang;

public class ItemPart {	
	String mName;
	int mDucats;
	ArrayList<VoidRelic> mSourceRelic;
	
	public ItemPart(String name) {
		mName = name;
		mSourceRelic = new ArrayList<>();
	}
	
	public void addRelic(VoidRelic vr) {
		mSourceRelic.add(vr);
	}
	
	public String getSourceRelic(boolean vaultedRequire) {
		String vaulted = "";
		String unvaulted = "";
		
		for (VoidRelic vr: mSourceRelic) {
			if (vr.isVaulted())
				vaulted += vr.getFullName() + " ";
			else unvaulted += vr.getFullName() + " ";
		}
		if (vaultedRequire) return unvaulted + vaulted;
		return unvaulted;
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
		return getName();
	}
	
	public String getInfo() {
		return "Item Part Name: " + mName;
	}
}
