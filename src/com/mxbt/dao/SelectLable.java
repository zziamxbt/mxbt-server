package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.LableBean;
import com.mxbt.utils.C3P0Utils;

public class SelectLable {
	
	//查询标签表所有的数据
		public List<LableBean> selectAllLable(){
			List<LableBean> mList=new ArrayList<LableBean>();
			LableBean lable=null;
			Connection connection = C3P0Utils.getConnection();
			PreparedStatement statement=null;
			PreparedStatement statement2=null;
			ResultSet resultSet=null;
			ResultSet resultSet2=null;
			int Lid;//标签id
			int LIid;//标签图片id
			String imagePath;//标签图片id对应的图片路径
			String Lcontent;//标签内容
			try {
				String sql="SELECT * FROM lable";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while(resultSet.next()){
					lable=new LableBean();
					Lid=resultSet.getInt("Lid");
					LIid=resultSet.getInt("LIid");
					Lcontent=resultSet.getString("Lcontent");
					lable.setLid(Lid);
					lable.setLIid(LIid);
					lable.setLcontent(Lcontent);
					
					String sql2="SELECT Ipath FROM image WHERE Iid="+LIid;//图片id对应的path
					statement2=connection.prepareStatement(sql2);
					resultSet2=statement2.executeQuery();
					while(resultSet2.next()){
						imagePath=resultSet2.getString("Ipath");
						lable.setPath(imagePath);
						mList.add(lable);
					}	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				C3P0Utils.close(resultSet, statement, connection);
			}
			return mList;
			
		}
		
		
		
		//查询标签表部分的数据
				public List<LableBean> selectPartLable(){
					List<LableBean> mList=new ArrayList<LableBean>();
					LableBean lable=null;
					Connection connection = C3P0Utils.getConnection();
					PreparedStatement statement=null;
					PreparedStatement statement2=null;
					ResultSet resultSet=null;
					ResultSet resultSet2=null;
					int Lid;//标签id
					int LIid;//标签图片id
					String imagePath;//标签图片id对应的图片路径	
					String Lcontent;//标签内容
					try {
						String sql="SELECT * FROM lable WHERE Lid IN (1,2,3,4,5,6,7)";
						statement = connection.prepareStatement(sql);
						resultSet = statement.executeQuery();
						while(resultSet.next()){
							lable=new LableBean();
							Lid=resultSet.getInt("Lid");
							LIid=resultSet.getInt("LIid");
							Lcontent=resultSet.getString("Lcontent");
							lable.setLid(Lid);
							lable.setLIid(LIid);
							lable.setLcontent(Lcontent);
							
							String sql2="SELECT Ipath FROM image WHERE Iid="+LIid;//图片id对应的path
							statement2=connection.prepareStatement(sql2);
							resultSet2=statement2.executeQuery();
							while(resultSet2.next()){
								imagePath=resultSet2.getString("Ipath");
								lable.setPath(imagePath);
								mList.add(lable);
							}	
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						C3P0Utils.close(resultSet, statement, connection);
					}
					return mList;
					
				}
				

}
