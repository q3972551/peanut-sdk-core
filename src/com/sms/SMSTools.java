package com.sms;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tools.JSONReadUtil;
import com.tools.StringUtil;

public class SMSTools
{
	private static IAcsClient s_acsClient    = null;
	private static String     s_signName     = null;
	private static String     s_templateCode = null;
	private static Map<String,String>s_codemap = new HashMap<String,String>();  

	public  static final int MOBILE_NUMBER_ILLEGAL = 1;
	public  static final int UNKOWN                = 3;

	static 
	{
		if (s_acsClient == null)
		{
			try
			{
				JSONObject ob  = JSONReadUtil.readFile("/config.json");
				JSONObject sms = ob.getJSONObject("sms");
				IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
						sms.getString("access_key"),sms.getString("access_secret"));
				DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", 
						sms.getString("product"), sms.getString("domain"));
				IAcsClient acsClient = new DefaultAcsClient(profile);
				s_acsClient = acsClient;
				s_signName  = sms.getString("signName");
				s_templateCode = sms.getString("templateCode");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送验证码
	 * @param phoneNumbers
	 * @param number
	 * @return
	 */
	public static int veriyCode(String phoneNumbers)
	{
		int error = 0;
		try{
			SendSmsRequest request = new SendSmsRequest();
			request.setPhoneNumbers(phoneNumbers);
			request.setSignName(s_signName);
			request.setMethod(MethodType.POST);
			request.setTemplateCode(s_templateCode);

			String number = StringUtil.getDigitCode(6);
			JSONObject ob = new JSONObject();
			ob.put("number", number);
			request.setTemplateParam(ob.toString());
			SendSmsResponse sendSmsResponse = s_acsClient.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				//请求成功
				s_codemap.put(phoneNumbers, number);
			}
			else
			{
				String code = sendSmsResponse.getCode();
				if (code.equals("isv.MOBILE_NUMBER_ILLEGAL"))
				{
					error = MOBILE_NUMBER_ILLEGAL;
				}
				else
				{
					error = UNKOWN;
				}

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			error = UNKOWN;
		}

		return error;
	}
	
	public static boolean isCheckCode(String phone,String code)
	{
		String value = s_codemap.get(phone);
		if (code.equals(value))
		{
			return true;
		}
		
		return false;
	}
}
