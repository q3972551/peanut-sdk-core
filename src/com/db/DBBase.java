package com.db;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DBBase
{
	protected static final String BIN_FILE_PATH = "/resource";

	private static DataInputStream s_stream;

	/**
	 * ��ȡ�������ļ�
	 */
	protected static void loadData(String fileName)
	{
		String path = BIN_FILE_PATH + File.separator + fileName;
		InputStream in = DBBase.class.getResourceAsStream(path);
		s_stream = new DataInputStream(new BufferedInputStream(in));

	}

	/**
	 * �رն������ļ�
	 */
	protected static void clearData()
	{
		try
		{
			s_stream.close();
			s_stream = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.gc();
	}

	/**
	 * ����ֽ�
	 */
	protected static void skip(int step) throws IOException
	{
		s_stream.skip(step);
	}

	/**
	 * ��ȡ1�ֽ�
	 */
	protected static short readByte() throws IOException
	{
		return s_stream.readByte();
	}

	/**
	 * ��ȡ������
	 */
	protected static short readShort() throws IOException
	{
		int value = s_stream.readUnsignedByte();
		value |= (s_stream.readUnsignedByte() << 8);
		return (short) value;
	}

	/**
	 * ��ȡ����
	 */
	protected static int readInt() throws IOException
	{
		int value = s_stream.readUnsignedByte();
		value |= (s_stream.readUnsignedByte() << 8);
		value |= (s_stream.readUnsignedByte() << 16);
		value |= (s_stream.readUnsignedByte() << 24);
		return value;
	}

	/**
	 * ��ȡ������
	 */
	protected static float readFloat() throws IOException
	{
		String value = readString();
		return Float.parseFloat(value);
	}

	/**
	 * ��ȡ����ֵ
	 */
	protected static boolean readBoolean() throws IOException
	{
		return s_stream.readBoolean();
	}

	/**
	 * ��ȡ�ַ�
	 */
	protected static String readString() throws IOException
	{
		int length = s_stream.readByte();
		char[] chars = new char[length];
		for (int i = 0; i < length; i++)
		{
			chars[i] = (char)s_stream.readByte();
		}
		return String.valueOf(chars);
	}

	/**
	 * ��ȡint����
	 */
	protected static int[] readIntArray() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			int[] res = new int[count];
			for (int i = 0; i < count; i++)
			{
				res[i] = readInt();
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡfloat����
	 */
	protected static float[] readFloatArray() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			float[] res = new float[count];
			for (int i = 0; i < count; i++)
			{
				res[i] = readFloat();
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡboolean����
	 */
	protected static boolean[] readBooleanArray() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			boolean[] res = new boolean[count];
			for (int i = 0; i < count; i++)
			{
				res[i] = readBoolean();
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡstring����
	 */
	protected static String[] readStringArray() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			String[] res = new String[count];
			for (int i = 0; i < count; i++)
			{
				res[i] = readString();
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡint�ֵ�
	 */
	protected static HashMap<String, Integer> readIntMap() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			HashMap<String, Integer> res = new HashMap<String, Integer>(count, 1.0f);
			for (int i = 0; i < count; i++)
			{
				res.put(readString(), readInt());
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡfloat�ֵ�
	 */
	protected static HashMap<String, Float> readFloatMap() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			HashMap<String, Float> res = new HashMap<String, Float>(count, 1.0f);
			for (int i = 0; i < count; i++)
			{
				res.put(readString(), readFloat());
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡboolean�ֵ�
	 */
	protected static HashMap<String, Boolean> readBooleanMap() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			HashMap<String, Boolean> res = new HashMap<String, Boolean>(count, 1.0f);
			for (int i = 0; i < count; i++)
			{
				res.put(readString(), readBoolean());
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡstring�ֵ�
	 */
	protected static HashMap<String, String> readStringMap() throws IOException
	{
		short count = readShort();
		if (count > 0)
		{
			HashMap<String, String> res = new HashMap<String, String>(count, 1.0f);
			for (int i = 0; i < count; i++)
			{
				res.put(readString(), readString());
			}
			return res;
		}
		return null;
	}

	/**
	 * ��ȡ��������(int)
	 */
	protected String listDesc(int[] attribute)
	{
		if (attribute == null)
			return "";
		String res = "[";
		for (int i = 0; i < attribute.length; i++)
		{
			res += attribute[i];
			if (i < attribute.length - 1)
				res += ",";
		}
		return res + "]";
	}

	/**
	 * ��ȡ��������(boolean)
	 */
	protected String listDesc(boolean[] attribute)
	{
		if (attribute == null)
			return "";
		String res = "[";
		for (int i = 0; i < attribute.length; i++)
		{
			res += attribute[i];
			if (i < attribute.length - 1)
				res += ",";
		}
		return res + "]";
	}

	/**
	 * ��ȡ��������(float)
	 */
	protected String listDesc(float[] attribute)
	{
		if (attribute == null)
			return "";
		String res = "[";
		for (int i = 0; i < attribute.length; i++)
		{
			res += attribute[i];
			if (i < attribute.length - 1)
				res += ",";
		}
		return res + "]";
	}

	/**
	 * ��ȡ��������(string)
	 */
	protected String listDesc(String[] attribute)
	{
		if (attribute == null)
			return "";
		String res = "[";
		for (int i = 0; i < attribute.length; i++)
		{
			res += attribute[i];
			if (i < attribute.length - 1)
				res += ",";
		}
		return res + "]";
	}

	protected String listDesc(ArrayList<Object> attribute)
	{
		if (attribute == null)
			return "";
		String res = "[";
		for (int i = 0; i < attribute.size(); i++)
		{
			res += attribute.get(i).toString();
			if (i < attribute.size() - 1)
				res += ",";
		}
		return res + "]";
	}

	/**
	 * ��ȡ�ֵ�����
	 */
	protected String dictDesc(Object attribute)
	{
		if (attribute == null)
			return "";
		return attribute.toString();
	}
}
