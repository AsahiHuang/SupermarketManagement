package com.java.model;

public class GoodType {
	private int id;
	private String goodTypeName;
	private String goodTypeDesc;
	
	public GoodType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public GoodType(String goodTypeName) {
		super();
		this.goodTypeName = goodTypeName;
	}
	public GoodType(String goodTypeName,String goodTypeDesc) {
		super();
		this.goodTypeName = goodTypeName;
		this.goodTypeDesc = goodTypeDesc;
	} 
	
	public GoodType(int id,String goodTypeName, String goodTypeDesc) {
		super();
		this.id=id;
		this.goodTypeName=goodTypeName;
		this.goodTypeDesc=goodTypeDesc;
	}




	public String getGoodTypeName() {
		return goodTypeName;
	}
	public void setGoodTypeName(String goodTypeName) {
		this.goodTypeName = goodTypeName;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodTypeDesc() {
		return goodTypeDesc;
	}
	public void setGoodTypeDesc(String goodTypeDesc) {
		this.goodTypeDesc = goodTypeDesc;
	}
	
	

}
