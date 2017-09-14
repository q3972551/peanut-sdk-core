package com.mail;
import net.sf.json.JSONObject;

public class MailContent{
	private int    m_stencil_id = 0;
	private String m_parameter  = null;
	
	public JSONObject toJson()
	{
		JSONObject jo = new JSONObject();
		jo.put("stencil_id", m_stencil_id);
		jo.put("parameter", m_parameter);
		return jo;
	}
	
	public String toString()
	{
		return this.toJson().toString();
	}

	public int getStencil_id() {
		return m_stencil_id;
	}

	public void setStencil_id(int stencil_id) {
		this.m_stencil_id = stencil_id;
	}

	public String getParameter() {
		return m_parameter;
	}

	public void setParameter(String parameter) {
		this.m_parameter = parameter;
	}
}