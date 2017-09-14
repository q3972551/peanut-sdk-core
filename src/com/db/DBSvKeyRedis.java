package com.db;

import java.io.IOException;

public class DBSvKeyRedis extends DBBase
{
	protected static DBSvKeyRedis[] s_table;
	protected static short s_dataCount = 0;

	protected String m_RoleCount;
	protected String m_GameTimes;
	protected String m_PriceWin;
	protected String m_RankList;
	protected String m_RoleInfo;
	protected String m_PrimaryData;
	protected String m_ShareList;
	protected String m_RadioList;
	protected String m_MailList;
	protected String m_MailCount;
	protected String m_ChallengeTimes;

	/**
	* 获取数据总数量
	*/
	public static int Count()
	{
		return s_dataCount;
	}

	/**
	* 通过主键或序号获取数据实例
	*/
	public static DBSvKeyRedis get(int index)
	{
		return s_table[index];
	}

	public static String getRoleCount(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_RoleCount);
	}

	public static String getGameTimes(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_GameTimes);
	}

	public static String getPriceWin(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_PriceWin);
	}

	public static String getRankList(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_RankList);
	}

	public static String getRoleInfo(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_RoleInfo);
	}

	public static String getPrimaryData(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_PrimaryData);
	}

	public static String getShareList(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_ShareList);
	}

	public static String getRadioList(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_RadioList);
	}

	public static String getMailList(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_MailList);
	}

	public static String getMailCount(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_MailCount);
	}

	public static String getChallengeTimes(int index)
	{
		DBSvKeyRedis data = s_table[index];
		return (data == null ? null : data.m_ChallengeTimes);
	}

	/**
	* 初始化数据库，获取静态数据
	*/
	public static void initData()
	{
		loadData("SvKey_Redis.bin");

		try
		{
			s_dataCount = readShort();
			s_table = new DBSvKeyRedis[s_dataCount];

			for (int i = 0; i < s_dataCount; i++)
			{
				DBSvKeyRedis data = new DBSvKeyRedis();
				s_table[i] = data;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		clearData();
	}

	public DBSvKeyRedis() throws IOException
	{
		m_RoleCount = readString();
		m_GameTimes = readString();
		m_PriceWin = readString();
		m_RankList = readString();
		m_RoleInfo = readString();
		m_PrimaryData = readString();
		m_ShareList = readString();
		m_RadioList = readString();
		m_MailList = readString();
		m_MailCount = readString();
		m_ChallengeTimes = readString();
	}

	public String getRoleCount()
	{
		return m_RoleCount;
	}

	public String getGameTimes()
	{
		return m_GameTimes;
	}

	public String getPriceWin()
	{
		return m_PriceWin;
	}

	public String getRankList()
	{
		return m_RankList;
	}

	public String getRoleInfo()
	{
		return m_RoleInfo;
	}

	public String getPrimaryData()
	{
		return m_PrimaryData;
	}

	public String getShareList()
	{
		return m_ShareList;
	}

	public String getRadioList()
	{
		return m_RadioList;
	}

	public String getMailList()
	{
		return m_MailList;
	}

	public String getMailCount()
	{
		return m_MailCount;
	}

	public String getChallengeTimes()
	{
		return m_ChallengeTimes;
	}

	public String toString()
	{
		return "[" + " RoleCount=" + m_RoleCount + " GameTimes=" + m_GameTimes + " PriceWin=" + m_PriceWin + " RankList=" + m_RankList + " RoleInfo=" + m_RoleInfo + " PrimaryData=" + m_PrimaryData + " ShareList=" + m_ShareList + " RadioList=" + m_RadioList + " MailList=" + m_MailList + " MailCount=" + m_MailCount + " ChallengeTimes=" + m_ChallengeTimes + " ]";
	}
}
