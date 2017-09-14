
package com.config;

import net.sf.json.JSONObject;

import com.tools.JSONReadUtil;

public class ServerConfig {
	private static String s_ip    = null;
	private static int    s_port  = 0;
	
	static{
		if (s_ip == null || s_port == 0)
		{
			JSONObject ob = JSONReadUtil.readFile("/config.json");
			s_ip = ob.getString("host");
			s_port = ob.getInt("port");
		}
	}
	
	public static String IP()
	{
		return s_ip;
	}
	
	public static int PORT()
	{
		return s_port;
	}
}
