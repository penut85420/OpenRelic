package main.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.data.Lang;

public class FontSetter extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public FontSetter(JFrame frame) {
		super(frame);
		init();
		initGUI();
	}
	
	private void init() {
		setTitle(Lang.t("font-setting"));
		setSize(300, 75);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new FlowLayout());
	}
	
	private void initGUI() {
		JLabel text = new JLabel(Lang.t("font"));
		JTextField fontset = new JTextField(25);
		JButton chk = new JButton(Lang.t("check"));
		
		fontset.setText(MainFrame.mGlobalFont.getFontName() + ";" + MainFrame.mGlobalFont.getSize());
		
		class SetFont implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] tmp = fontset.getText().split(";");
				tmp[0] = tmp[0].trim();
				tmp[1] = tmp[1].trim();
				Font f = new Font(tmp[0], Font.PLAIN, Integer.parseInt(tmp[1]));
				MainFrame.mGlobalFont = f;
				MainFrame.mFrame.refreshAll();
				dispose();
			}
		}
		
		fontset.addActionListener(new SetFont());
		chk.addActionListener(new SetFont());
		
		add(text, BorderLayout.WEST);
		add(fontset, BorderLayout.CENTER);
		add(chk, BorderLayout.EAST);
	}
	
	
}
