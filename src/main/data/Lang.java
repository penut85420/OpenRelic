package main.Data;

import java.util.*;

import main.Library.LibraryIO;

public class Lang {
	final static String LangFolder = "data\\lang\\";
	final static String LangPath = LangFolder + "lang.txt";
	public static HashMap<String, HashMap<String, String>> mLang;
	public static String Lang = "zh-TW";
	
	public static void init() {
		mLang = new HashMap<>();
		String[] langType = LibraryIO.readFile(LangPath).split("\r\n");
		
		for (String lang: langType) {
			mLang.put(lang, new HashMap<>());
			String[] langContent = LibraryIO.readFile(LangFolder + lang + ".lang").split("\r\n");
			for (String seg: langContent) {
				if (seg.startsWith("#")) continue;
				if (seg.indexOf(": ") < 0) continue;
				
				Integer splitIdx = seg.indexOf(": ");
				String key = seg.substring(0, splitIdx).toLowerCase();
				String value = seg.substring(splitIdx + 2);
				mLang.get(lang).put(key, value);
			}
		}
	}
	
	public static String[] getLangList() {
		String[] langList = new String[mLang.size()];
		int i = 0;
		for (String s: mLang.keySet())
			langList[i++] = mLang.get(s).get("lang-name");
			
		return langList;
	}
	
	public static void setLang(String langName) {
		for (String s: mLang.keySet())
			if (mLang.get(s).get("lang-name").equals(langName))
				Lang = s;
	}
	
	public static String t(String key) {
		key = key.toLowerCase();
		if (mLang.get(Lang).get(key) == null)
			return key;
		return mLang.get(Lang).get(key);
	}
	
	public static String tt(String key, int space) {
		String s = "";
		for (int i = 0; i < space; i++) s += "&nbsp;";
		s += t(key);
		for (int i = 0; i < space; i++) s += "&nbsp";
		
		return s;
	}
}
