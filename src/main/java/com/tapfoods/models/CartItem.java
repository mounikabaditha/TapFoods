package com.tapfoods.models;

public class CartItem {
	
	private int itemid;
	private int restuarantid;
	private String name;
	private float price;
	private int quantity;
	private float subtotal;
	public CartItem() {
		super();
	}
	public CartItem(int itemid, int restuarantid, String name, float price, int quantity, float subtotal) {
		super();
		this.itemid = itemid;
		this.restuarantid = restuarantid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getRestuarantid() {
		return restuarantid;
	}
	public void setRestuarantid(int restuarantid) {
		this.restuarantid = restuarantid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "CartItem [itemid=" + itemid + ", restuarantid=" + restuarantid + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
	
	
	

}
