package main.Data.Processing;

import main.Library.LibraryIO;

public class VaultedDataProcessing {
	
	static final String FILE_PATH = "wiki/vaulted_relic.txt";
	
	public static void main(String[] args) {
		handleVaultedRelic();
	}
	
	private static void handleVaultedRelic() {
		String[] content = LibraryIO.readFile(FILE_PATH).replaceAll("\r", "").split("\n");
		
		String nowEra = "";
		
		for (String line: content) {
			String[] seg = line.split(" ");
			if (!seg[0].equals(nowEra)) {
				log("\n" + seg[0]);
				nowEra = seg[0];
			}
			log(seg[1]);
		}
	}
	
	private static void log(Object obj) {
		System.out.println(obj.toString());
	}
}
