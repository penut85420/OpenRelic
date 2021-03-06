package main.Data;

import java.util.*;

import main.Data.DataType.*;
import main.Library.*;

public class RelicData {
	final static String Spliter = "\r\n\r\n";
	final static String VaultedRelicPath = "data\\VaultedRelics.txt";
	final static String AllRelicPath = "data\\VoidRelic.txt";
	final static String ItemSetPath = "data\\ItemSet.txt";
	public static HashMap<String, HashMap<String, Boolean>> mVaultedRelics;
	public static HashMap<String, HashMap<String, VoidRelic>> mRelics;
	public static HashMap<String, ItemPart> mItemPart;
	public static HashMap<String, ItemSet> mItemSet;
	
	public static void init() {
		mVaultedRelics = new HashMap<>();
		mRelics = new HashMap<>();
		mItemPart = new HashMap<>();
		mItemSet = new HashMap<>();
		loadVaultedRelic();
		loadAllRelic();
		loadItemSet();
		
//		Test Output
//		String[] arr = new String[mItemSet.keySet().size()];
//		mItemSet.keySet().toArray(arr);
//		Arrays.sort(arr);
//		for (String s: arr) {
//			log(s.replaceAll(" PRIME", "") + ": " + s.replaceAll(" PRIME", ""));
//		}
	}

	private static void loadVaultedRelic() {
		String[] content = LibraryIO.readFile(VaultedRelicPath).split(Spliter);
		
		for (String s: content) {
			String[] code = s.split("\r\n");
			HashMap<String, Boolean> tmpHash = new HashMap<>();
			
			for (int i = 1; i < code.length; i++)
				tmpHash.put(code[i], true);
			
			mVaultedRelics.put(code[0], tmpHash);
		}
	}
	
	private static void loadAllRelic() {
		String[] content = LibraryIO.readFile(AllRelicPath).split(Spliter);
		
		for (String s: content) {
			String[] line = s.split("\r\n");

			VoidRelic vr = new VoidRelic(line[0]);
			
			for (int i = 1; i < line.length; i++) {
				if (mItemPart.get(line[i]) == null)
					mItemPart.put(line[i], new ItemPart(line[i]));
				ItemPart ip = mItemPart.get(line[i]);
				vr.addItem(ip);
				ip.addRelic(vr);
			}
			
			if (mVaultedRelics.get(vr.getRawEra()).get(vr.getCode()) == null) {
				mVaultedRelics.get(vr.getRawEra()).put(vr.getCode(), false);
				vr.setVaulted(false);
			} else vr.setVaulted(true);
			
			if (mRelics.get(vr.getRawEra()) == null)
				mRelics.put(vr.getRawEra(), new HashMap<>());
			mRelics.get(vr.getRawEra()).put(vr.getCode(), vr);
		}
	}
	
	private static void loadItemSet() {
		String[] item = new String[mItemPart.keySet().size()];
		mItemPart.keySet().toArray(item);
		Arrays.sort(item);
		
		String preSet = "";
		ItemSet tmpSet = null;
		for (String i: item) {
			if (i.startsWith("FORMA")) continue;

			String nowSet = i.substring(0, i.indexOf("PRIME") + 5);
			if (!preSet.equals(nowSet)) {
				tmpSet = new ItemSet(nowSet);
				mItemSet.put(nowSet, tmpSet);
				tmpSet.setKeyName(nowSet);
				preSet = nowSet;
			}
			
			if (mItemPart.get(i) == null)
				log("Not found: " + i);
			tmpSet.addItem(mItemPart.get(i));
		}
	}
	
	public static void log(Object obj) {
		System.out.println(obj.toString());
	}
}
