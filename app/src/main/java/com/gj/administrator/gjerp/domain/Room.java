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
	public String rmId; // 房号自定义
	public String rmArea; // 区域
	public String rmFloor; // 楼层
	public BigDecimal rmPrctPrice; // 实际价格 相对房间类型里统一设置的价格
	public String rmTelphone; // 分机电话
	public States rmState; // 状态
	public Boolean rmAvailable; // 可用
	public String rmCatalog; // 房间类别
	public String rmPicture; // 房间图片
	public BigDecimal rmPrctDiscount;// 实际折扣 相对房间类型里统一设置的折扣

}
