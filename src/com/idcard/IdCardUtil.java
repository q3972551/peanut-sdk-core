package com.idcard;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.http.util.HttpUtils;
import com.tools.JSONReadUtil;

public class IdCardUtil
{
	private static String appcode        = null;
	private static final String Host     = "http://idcard.market.alicloudapi.com";
	private static final String Path     = "/lianzhuo/idcard";
	private static final String Method   = "GET";
	
	private static Logger m_logger = Logger.getLogger(IdCardUtil.class);
	
	static {
		JSONObject ob     = JSONReadUtil.readFile("/config.json");		
		JSONObject idcard = ob.getJSONObject("idcard");
		appcode           = idcard.getString("appcode");
	}
	
	

	
	/**
	 *  0	匹配	                                    匹配
		5	不匹配                                    不匹配
		14	无此身份证号码	            无此身份证号码
		96	交易失败，请稍后重试	交易失败，请稍后重试
	 * @param code
	 * @param name
	 * @return 正确的时候返回内容 ，如果错误返回非200的 错误码 例:error:401,返回"error:null"代表网络异常
	 */
	public static String isMatch(String code,String name)
	{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("cardno",code);
		querys.put("name", name);

		
		try
		{
			HttpResponse response = HttpUtils.doGet(Host,Path,Method, headers, querys);
			StatusLine line = response.getStatusLine();
			if (line.getStatusCode() == 200)
			{
				HttpEntity entity = response.getEntity();
				String value = EntityUtils.toString(entity);
				return value;
			}
			else
			{
				StringBuffer buffer = new StringBuffer();
				buffer.append("error:").append(line.getStatusCode());
				return buffer.toString();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m_logger.error("IdCardUtil.class", e);
		}
		finally{
		
		}
		return "error:null";
	}
}
