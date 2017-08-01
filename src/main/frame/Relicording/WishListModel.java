package main.Frame.Relicording;

import java.util.*;

import javax.swing.*;

import main.Data.RelicData;
import main.Data.DataType.ItemSet;
import main.Library.LibraryIO;

public class WishListModel extends AbstractListModel<String> {
	public static final String WISHLIST_PATH = "user/wlist.dat";
	
	ArrayList<ItemSet> mWishList;
	
	public WishListModel() { 
		mWishList = new ArrayList<>(); 
	}
	
	public void writeState() {
		String content = "";
		for (ItemSet i: mWishList)
			content += i.getKeyName() + "\n";
		LibraryIO.writeFile(WISHLIST_PATH, content);
	}
	
	public void loadState() {
		String raw = LibraryIO.readFile(WISHLIST_PATH);
		if (raw == null) return ;
		
		String[] content = raw.split("\n");
		for (String s: content)
			mWishList.add(RelicData.mItemSet.get(s));
	}
	
	public void addItem(ItemSet i) { mWishList.add(i); }
	
	public ItemSet getItem(int i) {
		if (i >= mWishList.size()) 
			return null;
		return mWishList.get(i); 
	}
	
	public void removeItem(int[] selected) {
		for (int i = selected.length - 1; i >= 0; i--)
			mWishList.remove(selected[i]);
	}
	
	public void refreshList() {
		fireContentsChanged(this, 0, getSize());
	}

	@Override
	public String getElementAt(int i) { return mWishList.get(i).toString(); }

	@Override
	public int getSize() { return mWishList.size(); }

}
