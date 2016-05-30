package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mxbt.beans.Users;
import com.mxbt.utils.C3P0Utils;

public class SelectUser {

	public boolean selecUser(String uname){
		Connection connection=C3P0Utils.getConnection();
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		//查看users表中是否存在此用户
		String sql="select * from user where Uname=?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, uname);
			resultSet = statement.executeQuery();
			//存在此用户返回true
			if(resultSet.next()){
				return true;
			}else {
				//不存在此用户返回false
				return false;
			} 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			C3P0Utils.close(resultSet, statement, connection);
		}
		
	}
	//判断用户是否登录成功
	public Users loginUser(String uname,String upassword){
		Users user=new Users();
		Connection connection = C3P0Utils.getConnection();
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		PreparedStatement statement2=null;
		ResultSet resultSet2=null;
		//查看users表中是否存在此用户
		String sql="SELECT * FROM USER WHERE Uname=? AND Upassword=?;";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, uname);
			statement.setString(2, upassword);
			resultSet = statement.executeQuery();
			//存在此用户返回user
			if(resultSet.next()){
				user.setUid(resultSet.getInt("Uid"));
				user.setUname(resultSet.getString("Uname"));
				user.setUnickname(resultSet.getString("Unickname"));
				user.setUcountry(resultSet.getString("Ucountry"));
				user.setUsex(resultSet.getString("Usex"));
				user.setUsign(resultSet.getString("Usign"));
				user.setUpassword(resultSet.getString("Upassword"));
				statement2 = connection.prepareStatement("select Ipath from image where Iid = "+resultSet.getInt("Uhead"));
				resultSet2= statement2.executeQuery();
				if(resultSet2.next()){
					user.setUhead(resultSet2.getString("Ipath"));
				}
				statement2 = connection.prepareStatement("select Ipath from image where Iid = "+resultSet.getInt("Ubk"));
				resultSet2= statement2.executeQuery();
				if(resultSet2.next()){
					user.setUbk(resultSet2.getString("Ipath"));
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(resultSet, statement, connection);
		}
		return user;
		
	}
	public void addUser(String uname,String upassword){
		Connection connection = C3P0Utils.getConnection();
		PreparedStatement statement=null;
		//向表中加入该用户
		String sql="INSERT INTO USER(Uhead,Ubk,Uname,Unickname,Usex,Ucountry,Usign,Upassword) VALUES(?,?,?,?,?,?,?,?)";
		try {
			statement=connection.prepareStatement(sql);
			statement.setInt(1,1);
			statement.setInt(2,5);
			statement.setString(3,uname);
			statement.setString(4,"匿名");
			statement.setString(5,"女");
			statement.setString(6,"China");
			statement.setString(7,"时光如戏，岁月无音....");
			statement.setString(8,upassword);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(null, statement, connection);
		}
		
	}
}
