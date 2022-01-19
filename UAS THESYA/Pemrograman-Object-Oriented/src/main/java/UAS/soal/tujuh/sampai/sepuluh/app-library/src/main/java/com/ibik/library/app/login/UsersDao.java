package com.ibik.library.app.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibik.library.app.connection.ConnectionDB;
import com.ibik.library.app.model.Users;

public class UsersDao {
	
	private String sqlLogin = "select * from user where NIK = ? and password = ?";
	
	public Users checkLogin(Users users) throws Exception{
		if(users == null) {
			return null;
		}		
		ConnectionDB conn = new ConnectionDB(); 
		Connection c = conn.connect();//open connection to db by class ConnectionDB
		PreparedStatement pst = c.prepareStatement(sqlLogin);
		pst.setLong(1, users.getNIK()); //entering parameter NIK to index 1
		pst.setString(2, users.getPassword()); //entering parameter Password to index 2
		ResultSet rs = pst.executeQuery(); //execute query base on var sqlLogin
		if(!rs.next()) { //check if there is no result from database
			return null; //will return null
		}
		//if it has result from query it will put the result to class encapsulation class Users
		Users result = new Users();
		result.setID(rs.getInt("ID"));
		result.setNIK(rs.getInt("NIK"));
		result.setFullname(rs.getString("Fullname"));
		result.setPlaceBirth(rs.getString("PlaceBirth"));
		result.setBirthdate(rs.getString("Birthdate"));
		result.setGender(rs.getString("Gender"));
		result.setAddress(rs.getString("Address"));
		result.setEmail(rs.getString("Email"));
		result.setPassword(rs.getString("Password"));
		
		c.close(); //closing connection to db
		return result; //return class Login value		
	}	
	
	public Users currentUser(ResultSet rs) throws SQLException {
		Users result = new Users();
		result.setID(rs.getInt("ID"));
		result.setNIK(rs.getInt("NIK"));
		result.setFullname(rs.getString("Fullname"));
		result.setPlaceBirth(rs.getString("PlaceBirth"));
		result.setBirthdate(rs.getString("Birthdate"));
		result.setGender(rs.getString("Gender"));
		result.setAddress(rs.getString("Address"));
		result.setEmail(rs.getString("Email"));
		result.setPassword(rs.getString("Password"));
		return result;
	}
}

