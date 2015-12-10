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

}
