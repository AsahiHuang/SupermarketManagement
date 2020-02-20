package com.java.util;


//字符串工具类判断1.是不是空 2.不为空
public class StringUtil {
	public static boolean isEmpty(String str)
	{
		if(str==null || "".equals(str.trim()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static boolean isNotEmpty(String str)
	{
		if(str!=null && "".equals(str.trim()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
