package com.ibtech.shopping.entity;

public class CartProduct {
	
	private long cartProductId; 
	private long cartId; 
	private long productId;
	private int salesQuantity;
	private double salesPrice;
	private float taxRate; 
	private double lineAmount;
	
	
	public long getCartProductId() {
		return cartProductId;
	}
	
	public void setCartProductId(long cartProductId) {
		this.cartProductId = cartProductId;
	}
	
	public long getCartId() {
		return cartId;
	}
	
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	
	public long getProductId() {
		return productId;
	}
	
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public int getSalesQuantity() {
		return salesQuantity;
	}
	
	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}
	
	public double getSalesPrice() {
		return salesPrice;
	}
	
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}
	
	public float getTaxRate() {
		return taxRate;
	}
	
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	
	public double getLineAmount() {
		
		this.lineAmount=(this.salesPrice+this.salesPrice*this.taxRate)*this.salesQuantity;
		
		return lineAmount;
	}
	
	

	
	
	
}
