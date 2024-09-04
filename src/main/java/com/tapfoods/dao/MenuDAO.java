package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.models.Menu;
import com.tapfoods.models.User;

public interface MenuDAO {
	
int addMenu(Menu m);
	
	ArrayList<Menu> getAllMenu();
	
	//Menu getMenu(int restaurantid);
	
	public ArrayList<Menu> getMenu(int restaurantid) ;
	
	int updateMenu(Menu m);
	
	int deleteMenu(String menu_name);

	
}
