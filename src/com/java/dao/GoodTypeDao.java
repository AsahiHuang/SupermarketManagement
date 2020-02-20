package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.GoodType;
import com.java.util.StringUtil;

public class GoodTypeDao {
	/**
	 * 商品类别添加
	 * @param con
	 * @param goodtype
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,GoodType goodType)throws Exception{
		String sql="insert into t_goodType values(null,?,?)";
		PreparedStatement pstmt=con.prepareCall(sql);
		pstmt.setString(1, goodType.getGoodTypeName());
		pstmt.setString(2, goodType.getGoodTypeDesc());
		return pstmt.executeUpdate();
	}
	
	public ResultSet list(Connection con,GoodType goodType)throws Exception{
		String sql="select * from t_goodType";
		if(StringUtil.isNotEmpty(goodType.getGoodTypeName())) {
			sql+=" where goodTypeName like '%"+goodType.getGoodTypeName()+"%'";
		}
		PreparedStatement pstmt=con.prepareCall(sql);
		return pstmt.executeQuery();
	}
	//返回查找结果
	public ResultSet findRs(Connection con,String name) throws Exception{
		String sql = "select * from t_goodType where goodTypeName=?";
		PreparedStatement pstmt=con.prepareCall(sql);
		pstmt.setString(1, name);
		return pstmt.executeQuery();
	}

	//删除商品类别
	public int delete(Connection con,String name)throws Exception{
		String sql="delete from t_goodType where goodTypeName=?";
		PreparedStatement pstmt=con.prepareCall(sql);	
		pstmt.setString(1, name);
		return pstmt.executeUpdate();
	}
	//更新商品类别
	public int update(Connection con,GoodType goodType)throws Exception{
		String sql="update t_goodType set goodTypeName=?,goodTypeDesc=? where id=?";
		PreparedStatement pstmt=con.prepareCall(sql);
		pstmt.setString(1, goodType.getGoodTypeName());
		pstmt.setString(2, goodType.getGoodTypeDesc());
		pstmt.setInt(3, goodType.getId());
		return pstmt.executeUpdate();
	}
}
