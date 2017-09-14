package com.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import com.config.ServerConfig;

public class JSONReadUtil
{	
	public static JSONObject readFile(String path)
	{
		BufferedReader reader = null;
		String laststr = "";
		try
		{
			InputStream in = ServerConfig.class.getResourceAsStream(path);
			InputStreamReader inputStreamReader;
			inputStreamReader = new InputStreamReader(in, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			
			String tempString = null;
			while((tempString = reader.readLine()) != null)
			{
				laststr += tempString;
			}
			reader.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		
		JSONObject ob = JSONObject.fromObject(laststr);
		return ob;
	}
}
