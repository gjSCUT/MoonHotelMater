package com.gj.administrator.gjerp.domain;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

@Table(name="tb_guest")
public class Guest {
	private String gtId;			//uupkid
	private String gtName;			//客人姓名
	private String gtType;			//客人类型
	private String gtCardCatalog;	//客人证件类别
	private String gtCardId;		//客人证件号码
	private String gtCountry;		//国籍
	private String gtAddress;		//地址
	private String gtZip;			//邮编
	private String gtCompany;		//公司(工作单位)
	private String gtTelphone;		//固定电话
	private String gtMobile;		//手机
	private String gtGender;		//性别
	private String gtEmail;			//Email
	private Timestamp gtCreateTime;	//资料创建时间

	public String getGtId() {
		return gtId;
	}
	public void setGtId(String gtId) {
		this.gtId = gtId;
	}

	public String getGtName() {
		return gtName;
	}
	public void setGtName(String gtName) {
		this.gtName = gtName;
	}

	public String getGtType() {
		return gtType;
	}
	public void setGtType(String gtType) {
		this.gtType = gtType;
	}

	public String getGtCardCatalog() {
		return gtCardCatalog;
	}
	public void setGtCardCatalog(String gtCardCatalog) {
		this.gtCardCatalog = gtCardCatalog;
	}

	public String getGtCardId() {
		return gtCardId;
	}
	public void setGtCardId(String gtCardId) {
		this.gtCardId = gtCardId;
	}

	public String getGtCountry() {
		return gtCountry;
	}
	public void setGtCountry(String gtCountry) {
		this.gtCountry = gtCountry;
	}

	public String getGtAddress() {
		return gtAddress;
	}
	public void setGtAddress(String gtAddress) {
		this.gtAddress = gtAddress;
	}

	public String getGtZip() {
		return gtZip;
	}
	public void setGtZip(String gtZip) {
		this.gtZip = gtZip;
	}

	public String getGtCompany() {
		return gtCompany;
	}
	public void setGtCompany(String gtCompany) {
		this.gtCompany = gtCompany;
	}

	public String getGtTelphone() {
		return gtTelphone;
	}
	public void setGtTelphone(String gtTelphone) {
		this.gtTelphone = gtTelphone;
	}

	public String getGtMobile() {
		return gtMobile;
	}
	public void setGtMobile(String gtMobile) {
		this.gtMobile = gtMobile;
	}

	public String getGtGender() {
		return gtGender;
	}
	public void setGtGender(String gtGender) {
		this.gtGender = gtGender;
	}

	public String getGtEmail() {
		return gtEmail;
	}
	public void setGtEmail(String gtEmail) {
		this.gtEmail = gtEmail;
	}

	public Timestamp getGtCreateTime() {
		return gtCreateTime;
	}
	public void setGtCreateTime(Timestamp gtCreateTime) {
		this.gtCreateTime = gtCreateTime;
	}
}
