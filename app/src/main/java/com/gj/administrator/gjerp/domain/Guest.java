package com.gj.administrator.gjerp.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;


@Table(name="guests")
public class Guest extends Model {
	public enum TYPE{
		NORMAL,VIP,GROUP
	}
	@Column(name="name",length = 20, notNull = true)
	public String name;
	@Column(name="type", notNull =  true)
	public int type;
	@Column(name = "createTime",length = 20, notNull =  true)
	public Date createTime;
	@Column(name="cardCatalog",length = 16, notNull =  true)
	public String cardCatalog;
	@Column(name="cardId",length = 32, notNull =  true)
	public String cardId;
	@Column(name="telphone",length = 16, notNull =  true)
	public String telphone;
	@Column(name="gender",length = 5, notNull =  true)
	public String gender;
	@Column(name="country",length = 16)
	public String country;
	@Column(name="address",length = 50)
	public String address;
	@Column(name="company",length = 50)
	public String company;
	@Column(name="email",length = 32)
	public String email;

	public Guest(){}

	public Guest(String name, TYPE type, String cardCatalog, String cardId, String telphone, String gender, String country, String address, String company, String email) {
		this.name = name;
		this.type = type.ordinal();
		this.createTime = new Date();
		this.cardCatalog = cardCatalog;
		this.cardId = cardId;
		this.telphone = telphone;
		this.gender = gender;
		this.country = country;
		this.address = address;
		this.company = company;
		this.email = email;
	}

}