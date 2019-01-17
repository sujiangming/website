package com.gnit.utils;

import java.io.UnsupportedEncodingException;

public class CharacterUtil {
	public static String getUTF_8String(String str) throws UnsupportedEncodingException{
		byte[] arrayStr = str.getBytes("iso-8859-1");
		String rtnStr = new String(arrayStr, "UTF-8");
		return rtnStr;
	}

}
