package com.service;

public interface LoginService {
	public int isLogin(String token,String passwd,String equip);
	public boolean isCheckToken(String token,String equip);
}
