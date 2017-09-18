package main.Frame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;

import main.Custom.Listener.WindowClosingListener;
import main.Data.Lang;
import main.Dialog.AboutDev;
import main.Dialog.FontChooser.FontChooser;
import main.Dialog.LangEditor.LangEditor;
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
		initObjectArr();
	}
	
	private void initObjectArr() {
		addObj(mTabbedPane);
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
		mFrame.addWindowListener(new WindowClosingListener() {
			@Override
			public void windowClosing(WindowEvent e) { onClose(); }
		});
	}
	
	private void initMenu() {
		JMenuBar bar = new JMenuBar();
		
		JMenu menuSetting = new JMenu(t("setting"));
		JMenuItem itemSetting = new JMenuItem(t("font-setting"));
		itemSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FontChooser fc = new FontChooser(mFrame);
				fc.setFont(mGlobalFont);
				fc.showDialog();
				if (fc.getUserFont() != null)
					mGlobalFont = fc.getUserFont();
				refreshAll();
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
			addObj(item);
			menuLang.add(item);
		}

		JMenuItem itemLangEditor = new JMenuItem(t("lang-editor"));
		 
//		TODO: Temporarily disable in release version
//		menuLang.add(new JMenuBar());
//		menuLang.add(itemLangEditor);
		
		itemLangEditor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LangEditor().setVisible(true);
			}
		});
		
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
		
		addObj(menuSetting);
		addObj(itemSetting);
		addObj(menuLang);
		addObj(itemLangEditor);
		addObj(menuAbout);
		addObj(itemAboutDev);
	}
	
	public void refreshAll() {
		for (SuperFrame p: mCtrlCenter)
			p.refresh();
	}

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
