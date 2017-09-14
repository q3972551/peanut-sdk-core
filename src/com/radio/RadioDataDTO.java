package com.radio;

import net.sf.json.JSONObject;

import com.common.ModuleCenter;
import com.common.ModuleDTO;

public class RadioDataDTO implements ModuleDTO{
	
	private int    m_id;
	private String m_parameter;
	private ModuleCenter m_center = ModuleCenter.Radio;
	
	public RadioDataDTO(int id,String parameter)
	{
		m_id = id;
		m_parameter = parameter;
	}
	
	public int getId() 
	{
		return m_id;
	}
	
	public String getParameter() 
	{
		return m_parameter;
	}
	
	@Override
	public JSONObject toJSON()
	{
		// TODO Auto-generated method stub
		JSONObject ob = new JSONObject();
		ob.put("id", this.getId());
		ob.put("parameter", this.getParameter());
		return ob;

	}

	@Override
	public String toString()
	{
		return this.toJSON().toString();
	}
	
	@Override
	public ModuleCenter getCenter()
	{
		return this.m_center;
	}
}
