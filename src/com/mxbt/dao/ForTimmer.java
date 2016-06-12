package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.AndWriteBean;
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
			}finally {

				try {
					if(result!=null)
					result.close();
					if(result2!=null)
					result2.close();
					if(result3!=null)
					result3.close();
					if(state!=null)
					state.close();
					if(state2!=null)
					state2.close();
					if(state3!=null)
					state3.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
	
			
			return list;
	}
	
	
	public List<String> getChapterTime() {
		List<String> list = new ArrayList<String>();
			
			try {
				// 获取连接
				connection = C3P0Utils.getConnection();

				// 查询文章表
				String sql = "SELECT Ccreatetime FROM chapter WHERE ctid=0";
				state = connection.prepareStatement(sql);
				result = state.executeQuery();
				while(result.next()){
					list.add(result.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {

				try {
					if(result!=null)
					result.close();
					if(result2!=null)
					result2.close();
					if(result3!=null)
					result3.close();
					if(state!=null)
					state.close();
					if(state2!=null)
					state2.close();
					if(state3!=null)
					state3.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
	
			
			return list;
	}
	
	
	
	
	
	public List<Integer> getChapterNum() {
		List<Integer> list = new ArrayList<Integer>();
			
			try {
				// 获取连接
				connection = C3P0Utils.getConnection();

				// 查询文章表
				String sql = "SELECT Cnum FROM chapter WHERE ctid=0";
				state = connection.prepareStatement(sql);
				result = state.executeQuery();
				while(result.next()){
					list.add(result.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {

				try {
					if(result!=null)
					result.close();
					if(result2!=null)
					result2.close();
					if(result3!=null)
					result3.close();
					if(state!=null)
					state.close();
					if(state2!=null)
					state2.close();
					if(state3!=null)
					state3.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
	
			
			return list;
	}
	
	
	public List<Integer> getChapterAid() {
		List<Integer> list = new ArrayList<Integer>();
			
			try {
				// 获取连接
				connection = C3P0Utils.getConnection();

				// 查询文章表
				String sql = "SELECT Caid FROM chapter WHERE ctid=0";
				state = connection.prepareStatement(sql);
				result = state.executeQuery();
				while(result.next()){
					list.add(result.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {

				try {
					if(result!=null)
					result.close();
					if(result2!=null)
					result2.close();
					if(result3!=null)
					result3.close();
					if(state!=null)
					state.close();
					if(state2!=null)
					state2.close();
					if(state3!=null)
					state3.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
	
			
			return list;
	}
	
	
	public AndWriteBean getAndWrite(int cid) {
		AndWriteBean ab = null;
			
			try {
				// 获取连接
				connection = C3P0Utils.getConnection();

				// 查询文章表
				
				String sql = "SELECT * FROM andwrite WHERE AWcid= "+cid +" ORDER BY AWvote  DESC ,AWcreatetime DESC LIMIT 1";
				state = connection.prepareStatement(sql);
				result = state.executeQuery();
					while(result.next()){
						ab=new AndWriteBean(result.getInt("AWid"), result.getInt("AWcid"), result.getInt("AWtid"), result.getInt("AWauthor"), result.getString("AWtitle"), result.getInt("AWvote"), result.getString("AWcreatetime"), result.getString("AWisfinish"));
					}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {

				try {
					if(result!=null)
					result.close();
					if(result2!=null)
					result2.close();
					if(result3!=null)
					result3.close();
					if(state!=null)
					state.close();
					if(state2!=null)
					state2.close();
					if(state3!=null)
					state3.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
	
			
			return ab;
	}
	
	
	
	
	
	
	public void updateChapter(int cid , AndWriteBean awb,int cnum , String nowtime,int Caid) {
		
			
			try {
				// 获取连接
				connection = C3P0Utils.getConnection();

				// 查询文章表
				
				String sql = "update chapter set Cauthor=? , ctid = ? , ctitle = ? ,isfinish = ?  where Cid=?";
				state = connection.prepareStatement(sql);
				state.setInt(1,awb.getAWauthor());
				state.setInt(2,awb.getAWtid());
				state.setString(3, awb.getAWtitle());
				state.setString(4,awb.getAWisfinish());
				state.setInt(5, cid);
				state.executeUpdate();
				
				
				String sql2 = "INSERT INTO chapter(Caid,Ctid,Cnum,CCreatetime,isfinish) VALUES(?,?,?,?,?)";
				state2 = connection.prepareStatement(sql2);
				state2.setInt(1, Caid);
				state2.setInt(2, 0);
				state2.setInt(3, cnum+1);
				state2.setString(4,nowtime);
				state2.setString(5,"");
				state2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {

				try {
					if(result!=null)
					result.close();
					if(result2!=null)
					result2.close();
					if(result3!=null)
					result3.close();
					if(state!=null)
					state.close();
					if(state2!=null)
					state2.close();
					if(state3!=null)
					state3.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
	
			
			
	}
	
	
	
	
}
