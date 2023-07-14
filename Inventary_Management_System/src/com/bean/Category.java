package com.bean;

public class Category {

	int id;
	String name;
	public int getId()
	{
		return id;
	}
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
