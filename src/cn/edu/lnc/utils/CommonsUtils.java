package cn.edu.lnc.utils;

import java.util.UUID;

public class CommonsUtils {
	//封装一串随机数作为唯一标识
	public static String getUUID(){
		return UUID.randomUUID().toString();	
	}
}
