package main.Frame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;

import main.Data.Lang;
import main.Dialog.AboutDev;
import main.Frame.RelicViewer.RelicViewer;
import main.Frame.Relicording.Relicording;
import main.Res.Res;

public class MainFrame extends JFrame implements SuperFrame {
	private static final long serialVersionUID = 1L;
	
	public static MainFrame mFrame;
	public static Font mGlobalFont;
	
	ArrayList<SuperFrame> mCtrlCenter = new ArrayList<>();
	
	JTabbedPane mTabbedPane;
	RelicViewer mRelicViewer;
	Relicording mRelicording;
	
	public MainFrame() {
		init();
		initUIManager();
		initMenu();
		initGUI();
		initEvent();
		initCtrl();
	}

	private void initCtrl() {
		mCtrlCenter.add(this);
		mCtrlCenter.add(mRelicViewer);
		mCtrlCenter.add(mRelicording);
	}

	private void init() {
		mFrame = this;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(t("window-title"));
		setSize(900, 600);
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
		
		mRelicording = new Relicording();
		mTabbedPane.addTab(t("relicording"), mRelicording);
		
		add(mTabbedPane);
	}
	
	private void initEvent() {
		mFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				onClose();
			}
			
			public void windowActivated(WindowEvent e) { }
			public void windowClosed(WindowEvent e) { }
			public void windowDeactivated(WindowEvent e) { }
			public void windowDeiconified(WindowEvent e) { }
			public void windowIconified(WindowEvent e) { }
			public void windowOpened(WindowEvent e) { }
		});
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
		menuLang.add(new JMenuBar());
		JMenuItem itemLangEditor = new JMenuItem(t("lang-editor"));
		menuLang.add(itemLangEditor);
		
		JMenu menuAbout = new JMenu(t("about"));
		JMenuItem itemAboutDev = new JMenuItem(t("about-dev")); 
		itemAboutDev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDev(mFrame).setVisible(true);
			}
		});
		menuAbout.add(itemAboutDev);
		
		bar.add(menuSetting);
		bar.add(menuLang);
		bar.add(menuAbout);
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
		mTabbedPane.setTitleAt(1, t("relicording"));
		initMenu();
	}

	@Override
	public void onClose() {
		mRelicViewer.onClose();
		mRelicording.onClose();
	}
}
