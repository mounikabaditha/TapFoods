package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.models.Restuarant;


public interface ResturantDAO {
	

	int addResturant(Restuarant r);
	
	ArrayList<Restuarant> getAllResturants();
	
	Restuarant getRestuarant(int id);
	
	int updateRestuarant(Restuarant r);
	
	int deleteRestuarant(int id);

}
