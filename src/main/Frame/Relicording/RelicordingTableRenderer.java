package main.Frame.Relicording;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

public class RelicordingTableRenderer implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent (JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		
		JCheckBox c = new JCheckBox();
		c.setSelected((boolean)value);
		c.setOpaque(true);
		c.setHorizontalAlignment(JCheckBox.CENTER);
		
		if (hasFocus || isSelected) c.setBackground(table.getSelectionBackground());
		else {
			if (row % 2 == 1)
				c.setBackground(table.getBackground());
			else c.setBackground(Color.WHITE);
		}
		
		return c;
	}

}
