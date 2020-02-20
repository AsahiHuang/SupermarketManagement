package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;


/**
 * 数据库工具类
 */
public class DbUtil {
	

	private String dbAddress = "";
	private String dbUrl="jdbc:mysql://" + dbAddress + "/db_market?characterEncoding=UTF-8";//数据库连接地址
	private String dbUserName="root";
	private String dbPassword="";
	private String jdbcName="com.mysql.jdbc.Driver";//驱动名称


	/**
     * 连接数据库
	 */
	public Connection getCon()throws Exception
	{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
     * 关闭数据库
	 */
	public void closeCon(Connection con)throws Exception
	{
		if(con!=null)
			con.close();
	}

	//数据库测试
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
