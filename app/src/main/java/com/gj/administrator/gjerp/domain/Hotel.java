package com.gj.administrator.gjerp.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name="hotels")
public class Hotel extends Model {
	@Column(name = "name")
	public String name;

	public Hotel(){
		super();
	}
	public Hotel(String name) {
		super();
		this.name = name;
	}
}
