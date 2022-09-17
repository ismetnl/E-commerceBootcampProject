package com.ibtech.sales.inventory.entity;

public class Adress {
	
	private long adressId;
	private String adressLine1;
	private String adressLine2;
	private Cart cart;
	private Province province;
	
	
	public long getAdressId() {
		return adressId;
	}
	public void setAdressId(long adressId) {
		this.adressId = adressId;
	}
	public String getAdressLine1() {
		return adressLine1;
	}
	public void setAdressLine1(String adressLine1) {
		this.adressLine1 = adressLine1;
	}
	public String getAdressLine2() {
		return adressLine2;
	}
	public void setAdressLine2(String adressLine2) {
		this.adressLine2 = adressLine2;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
	
	
}
