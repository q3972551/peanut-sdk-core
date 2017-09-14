package com.tools;

import java.util.Random;

public class RandomUtil
{
	/**
	 * 根据给定的范围生成随机数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDigital(int start,int end){
		Random rd  = new Random();
		int value = rd.nextInt(end - start + 1) + start; 	
		return value;
	}
	
	public static boolean isExist(int count,int sum)
	{
		Random r  = new Random();
		int value = r.nextInt(sum + 1);
		
		if (value < count)
		{
			return true;
		}
		
		return false;
	}
}
