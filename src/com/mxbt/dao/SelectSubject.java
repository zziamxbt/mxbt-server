package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.SubjectBean;
import com.mxbt.utils.C3P0Utils;

public class SelectSubject {

	//查询专题表中图片对应的路径，返回路径集合
	public List<SubjectBean> selectAllSubject(){
		List<SubjectBean> mList=new ArrayList<SubjectBean>();
		SubjectBean subject=null;
		Connection connection = C3P0Utils.getConnection();
		PreparedStatement statement=null;
		PreparedStatement statement2=null;
		ResultSet resultSet=null;
		ResultSet resultSet2=null;
		int SIid;//专题图片id
		String imagePath;//专题图片id对应的图片路径	
		String Scontent;//专题内容
		try {
			String sql="SELECT SIid,Scontent FROM SUBJECT";//专题表所有的图片id
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				subject=new SubjectBean();
				SIid=resultSet.getInt("SIid");
				Scontent=resultSet.getString("Scontent");
				subject.setSIid(SIid);
				subject.setScontent(Scontent);
				String sql2="SELECT Ipath FROM image WHERE Iid="+SIid;//图片id对应的path
				statement2=connection.prepareStatement(sql2);
				resultSet2=statement2.executeQuery();
				while(resultSet2.next()){
					imagePath=resultSet2.getString("Ipath");
					subject.setPath(imagePath);
					mList.add(subject);
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
