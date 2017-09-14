/**
 * Author  : Cao_Xiao_Bin
 * Created on : 2015年4月1日上午11:46:25
 * Content : byte数组和int转换类
 * Example :
 */
package com.tools;

public class ByteUtil
{
	/**
	 * 将 Little-Endian 的字节数组转为 int 类型的数据<br />
	 * Little-Endian 表示高位字节在高位索引中
	 * 
	 * @param bys
	 *            字节数组
	 * @param start
	 *            需要转换的开始索引位数
	 * @param len
	 *            需要转换的字节数量
	 * @return 指定开始位置和长度以 LE 方式表示的 int 数值
	 */
	public static int bytes2IntLE(byte[] bys, int start, int len)
	{
		return bytes2Int(bys, start, len, false);
	}

	public static int bytes2IntLE(byte[] bys)
	{
		return bytes2Int(bys, 0, bys.length, false);
	}

	/**
	 * 将 Big-Endian 的字节数组转为 int 类型的数据<br />
	 * Big-Endian 表示高位字节在低位索引中
	 * 
	 * @param bys
	 *            字节数组
	 * @param start
	 *            需要转换的开始索引位数
	 * @param len
	 *            需要转换的字节数量
	 * @return 指定开始位置和长度以 BE 方式表示的 int 数值
	 */
	public static int bytes2IntBE(byte[] bys, int start, int len)
	{
		return bytes2Int(bys, start, len, true);
	}

	public static int bytes2IntBE(byte[] bys)
	{
		return bytes2Int(bys, 0, bys.length, true);
	}

	/**
	 * 将字节数组转为 Java 中的 int 数值
	 * 
	 * @param bys
	 *            字节数组
	 * @param start
	 *            需要转换的起始索引点
	 * @param len
	 *            需要转换的字节长度
	 * @param isBigEndian
	 *            是否是 BE（true -- BE 序，false -- LE 序）
	 * @return
	 */
	private static int bytes2Int(byte[] bys, int start, int len,
			boolean isBigEndian)
	{
		int n = 0;
		for (int i = start, k = start + len % (Integer.SIZE / Byte.SIZE + 1); i < k; i++)
		{
			n |= (bys[i] & 0xff) << ((isBigEndian ? (k - i - 1) : i) * Byte.SIZE);
		}
		return n;
	}

	public static int byte2ArrayToInt(byte[] bytes)
	{
		double x = 0;
		for (int i = 0; i < bytes.length; i++)
		{
			int value = bytes[i];
			if (value == 1)
			{
				x = x + Math.pow(2, i);
			}
		}

		return (int) x;
	}
}
