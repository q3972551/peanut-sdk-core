package com.common;

import net.sf.json.JSONObject;

public interface ModuleDTO
{
	public JSONObject toJSON() ;
	public ModuleCenter getCenter();
}
