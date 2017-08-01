package main.Dialog.LangEditor;

import java.util.*;

import javax.swing.table.*;

public class LangEditorTableModel extends AbstractTableModel {
	String[] mColName = {"Name", "English", "繁體中文"};
	String[] mLangList = {"en", "zh-TW"};
	
	HashMap<String, ArrayList<String>> mLangMap;
	
	public LangEditorTableModel() {
		mLangMap = new HashMap<>();
	}
	
	@Override
	public String getColumnName(int col) {
		return mColName[col];
	}
	
	@Override
	public int getColumnCount() {
		return mColName.length;
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return null;
	}

}
