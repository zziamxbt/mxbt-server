package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.IndexBean;
import com.mxbt.utils.C3P0Utils;

public class ForTimmer {

	private Connection connection = null;
	private PreparedStatement state = null;
	private ResultSet result = null;
	private PreparedStatement state2 = null;
	private ResultSet result2 = null;
	private PreparedStatement state3 = null;
	private ResultSet result3 = null;
	public List<Integer> getChapter() {
		List<Integer> list = new ArrayList<Integer>();
			
			try {
				// 获取连接
				connection = C3P0Utils.getConnection();

				// 查询文章表
				String sql = "SELECT Cid FROM chapter WHERE ctid=0";
				state = connection.prepareStatement(sql);
				result = state.executeQuery();
				while(result.next()){
					list.add(result.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			
			return list;
	}
	
	
	
	
	public List<Integer> getAndWrite(List<Integer> cidList) {
		List<Integer> list = new ArrayList<Integer>();
			
			try {
				// 获取连接
				connection = C3P0Utils.getConnection();

				// 查询文章表
				String sql = "SELECT Cid FROM chapter WHERE ctid=0";
				state = connection.prepareStatement(sql);
				result = state.executeQuery();
				while(result.next()){
					list.add(result.getInt(1));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			
			return list;
	}
}
