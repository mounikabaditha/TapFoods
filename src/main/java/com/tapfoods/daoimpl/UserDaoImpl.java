package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.dbutils.DbUtils;
import com.tapfoods.models.User;

public class UserDaoImpl implements UserDAO {
	
	Connection con;
	User user;
	private Statement stmt;
	private ResultSet resultSet;
	private  PreparedStatement pstmt;
	private static final String ADD_USER= "insert into `user`(`username`,`email`,`password`,`address`,`phono`) values(?,?,?,?,?,)";
	private static final String SELECT_ALL="select *from user";
	private static final String SELECT_ON_EMAIL="select * from `user` where `email`=?";
	
	private static final String UPDATE_ON_EMAIL="update `user` set `username`=?,`password`=?,`address`=?,phono`=? where `email`=?";
	
	private static final String DELETE_ON_EMAIL="delete from `user` where `email` =?";
	int status=0;
	
	ArrayList<User> userList=new ArrayList<User>();
	
	public UserDaoImpl()
	{
		try {
			
			DbUtils.myConnect();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int addUser(User u) {
	
		try {
			pstmt =con.prepareStatement(ADD_USER);
			
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(4, u.getPhono());
			status= pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public ArrayList<User> getAllusers() {
		
		try {
			
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(SELECT_ALL);
			userList=extractUserFromUserList(resultSet);			
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		return userList;
	}

	@Override
	public User getUser(String email) {
		
		try {
			
			pstmt=con.prepareStatement(SELECT_ON_EMAIL);
			pstmt.setString(1, email);
			
			resultSet=pstmt.executeQuery();
			userList=extractUserFromUserList(resultSet);
			user = userList.get(0);	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
		
	}

	@Override
	public int updateUser(User u) {
		try {
			
			pstmt=con.prepareStatement(UPDATE_ON_EMAIL);

			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getAddress());
			pstmt.setString(4, u.getPhono());
			pstmt.setString(5, u.getEmail());
			status= pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteUser(String email) {
		try {
			
		
		pstmt=con.prepareStatement(DELETE_ON_EMAIL);
		pstmt.setString(1, email);
		status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	
	ArrayList<User> extractUserFromUserList(ResultSet resultSet)
	{
		try {
			while(resultSet.next())
			{
				userList.add(new User(resultSet.getInt("userid"),
				resultSet.getString("username"),
				resultSet.getString("email"),
				resultSet.getString("password"),
				resultSet.getString("address"),
				resultSet.getString("phono")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}

}
