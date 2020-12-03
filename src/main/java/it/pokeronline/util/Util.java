package it.pokeronline.util;

import java.text.SimpleDateFormat;

public class Util {
	
	public static boolean isInteger (String numString) {
		try {
			Integer.parseInt(numString);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isEmptyOrNull(String value) {
		return value == null || value == "";
	}
	
	public static boolean isLong (String value) {
		try {
			Long.parseLong(value);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isDate(String date) {
		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

}
