package com.app.ManiManitas.entity;

import jakarta.persistence.Entity;

@Entity
public class Category extends DomainEntity{
	private static final long serialVersionUID = 1L;
	
	private String name;

	
	
	public Category() {
		super();
	}

	public Category(int id, String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [ name=" + name + "]";
	}	
	
	
}
