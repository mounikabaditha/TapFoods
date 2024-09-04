package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.dbutils.DbUtils;
import com.tapfoods.models.Menu;

public class MenuDaoImpl implements MenuDAO {
    
    Connection con;
    Menu menu;
    private Statement stmt;
    private ResultSet resultSet;
    private PreparedStatement pstmt;
    private static final String ADD_MENU = "insert into `menu`(`restaurantid`, `menu_name`, `price`, `item_description`, `imgpath`) values(?,?,?,?,?)";
    private static final String SELECT_ALL = "select * from menu";
    private static final String SELECT_ON_NAME = "select * from `menu` where `restaurantid`=?";
    private static final String UPDATE_ON_NAME = "update `menu` set `restaurantid`=?, `price`=?, `item_description`=?, `imgpath`=? where `menu_name`=?";
    private static final String DELETE_ON_NAME = "delete from `menu` where `menu_name` = ?";
    int status = 0;
    
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    
    public MenuDaoImpl() {
        try {
           con= DbUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int addMenu(Menu m) {
        try {
            pstmt = con.prepareStatement(ADD_MENU);
            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getMenuName());
            pstmt.setFloat(3, m.getPrice());
            pstmt.setString(4, m.getItemDescription());
            pstmt.setString(5, m.getImgPath());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    @Override
    public ArrayList<Menu> getAllMenu() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            menuList = extractMenuFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }
    
   
    @Override
    public ArrayList<Menu> getMenu(int restaurantid) {
        ArrayList<Menu> menuList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(SELECT_ON_NAME);
            pstmt.setInt(1, restaurantid);
            resultSet = pstmt.executeQuery();
            menuList = extractMenuFromResultSet(resultSet);  // This returns the list of menus
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

        @Override
    public int updateMenu(Menu m) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_NAME);
            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setFloat(2, m.getPrice());
            pstmt.setString(3, m.getItemDescription());
            pstmt.setString(4, m.getImgPath());
            pstmt.setString(5, m.getMenuName());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    @Override
    public int deleteMenu(String menuName) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_NAME);
            pstmt.setString(1, menuName);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    ArrayList<Menu> extractMenuFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                menuList.add(new Menu(
                    resultSet.getInt("menuid"),
                    resultSet.getInt("restaurantid"),
                    resultSet.getString("menu_name"),
                    resultSet.getFloat("price"),
                    resultSet.getString("item_description"),
                    resultSet.getString("imgpath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}

