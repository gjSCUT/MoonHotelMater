package com.gj.administrator.gjerp.domain;

import java.math.BigDecimal;


public class Room {
	public class States{
		private final int number;
		private final String color;
        private final String[] colors = { "#008CD2", "#FF7D00","#FFE000", "#E40090", "#00AF4D", "#E80033" };
		States(int number){
			this.number = number;
			this.color = colors[number];
		}
	}
	private String rmId; // 房号自定义
	private String rmArea; // 区域
	private String rmFloor; // 楼层
	private BigDecimal rmPrctPrice; // 实际价格 相对房间类型里统一设置的价格
	private String rmTelphone; // 分机电话
	private States rmState; // 状态
	private Boolean rmAvailable; // 可用
	private String rmCatalog; // 房间类别
	private String rmPicture; // 房间图片
	private BigDecimal rmPrctDiscount;// 实际折扣 相对房间类型里统一设置的折扣

	public String getRmId() {
		return rmId;
	}

	public void setRmId(String rmId) {
		this.rmId = rmId;
	}

	public String getRmArea() {
		return rmArea;
	}

	public void setRmArea(String rmArea) {
		this.rmArea = rmArea;
	}

	public String getRmFloor() {
		return rmFloor;
	}

	public void setRmFloor(String rmFloor) {
		this.rmFloor = rmFloor;
	}

	public BigDecimal getRmPrctPrice() {
		return rmPrctPrice;
	}

	public void setRmPrctPrice(BigDecimal rmPrctPrice) {
		this.rmPrctPrice = rmPrctPrice;
	}

	public String getRmTelphone() {
		return rmTelphone;
	}

	public void setRmTelphone(String rmTelphone) {
		this.rmTelphone = rmTelphone;
	}

	public int getRmState() {
		return rmState.number;
	}

	public void setRmState(Integer rmState) {
		this.rmState = new States(rmState);
	}

	public String getRoomInfoStateAsColor() {
		return rmState.color;
	}

	public Boolean getRmAvailable() {
		return rmAvailable;
	}

	public void setRmAvailable(Boolean rmAvailable) {
		this.rmAvailable = rmAvailable;
	}

	public String getRmCatalog() {
		return rmCatalog;
	}

	public void setRmCatalog(String rmCatalog) {
		this.rmCatalog = rmCatalog;
	}

	public String getRmPicture() {
		return rmPicture;
	}

	public void setRmPicture(String rmPicture) {
		this.rmPicture = rmPicture;
	}

	public BigDecimal getRmPrctDiscount() {
		return rmPrctDiscount;
	}

	public void setRmPrctDiscount(BigDecimal rmPrctDiscount) {
		this.rmPrctDiscount = rmPrctDiscount;
	}

}
