package main.Data.Processing;

import java.util.*;

import main.Library.LibraryIO;

public class VaultedDataProcessing {
	
	static final String FILE_PATH = "wiki/vaulted_relic.txt";
	static final String DATA_PATH = "data/VaultedRelics.txt";
	HashMap<String, HashMap<String, Boolean>> mNewRelics;
	HashMap<String, HashMap<String, Boolean>> mOldRelics;
	
	public static void main(String[] args) {
		new VaultedDataProcessing().handleVaultedRelic();
	}
	
	private void handleVaultedRelic() {
		// Initialize
		mNewRelics = new HashMap<>();
		mOldRelics = new HashMap<>();
		String nowEra = "";
		
		// New Data Handling
		String[] new_content = LibraryIO.readFile(FILE_PATH).replaceAll("\r", "").split("\n");
		
		for (String line: new_content) {
			String[] seg = line.split(" ");
			if (!seg[0].equals(nowEra)) {
				log("\n" + seg[0]);
				nowEra = seg[0];
				mNewRelics.put(nowEra, new HashMap<>());
			}
			log(seg[1]);
			mNewRelics.get(nowEra).put(seg[1], true);
		}
		
		// Old Data Loading
		String[] old_content = LibraryIO.readFile(DATA_PATH).replaceAll("\r", "").split("\n");
		
		for (String line: old_content) {
			if (mNewRelics.get(line) != null) {
				nowEra = line;
				mOldRelics.put(nowEra, new HashMap<>());
			} else 
				mOldRelics.get(nowEra).put(line, true);
		}
		
		// Compare
		for (String era: mNewRelics.keySet()) {
			for (String code: mNewRelics.get(era).keySet())
				if (mOldRelics.get(era).get(code) == null)
					log(era + " " + code);
		}
	}
	
	private static void log(Object obj) {
		System.out.println(obj.toString());
	}
}
