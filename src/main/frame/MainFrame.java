package main.frame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;

import main.data.Lang;
import main.frame.RelicViewer.RelicViewer;
import main.res.Res;

public class MainFrame extends JFrame implements SuperFrame {
	private static final long serialVersionUID = 1L;
	
	public static MainFrame mFrame;
	public static Font mGlobalFont;
	
	ArrayList<SuperFrame> mCtrlCenter = new ArrayList<>();
	
	JTabbedPane mTabbedPane;
	RelicViewer mRelicViewer;
	
	public MainFrame() {
		init();
		initUIManager();
		initMenu();
		initGUI();
		initCtrl();
	}

	private void initCtrl() {
		mCtrlCenter.add(this);
		mCtrlCenter.add(mRelicViewer);
	}

	private void init() {
		mFrame = this;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(t("window-title"));
		setSize(1280, 720);
		setIconImage(Res.getIcon());
		setLocationRelativeTo(null);
	}
	
	private void initUIManager() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
			mGlobalFont = new Font("細明體", Font.PLAIN, 12);
			Enumeration<Object> keys = UIManager.getDefaults().keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get (key);
				if (value != null && value instanceof FontUIResource)
					UIManager.put(key, mGlobalFont);
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	private void initGUI() {
		mTabbedPane = new JTabbedPane();
		
		mRelicViewer = new RelicViewer();
		mTabbedPane.addTab(t("relic-viewer"), mRelicViewer);
		
		add(mTabbedPane);
	}
	
	private void initMenu() {
		JMenuBar bar = new JMenuBar();
		
		JMenu menuSetting = new JMenu(t("setting"));
		JMenuItem itemSetting = new JMenuItem(t("font-setting"));
		itemSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FontSetter(mFrame).setVisible(true);
			}
		});
		menuSetting.add(itemSetting);
		
		JMenu menuLang = new JMenu(t("language"));
		for (String s: Lang.getLangList()) {
			JMenuItem item = new JMenuItem(s);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Lang.setLang(s);
					refreshAll();
				}
			});
			menuLang.add(item);
		}
		
		bar.add(menuSetting);
		bar.add(menuLang);
		setJMenuBar(bar);
	}
	
	public void refreshAll() {
		for (SuperFrame p: mCtrlCenter)
			p.refresh();
	}

	private String t(String key) { return Lang.t(key); }

	public void run() { setVisible(true); }

	@Override
	public void refresh() {
		setTitle(t("window-title"));
		mTabbedPane.setTitleAt(0, t("relic-viewer"));
		initMenu();
	}
}
