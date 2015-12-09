package com.gj.administrator.gjerp.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

@Table(name="guests")
public class Guest extends Model {
	public String name;
	public String type;
	public String cardCatalog;
	public String cardId;
	public String country;
	public String address;
	public String company;
	public String telphone;
	public String mobile;
	public String gender;
	public String email;
	public Timestamp createTime;	//资料创建时间

}
