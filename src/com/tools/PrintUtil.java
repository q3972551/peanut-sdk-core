package com.tools;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class PrintUtil {
	public static void console(Object ob){
		if (ob instanceof List || 
				ob instanceof Map  ||
				ob instanceof Object[]) 
			{
				JSONArray jo = JSONArray.fromObject(ob);
				System.out.println(jo.toString());
				return;
			}
			
			if(ob instanceof String ||
			   ob instanceof Integer ||
			   ob instanceof Long ||
			   ob instanceof Character ||
			   ob instanceof Short ||
			   ob instanceof Boolean ||
			   ob instanceof Double ||
			   ob instanceof Float ){
				System.out.println(ob);
				return;
			}

			JSONObject jo = JSONObject.fromObject(ob);
			System.out.println(jo.toString());
	}
}
