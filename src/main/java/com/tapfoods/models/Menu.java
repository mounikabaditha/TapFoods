package com.tapfoods.models;

public class Menu {
    private int menuId;
    private int restaurantId;
    private String menuName;
    private float price;
    private String itemDescription;
    private String imgPath;

    // Default constructor
    public Menu() {
    }

    // Parameterized constructor
    public Menu(int menuId, int restaurantId, String menuName, float price, String itemDescription, String imgPath) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.menuName = menuName;
        this.price = price;
        this.itemDescription = itemDescription;
        this.imgPath = imgPath;
    }
    

    public Menu(int restaurantId, String menuName, float price, String itemDescription, String imgPath) {
		super();
		this.restaurantId = restaurantId;
		this.menuName = menuName;
		this.price = price;
		this.itemDescription = itemDescription;
		this.imgPath = imgPath;
	}

	// Getter and Setter methods
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    // toString method
    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                ", itemDescription='" + itemDescription + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

	
}

