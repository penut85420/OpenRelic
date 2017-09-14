package main.Data;

import java.awt.*;
import java.util.*;

public class ColorManager {
	static HashMap<String, Color> mColorMap;
	
	// Color Key
	public final static String CommonFG = "CommonForeground";
	public final static String CommonBG = "CommonBackground";
	public final static String UncommonFG = "UncommonForeground";
	public final static String UncommonBG = "UncommonBackground";
	public final static String RareFG = "RareForeground";
	public final static String RareBG = "RareBackground";
	
	// Default Color
	public final static Color DefaultCommonFGColor = Color.decode("#006100");
	public final static Color DefaultCommonBGColor = Color.decode("#C6EFCE");
	public final static Color DefaultUncommonFGColor = Color.decode("#0B5394");
	public final static Color DefaultUncommonBGColor = Color.decode("#9FC5E8");
	public final static Color DefaultRareFGColor = Color.decode("#9C6500");
	public final static Color DefaultRareBGColor = Color.decode("#FFEB9C");
	
	public static void init() {
		mColorMap = new HashMap<>();
		
		if (!loadColorSetting())
			setDefaultColor();
	}
	
	private static void setDefaultColor() {
		mColorMap.put(CommonFG, DefaultCommonFGColor);
		mColorMap.put(CommonBG, DefaultCommonBGColor);
		mColorMap.put(UncommonFG, DefaultUncommonFGColor);
		mColorMap.put(UncommonBG, DefaultUncommonBGColor);
		mColorMap.put(RareFG, DefaultRareFGColor);
		mColorMap.put(RareBG, DefaultRareBGColor);
	}

	public static void setColor(String key, Color c) {
		mColorMap.put(key, c);
	}
	
	public static String getColor(String key) {
		Color c = mColorMap.get(key);
		return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
	}
	
	public static String getForegroundTaggedText(String t, String colorName) {
		return String.format("<font color=%s>%s</font>", getColor(colorName), t);
	}
	
	public static String getBackgroundTaggedText(String t, String colorName) {
		return String.format("<span bgcolor=%s>%s</span>", getColor(colorName), t);
	}
	
	public static String getForeBackgroundTaggedText(String t, String fgColor, String bgColor) {
		return getBackgroundTaggedText(getForegroundTaggedText(t, fgColor), bgColor);
	}
	
	// Load User Color Setting
	private static boolean loadColorSetting() {
		// TODO Load Setting from "user/color.dat"
		return false;
	}
}
