/**
 * Author  : Cao_Xiao_Bin
 * Created on : 2015骞�鏈�鏃ヤ笂鍗�1:46:25
 * Content : uuid鐢熸垚绫�
 * Example :
 */
package com.tools;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{

	public static String getUUID()
	{
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		return id;
	}
	
	public static boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches())
		{
			return false;
		}
		return true;
	}

	public static boolean isChineseChar(String str)
	{
		boolean temp = false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find())
		{
			temp = true;
		}
		return temp;
	}

	public static boolean isExsitName(String content)
	{
		return content.contains("@");
	}
	
	/**
	 * 得到N位的数字码
	 * @param count
	 * @return
	 */
	public static String getDigitCode(int digit)
	{
		Random  r   = new Random();
		int maxCount= 1;
		for (int i = 0 ; i < digit ; i ++)
		{
			maxCount *= 10;
		}
		String body = String.valueOf(r.nextInt(maxCount));
		int length  = digit - body.length();
		if (length > 0 )
		{
			for (int i = 0 ; i < length ; i ++)
			{
				body = "0" + body;
			}
		}
		
		return body;

	}
	
	/**
	 * 得到字母码
	 * @param 位数
	 * @return
	 */
	public static String getAlphaCode(int digit)
	{
		String chars   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String newChar = "";
		for(int i = 0 ; i < digit ; i ++)
		{
			newChar += chars.charAt((int)(Math.random() * 26));
		}
		return newChar;
	}
}
