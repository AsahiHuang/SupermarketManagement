package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Good;
import com.java.model.GoodType;
import com.java.util.StringUtil;

//商品类
public class GoodDao {

    //商品添加
	public int add(Connection con,Good good)throws Exception{
		String sql="insert into t_good values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareCall(sql);
		pstmt.setString(1, good.getCategory());
		pstmt.setString(2, good.getName());
		pstmt.setString(3, good.getPrice());
		pstmt.setString(4,good.getCount());
		pstmt.setString(5,good.getState());
		return pstmt.executeUpdate();
	}
	//商品信息查询 -按名和类
	public ResultSet list(Connection con,Good good)throws Exception{
		String sql ="select * from t_good where name like ";
		sql += ("'%" + good.getName() + "%' AND category like '%" + good.getCategory() + "%'");
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	public ResultSet listByID(Connection con,int id) throws Exception{
		String sql = "select * from t_good where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeQuery();
	}
	//商品删除 -按编号
	public int delete(Connection con,int id)throws Exception{
		String sql="delete from t_good where id=?";
		PreparedStatement pstmt=con.prepareCall(sql);	
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
	}
	
	
	//商品删除 -按类别（用于类别删除后删除同类的商品）
	public int deleteByCate(Connection con,String category)throws Exception{
		String sql="delete from t_good where category=?";
		PreparedStatement pstmt=con.prepareCall(sql);	
		pstmt.setString(1, category);
		return pstmt.executeUpdate();
	}
	//商品更新 -按编号
	public int update(Connection con,Good good)throws Exception{
		String sql = "update t_good set category=?,"
				+ "name=?,"
				+ "price=?,"
				+ "count=?,"
				+ "state=?"
				+ " where id=?";
		PreparedStatement pstmt=con.prepareCall(sql);
		pstmt.setString(1, good.getCategory());
		pstmt.setString(2, good.getName());
		pstmt.setString(3, good.getPrice());
		pstmt.setString(4, good.getCount());
		pstmt.setString(5, good.getState());
		pstmt.setInt(6, good.getId());
		return pstmt.executeUpdate();
	}

	
}
