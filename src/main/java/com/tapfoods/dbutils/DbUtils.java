package com.tapfoods.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class DbUtils {
	
	private static Connection con = null;
	private static String url="jdbc:mysql://localhost:3306/tapfoods";
	private static String username="root";
	private static String password="system@123";
	
	public static Connection myConnect()
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}

}
