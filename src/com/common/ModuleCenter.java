package com.common;

public enum ModuleCenter
{
	Mail("MailDataDTO","mail_list"),
	Radio("RadioDataDTO","radio_list");
	
	private String m_classname = null;
	private String m_key       = null;
	private ModuleCenter(String name,String key)
	{
		m_classname = name;
		m_key       = key;
	}
	
	public String getClassName()
	{
		return this.m_classname;
	}
	
	public String getKey()
	{
		return m_key;
	}
}
