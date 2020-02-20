package com.java.model;

//商品实体
public class Good {

private int id;//商品id
private String category;//商品类别
private String name;//商品名称
private String price;//商品价格
private String count;//商品数量
private String state; //商品数量


public Good() {
	super();
}
public Good(String name) {
	super();
	this.name = name;
}

public Good(String category, String name, String price, String count, String state) {
	super();
	this.category = category;
	this.name = name;
	this.price = price;
	this.count = count;
	this.state = state;
}
public Good(int id,String category,String name,String price,String count,String state) {
	super();
	this.id = id;
	this.category = category;
	this.name = name;
	this.price = price;
	this.count = count;
	this.state = state;
}

public Good(String name, String state) {
	super();
	this.name = name;
	this.state = state;
}
public Good(String name,String category,String state) {
	super();
	this.category = category;
	this.name = name;
	this.state = state;
}
public Good(int id) {
	super();
	this.id = id;
}

public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}

}
