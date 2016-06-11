package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.IndexBean;
import com.mxbt.beans.Users;
import com.mxbt.utils.C3P0Utils;
import com.mysql.jdbc.Statement;

public class ForEdit {

	
	private Connection connection = null;
	private PreparedStatement state = null;
	private ResultSet result = null;
	private PreparedStatement state2 = null;
	private ResultSet result2 = null;
	private PreparedStatement state3 = null;
	private ResultSet result3 = null;
	//我的故事分页
	public List<Integer> editImg(Users user) {
		List<Integer> list = new ArrayList<Integer>();
			// 获取连接
			connection = C3P0Utils.getConnection();
			// 查询文章表
			String sql = "INSERT INTO Image(Ipath) VALUES(?)";
			String sql2 = "INSERT INTO Image(Ipath) VALUES(?)";
			try {
				state = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				state.setString(1,user.getUbk());
				state.executeUpdate();
				ResultSet rsKey = state.getGeneratedKeys();      //ResultSet 指示键值  
				rsKey.next();  
				int key = rsKey.getInt(1);     //得到第一个键值  
				list.add(key);
				rsKey.close();  
				state = connection.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
				state.setString(1, user.getUhead());
				state.executeUpdate();
				ResultSet rsKey2 = state.getGeneratedKeys();      //ResultSet 指示键值  
				rsKey2.next();  
				int key2 = rsKey2.getInt(1);     //得到第一个键值  
				list.add(key2);
				rsKey2.close();  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
					try {
						if(connection!=null){
						connection.close();
						}
						if(state!=null){
							state.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
			return list;
	}
	
	
	
	
	
	
	
	
	public void editUser(Users user,int uhead , int ubk ) {
		List<Integer> list = new ArrayList<Integer>();
			// 获取连接
			connection = C3P0Utils.getConnection();
			// 查询文章表
			String sql = "update User set Uhead=? , Ubk=? , Uname=? , Unickname=? , Usex=? , Ucountry=? , Usign = ? where Uid="
							+ user.getUid();
			try {
				state = connection.prepareStatement(sql);
				state.setInt(1,uhead);
				state.setInt(2,ubk);
				state.setString(3,user.getUname());
				state.setString(4,user.getUnickname());
				state.setString(5,user.getUsex());
				state.setString(6,user.getUcountry());
				state.setString(7,user.getUsign());
				state.executeUpdate();
				  
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
					try {
						if(connection!=null){
						connection.close();
						}
						if(state!=null){
							state.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
		
	}
}
