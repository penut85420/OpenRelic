package main.Library;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LibrarySugar {
	public static void log(Object obj) {
		System.out.println(obj.toString());
	}
	
	public static void setTableHeaderAlignment(JTable table, int align) {
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(align);
		
	}
}
