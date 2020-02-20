package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.java.model.UserRegister;

public class UserRegisterDao {
	
	public static int add(Connection con,UserRegister userRegister)throws Exception{
		String sql="insert into t_user values(null,?,?)";
		PreparedStatement pstmt=con.prepareCall(sql);
		pstmt.setString(1, userRegister.getUsername());
		pstmt.setString(2, userRegister.getPassword());
		return pstmt.executeUpdate();
	}
}
