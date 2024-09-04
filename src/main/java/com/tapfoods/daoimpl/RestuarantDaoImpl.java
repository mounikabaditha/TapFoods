package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.ResturantDAO;
import com.tapfoods.dbutils.DbUtils;
import com.tapfoods.models.Restuarant;

public class RestuarantDaoImpl implements ResturantDAO {
	
	Connection con;
	Restuarant res;
	private Statement stmt;
	private ResultSet resultSet;
	private PreparedStatement pstmt;
	private static final String ADD_RESTAURANT = "insert into `restaurant`(`restaurant_name`, `delivery_time`, `cuisine_type`, `ratings`, `address`, `isactive`, `adminid`, `imgpath`) values(?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL = "select * from restaurant";
	private static final String SELECT_ON_ID = "select * from `restaurant` where `restaurantid`=?";

	private static final String UPDATE_ON_ID = "update `restaurant` set `restaurant_name`=?, `delivery_time`=?, `cuisine_type`=?, `ratings`=?, `address`=?, `isactive`=?, `adminid`=?, `imgpath`=? where `restaurantid`=?";

	private static final String DELETE_ON_ID = "delete from `restaurant` where `restaurantid` = ?";
	int status = 0;
	
	ArrayList<Restuarant> restuarantList = new ArrayList<Restuarant>();
	
	public RestuarantDaoImpl() {
		try {
			con = DbUtils.myConnect(); // Assign the connection to 'con'
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addResturant(Restuarant r) {
		try {
			pstmt = con.prepareStatement(ADD_RESTAURANT);
			
			pstmt.setString(1, r.getRestuarantname());
			pstmt.setInt(2, r.getDeliverytime());
			pstmt.setString(3, r.getCuisinetype());
			pstmt.setFloat(4, r.getRatings());
			pstmt.setString(5, r.getAddress());
			pstmt.setString(6, r.getIsactive());
			pstmt.setInt(7, r.getAdminid());
			pstmt.setString(8, r.getImgpath());
			status = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Restuarant> getAllResturants() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL);
			restuarantList = extractRestuarantFromList(resultSet);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return restuarantList;
	}

	@Override
	public Restuarant getRestuarant(int id) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_ID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			restuarantList = extractRestuarantFromList(resultSet);
			if (!restuarantList.isEmpty()) {
				res = restuarantList.get(0);	
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateRestuarant(Restuarant r) {
		try {
			pstmt = con.prepareStatement(UPDATE_ON_ID);
			pstmt.setString(1, r.getRestuarantname());
			pstmt.setInt(2, r.getDeliverytime());
			pstmt.setString(3, r.getCuisinetype());
			pstmt.setFloat(4, r.getRatings());
			pstmt.setString(5, r.getAddress());
			pstmt.setString(6, r.getIsactive());
			pstmt.setInt(7, r.getAdminid());
			pstmt.setString(8, r.getImgpath());
			pstmt.setInt(9, r.getRestuarantid());
			status = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteRestuarant(int id) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_ID);
			pstmt.setInt(1, id);
			status = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	ArrayList<Restuarant> extractRestuarantFromList(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				restuarantList.add(new Restuarant(
					resultSet.getInt("restaurantid"),        
					resultSet.getString("restaurant_name"),  
					resultSet.getInt("delivery_time"),       
					resultSet.getString("cuisine_type"),     
					resultSet.getFloat("ratings"),           
					resultSet.getString("address"),          
					resultSet.getString("isactive"),         
					resultSet.getInt("adminid"),             
					resultSet.getString("imgpath")          
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restuarantList;
	}
}
