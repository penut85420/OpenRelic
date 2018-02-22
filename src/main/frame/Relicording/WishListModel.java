package main.Frame.Relicording;

import java.util.*;

import javax.swing.*;

import main.Data.RelicData;
import main.Data.DataType.ItemSet;
import main.StateRecorder.StateManager;

public class WishListModel extends AbstractListModel<String> {	
	ArrayList<ItemSet> mWishList;
	
	public WishListModel() { 
		mWishList = new ArrayList<>(); 
	}
	
	public void writeState() {
		String content = "";
		for (ItemSet i: mWishList)
			content += i.getKeyName() + "\n";
		StateManager.writeState(content, StateManager.RelicordingWishList);
	}
	
	public void loadState() {
		String[] content = StateManager.loadState(StateManager.RelicordingWishList);
		
		for (String s: content) {
			ItemSet i = RelicData.mItemSet.get(s);
			if (i != null)
				mWishList.add(i);
		}
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
