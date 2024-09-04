package com.tapfoods.models;

public class Restuarant {

	
	private int restuarantid;
	private String restuarantname;
	private int deliverytime;
	private String cuisinetype;
	private float ratings;
	private String address;
	private String isactive;
	private int adminid;
	private String imgpath;
	
	public Restuarant() {
		
	}
	
	public Restuarant(int restuarantid, String restuarantname, int deliverytime, String cuisinetype, float ratings,
			String address, String isactive, int adminid, String imgpath) {
		super();
		this.restuarantid = restuarantid;
		this.restuarantname = restuarantname;
		this.deliverytime = deliverytime;
		this.cuisinetype = cuisinetype;
		this.ratings = ratings;
		this.address = address;
		this.isactive = isactive;
		this.adminid = adminid;
		this.imgpath = imgpath;
	}

	public Restuarant(String restuarantname, int deliverytime, String cuisinetype, float ratings, String address,
			String isactive, int adminid, String imgpath) {
		super();
		this.restuarantname = restuarantname;
		this.deliverytime = deliverytime;
		this.cuisinetype = cuisinetype;
		this.ratings = ratings;
		this.address = address;
		this.isactive = isactive;
		this.adminid = adminid;
		this.imgpath = imgpath;
	}

	public int getRestuarantid() {
		return restuarantid;
	}

	public void setRestuarantid(int restuarantid) {
		this.restuarantid = restuarantid;
	}

	public String getRestuarantname() {
		return restuarantname;
	}

	public void setRestuarantname(String restuarantname) {
		this.restuarantname = restuarantname;
	}

	public int getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(int deliverytime) {
		this.deliverytime = deliverytime;
	}

	public String getCuisinetype() {
		return cuisinetype;
	}

	public void setCuisinetype(String cuisinetype) {
		this.cuisinetype = cuisinetype;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public int getAdminid() {
		return adminid;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Override
	public String toString() {
		return "Restuarant [restuarantid=" + restuarantid + ", restuarantname=" + restuarantname + ", deliverytime="
				+ deliverytime + ", cuisinetype=" + cuisinetype + ", ratings=" + ratings + ", address=" + address
				+ ", isactive=" + isactive + ", adminid=" + adminid + ", imgpath=" + imgpath + "]";
	}
	
	
	
	
}
