package main.Dialog.LangEditor;

import java.awt.*;

import javax.swing.*;

import main.Data.Lang;
import main.Frame.MainFrame;

public class LangEditor extends JDialog {
	
	JTable mLangEdit;
	LangEditorTableModel mLangEditTableModel;
	JList<String> mLangList;
	JButton mAdd;
	JButton mDelete;
	
	public LangEditor() {
		super(MainFrame.mFrame);
		init();
		initGUI();
	}
	
	private void init() {
		setModal(true);
		setSize(700, 350);
		setLocationRelativeTo(null);
		setTitle(Lang.t("lang-editor"));
		getContentPane().setLayout(new BorderLayout());
	}
	
	private void initGUI() {
		mLangEditTableModel = new LangEditorTableModel();
		mLangEdit = new JTable(mLangEditTableModel);
		mLangList = new JList<>();
		JScrollPane spLangEdit = new JScrollPane(mLangEdit);
		JScrollPane spLangList = new JScrollPane(mLangList);
		mAdd = new JButton(Lang.t("add-lang"));
		mDelete = new JButton(Lang.t("delete-lang"));
		
		JPanel pRight = new JPanel(new BorderLayout());
			JPanel pRightBottom = new JPanel(new GridLayout(0, 2));
			pRightBottom.add(mAdd);
			pRightBottom.add(mDelete);
		pRight.add(spLangList, BorderLayout.CENTER);
		pRight.add(pRightBottom, BorderLayout.SOUTH);
		
		add(spLangEdit, BorderLayout.CENTER);
		add(pRight, BorderLayout.EAST);
	}
}
