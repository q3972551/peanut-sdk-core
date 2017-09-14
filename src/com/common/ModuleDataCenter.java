package com.common;

import com.redis.RedisTools;

public class ModuleDataCenter
{
	/**
	 * 向redis提供数据 
	 * @param 模块
	 * @param 数据
	 * @return 成功与否
	 */
	public static void providerData(ModuleDTO dto)
	{
		RedisTools.addList(dto.getCenter().getKey(), dto.toString());
	}
}
