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

public class ForCreate{

	
	private Connection connection = null;
	private PreparedStatement state = null;
	private ResultSet result = null;
	private PreparedStatement state2 = null;
	private ResultSet result2 = null;
	private PreparedStatement state3 = null;
	private ResultSet result3 = null;
	
	
	
	
	public int insertImg(String img) {
			int key=0;
			// 获取连接
			connection = C3P0Utils.getConnection();
			// 查询文章表
			String sql = "INSERT INTO Image(Ipath) VALUES(?)";
			
			try {
				state = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				state.setString(1,img);
				state.executeUpdate();
				ResultSet rsKey = state.getGeneratedKeys();      //ResultSet 指示键值  
				rsKey.next();  
				key = rsKey.getInt(1);     //得到第一个键值  
				rsKey.close();  
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
		
			return key;
	}
	
	
	
			public int insertText(String text) {
				int key=0;
				// 获取连接
				connection = C3P0Utils.getConnection();
				// 查询文章表
				String sql = "INSERT INTO Text(Tpath) VALUES(?)";
				
				try {
					state = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					state.setString(1,text);
					state.executeUpdate();
					ResultSet rsKey = state.getGeneratedKeys();      //ResultSet 指示键值  
					rsKey.next();  
					key = rsKey.getInt(1);     //得到第一个键值  
					rsKey.close();  
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
			
				return key;
		}
			
			
			
			
			public int insertArticle(int Aauthor , int Akind , int Acoverimg ,String Atitle , String Adatetime) {
				int key=0;
				// 获取连接
				connection = C3P0Utils.getConnection();
				// 查询文章表
				String sql = "INSERT INTO article(Aauthor,Akind,Acoverimg,Atitle,Adatetime) VALUES(?,?,?,?,?)";
				
				try {
					state = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					state.setInt(1,Aauthor);
					state.setInt(2,Akind);
					state.setInt(3,Acoverimg);
					state.setString(4,Atitle);
					state.setString(5,Adatetime);
					state.executeUpdate();
					ResultSet rsKey = state.getGeneratedKeys();      //ResultSet 指示键值  
					rsKey.next();  
					key = rsKey.getInt(1);     //得到第一个键值  
					rsKey.close();  
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
			
				return key;
		}
			
			
			
			
			
			
			
			
			public void insertChapter(int Cauthor , int Caid , int Ctid ,int Cnum , String Ctitle , String CCreatetime) {
			
				// 获取连接
				connection = C3P0Utils.getConnection();
				// 查询文章表
				String sql = "INSERT INTO chapter(Cauthor,Caid,Ctid,Cnum,Ctitle,CCreatetime,isfinish) VALUES(?,?,?,?,?,?,?)";
				String sql2 = "INSERT INTO chapter(Caid,Ctid,Cnum,CCreatetime,isfinish) VALUES(?,?,?,?,?)";
				try {
					state = connection.prepareStatement(sql);
					state.setInt(1,Cauthor);
					state.setInt(2,Caid);
					state.setInt(3,Ctid);
					state.setInt(4,Cnum);
					state.setString(5,Ctitle);
					state.setString(6,CCreatetime);
					state.setString(7,"连载中");
					state.executeUpdate();
					state2 = connection.prepareStatement(sql2);
					state2.setInt(1,Caid);
					state2.setInt(2,0);
					state2.setInt(3,Cnum+1);
					state2.setString(4,CCreatetime);
					state2.setString(5,"连载中");
					state2.executeUpdate();
					
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
							if(state2!=null){
								state2.close();
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			

		}
}
