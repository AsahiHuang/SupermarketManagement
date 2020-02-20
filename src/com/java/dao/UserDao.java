package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.User;


/**
 * 用户Dao类
 */
public class UserDao {


	//登录验证
	public User login(Connection con,User user)throws Exception
	{
		User resultUser=null;
		String sql="select * from t_user where username=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())
		{
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setIdentify(rs.getBoolean("ismanager"));
		}
		return resultUser;
	}
	
	//用户信息查询
		public static ResultSet list(Connection con,User user)throws Exception{
			StringBuffer sb=new StringBuffer("select * from t_user");
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		}
		

		//用户信息删除
		public int delete(Connection con,String id)throws Exception{
			String sql="delete from t_user where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}
			
				
		//用户信息修改
		public int update(Connection con,User user)throws Exception{
			String sql="update t_user set username=?,password=? where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getId());
			return pstmt.executeUpdate();
		}
		
		
		//用户信息添加
		
		
		
		
		
		
		
		
		
		

}
