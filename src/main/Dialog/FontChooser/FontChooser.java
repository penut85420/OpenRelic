package main.Dialog.FontChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import main.Custom.Listener.MouseSmoothClickListener;
import main.Data.Lang;
import static main.Library.LibrarySugar.log;

public class FontChooser extends JDialog {
	
	JLabel t1;
	JLabel t2;
	JLabel t3;
	JLabel tExample;
	JTextField mSearchFont;
	JTextField mSearchStyle;
	JTextField mSearchSize;
	JList<String> mFontList;
	JList<FontStyle> mStyleList;
	JList<Integer> mSizeList;
	JButton mCheck;
	JButton mCancel;
	
	TitledBorder mExampleBorder;
	
	Font mUserFont;
	final static Font DEFAULT_FONT = new Font("細明體", Font.PLAIN, 12);
	
	public FontChooser(Window window) {
		super(window);
		init();
		initGUI();
		initEvent();
	}

	private void init() {
		setModal(true);
		setSize(650, 500);
		setTitle(t("font-setting"));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2, 0));
		mUserFont = DEFAULT_FONT;
	}
	
	private void initGUI() {
		t1 = new JLabel(t("font"));
		t2 = new JLabel(t("font-style"));
		t3 = new JLabel(t("font-size"));
		tExample = new JLabel(t("example-text"));
		mCheck = new JButton(t("check"));
		mCancel = new JButton(t("cancel"));
		mSearchFont = new JTextField();
		mSearchStyle = new JTextField();
		mSearchSize = new JTextField();
		
		mFontList = new JList<>(getFontList());
		mStyleList = new JList<>(getStyleList());
		mSizeList = new JList<>(getSizeList());
		
		mExampleBorder = new TitledBorder(t("example"));
		
		tExample.setBorder(mExampleBorder);
		tExample.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel pTop = new JPanel(new GridLayout(0, 3));
			JPanel pTopLeft = new JPanel(new BorderLayout());
				JPanel pTopLeftTop = new JPanel(new GridLayout(2, 0));
				pTopLeftTop.add(t1);
				pTopLeftTop.add(mSearchFont);
			pTopLeft.add(pTopLeftTop, BorderLayout.NORTH);
			pTopLeft.add(new JScrollPane(mFontList), BorderLayout.CENTER);
			
			JPanel pTopCenter = new JPanel(new BorderLayout());
				JPanel pTopCenterTop = new JPanel(new GridLayout(2, 0));
				pTopCenterTop.add(t2);
				pTopCenterTop.add(mSearchStyle);
			pTopCenter.add(pTopCenterTop, BorderLayout.NORTH);
			pTopCenter.add(new JScrollPane(mStyleList), BorderLayout.CENTER);
			
			JPanel pTopRight = new JPanel(new BorderLayout());
				JPanel pTopRightTop = new JPanel(new GridLayout(2, 0));
				pTopRightTop.add(t3);
				pTopRightTop.add(mSearchSize);
			pTopRight.add(pTopRightTop, BorderLayout.NORTH);
			pTopRight.add(new JScrollPane(mSizeList), BorderLayout.CENTER);
			
		pTop.setBorder(new EmptyBorder(10, 5, 20, 5));
		pTop.add(pTopLeft);
		pTop.add(pTopCenter);
		pTop.add(pTopRight);
		
		JPanel pBottom = new JPanel(new BorderLayout());
			JPanel pBottomCenter = new JPanel(new BorderLayout());
			pBottomCenter.add(tExample, BorderLayout.CENTER);
			pBottomCenter.setBorder(new EmptyBorder(20, 100, 20, 100));
			
			JPanel pBottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			pBottomRight.add(mCheck);
			pBottomRight.add(mCancel);
			
		pBottom.add(pBottomCenter, BorderLayout.CENTER);
		pBottom.add(pBottomRight, BorderLayout.SOUTH);
		
		add(pTop);
		add(pBottom);
	}
	
	private void initEvent() {
		mFontList.addMouseListener(new MouseSmoothClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fontname = mFontList.getSelectedValue();
				mSearchFont.setText(fontname);
				setExFont(fontname);
			}
		});
		
		mStyleList.addMouseListener(new MouseSmoothClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FontStyle fontstyle = mStyleList.getSelectedValue();
				mSearchStyle.setText(fontstyle.toString());
				setExFont(fontstyle);
			}
		});
		
		mSizeList.addMouseListener(new MouseSmoothClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer size = mSizeList.getSelectedValue();
				mSearchSize.setText(String.valueOf(size));
				setExFont(size);
			}
		});
		
		mCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		mCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mUserFont = null;
				setVisible(false);
			}
		});
	}
	
	private String[] getFontList() {
		String[] fontList;
		
		fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		return fontList;
	}
	
	private FontStyle[] getStyleList() {
		FontStyle[] stylelist = new FontStyle[4];
		
		stylelist[0] = new FontStyle(t("font-plain"), Font.PLAIN);
		stylelist[1] = new FontStyle(t("font-bold"), Font.BOLD);
		stylelist[2] = new FontStyle(t("font-italic"), Font.ITALIC);
		stylelist[3] = new FontStyle(t("font-bold+italic"), Font.BOLD + Font.ITALIC);
		
		return stylelist;
	}
	
	private Integer[] getSizeList() {
		Integer[] sizeList = new Integer[65];
		
		for (int i = 8; i < 73; i++)
			sizeList[i - 8] = i;
		
		return sizeList;
	}
	
	private void setExFont(int size) {
		setExFont(new Font(mUserFont.getName(), mUserFont.getStyle(), size));
	}
	
	private void setExFont(FontStyle style) {
		setExFont(new Font(mUserFont.getName(), style.getStyle(), mUserFont.getSize()));
	}
	
	private void setExFont(String font) {
		setExFont(new Font(font, mUserFont.getStyle(), mUserFont.getSize()));
	}
	
	private void setExFont(Font font) {
		mUserFont = font;
		tExample.setFont(mUserFont);
	}
	
	public Font getUserFont() {
		return mUserFont;
	}
	
	public void showDialog() {
		mSearchFont.setText(mUserFont.getName());
		mSearchStyle.setText(getStyleList()[mUserFont.getStyle()].toString());
		mSearchSize.setText(String.valueOf(mUserFont.getSize()));
		
		mFontList.setSelectedValue(mUserFont.getName(), true);
		mStyleList.setSelectedIndex(mUserFont.getStyle());
		mSizeList.setSelectedValue(mUserFont.getSize(), true);
		
		setVisible(true);
	}

	private String t(String key) { return Lang.t(key); }
	
	public static void main(String[] args) {
		Lang.init();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FontChooser fc = new FontChooser(null);
				fc.setExFont(DEFAULT_FONT);
				fc.showDialog();
				if (fc.getUserFont() != null)
					log(fc.getUserFont());
			}
		});
	}
	
	class FontStyle {
		String mStyleName;
		int mStyleCode;
		
		public FontStyle(String name, int code) {
			mStyleName = name;
			mStyleCode = code;
		}
		
		public FontStyle(int code) {
			mStyleName = getStyleList()[code].toString();
			mStyleCode = code;
		}
		
		public String toString() { return mStyleName; }
		public int getStyle() { return mStyleCode; }
	}
	
	/* *
	 *   How To Get System Font List:
	 * 		import java.awt.GraphicsEnvironment;
	 * 		String[] fontList
	 * 			 = GraphicsEnvironment
	 * 				.getLocalGraphicsEnvironment()
	 * 				.getAvailableFontFamilyNames();
	 * 
	 * */
}
