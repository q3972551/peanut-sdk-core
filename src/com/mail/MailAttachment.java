package com.mail;
import net.sf.json.JSONObject;

public class MailAttachment{
	public int    m_count     = 0;
	public String m_type      = null;
	
	public JSONObject toJson()
	{
		JSONObject jo = new JSONObject();
		jo.put("count", m_count);
		jo.put("type", m_type);
		return jo;
	}
	
	public String toString()
	{
		return this.toJson().toString();
	}

	public int getCount() {
		return m_count;
	}

	public void setCount(int count) {
		this.m_count = count;
	}

	public String getType() {
		return m_type;
	}

	public void setType(String m_type) {
		this.m_type = m_type;
	}
}
