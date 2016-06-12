package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mxbt.beans.rank_novel;
import com.mxbt.utils.C3P0Utils;

public class rank_excute2 {
	private Connection connection;
	private PreparedStatement state;
	private ResultSet result;
	private PreparedStatement state2;
	private ResultSet result2;
	private PreparedStatement state3;
	private ResultSet result3;
	private PreparedStatement state4;
	private ResultSet result4;
	private PreparedStatement sta;
	private ResultSet res;
	private PreparedStatement sta2;
	private ResultSet res2;
	int RECaid;
	int Aid;
	int Caid;
	int AWcid;

	public List<rank_novel> getweekbest() {
		List<rank_novel> list = new ArrayList<rank_novel>();
		rank_novel novel = null;

		try {
			connection = C3P0Utils.getConnection();
			String sql = "SELECT RECaid, COUNT(*) FROM recentread WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(RECcreatetime) GROUP BY RECaid  ORDER BY COUNT(*) DESC ";
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			while (result.next()) {
				novel = new rank_novel();
				RECaid = result.getInt("RECaid");
				novel.setAid(RECaid);
				String sql2 = "select * from article where Aid=" + RECaid;
				state2 = connection.prepareStatement(sql2);
				result2 = state2.executeQuery();
				while (result2.next()) {
					Aid = result2.getInt("Aid");
					novel.setTitle(result2.getString("Atitle"));
					sta = connection
							.prepareStatement("select Ipath from image where Iid ="
									+ result2.getInt("Acoverimg"));
					res = sta.executeQuery();
					while (res.next()) {
						novel.setCoverimg(res.getString("Ipath"));
					}
					String sql3 = "select * from user where Uid=" + Aid;
					state3 = connection.prepareStatement(sql3);
					result3 = state3.executeQuery();
					while (result3.next()) {

						novel.setNickname(result3.getString("Unickname"));
						sta2 = connection
								.prepareStatement("select Ipath from image where Iid="
										+ result3.getInt("Uhead"));
						res2 = sta2.executeQuery();
						while (res2.next()) {
							novel.setHead(res2.getString("Ipath"));
						}
					}
					String sql4 = "select * from chapter where caid=" + Aid;
					state4 = connection.prepareStatement(sql4);
					result4 = state4.executeQuery();
					while (result4.next()) {

						novel.setUpdatetime(result4.getString("Ccreatetime"));
						novel.setIsfinish(result4.getString("isfinish"));

					}
				}
				list.add(novel);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
			C3P0Utils.close(result3, state3, connection);
			C3P0Utils.close(result4, state4, connection);
			C3P0Utils.close(res, sta, connection);
			C3P0Utils.close(res2, sta2, connection);
		}
		return list;

	}

	public List<rank_novel> getbest() {
		List<rank_novel> list = new ArrayList<rank_novel>();
		rank_novel novel = null;

		try {
			connection = C3P0Utils.getConnection();
			String sql = "SELECT RECaid, COUNT(*) FROM recentread GROUP BY RECaid ORDER BY COUNT(*) DESC ";
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			while (result.next()) {
				novel = new rank_novel();
				RECaid = result.getInt("RECaid");
				String sql2 = "select * from article where Aid=" + RECaid;
				state2 = connection.prepareStatement(sql2);
				result2 = state2.executeQuery();
				while (result2.next()) {
					Aid = result2.getInt("Aid");
					novel.setAid(Aid);
					novel.setTitle(result2.getString("Atitle"));
					sta = connection
							.prepareStatement("select Ipath from image where Iid ="
									+ result2.getInt("Acoverimg"));
					res = sta.executeQuery();
					while (res.next()) {
						novel.setCoverimg(res.getString("Ipath"));
					}
					String sql3 = "select * from user where Uid=" + Aid;
					state3 = connection.prepareStatement(sql3);
					result3 = state3.executeQuery();
					while (result3.next()) {
						novel.setNickname(result3.getString("Unickname"));
						sta2 = connection
								.prepareStatement("select Ipath from image where Iid="
										+ result3.getInt("Uhead"));
						res2 = sta2.executeQuery();
						while (res2.next()) {
							novel.setHead(res2.getString("Ipath"));
						}
					}
					String sql4 = "select * from chapter where caid=" + Aid;
					state4 = connection.prepareStatement(sql4);
					result4 = state4.executeQuery();
					while (result4.next()) {
						novel.setUpdatetime(result4.getString("Ccreatetime"));
						novel.setIsfinish(result4.getString("isfinish"));
					}
				}
				list.add(novel);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
			C3P0Utils.close(result3, state3, connection);
			C3P0Utils.close(result4, state4, connection);
			C3P0Utils.close(res, sta, connection);
			C3P0Utils.close(res2, sta2, connection);
		}
		return list;
	}

	// 续写者最多
	public List<rank_novel> getwriterbest() {
		List<rank_novel> list = new ArrayList<rank_novel>();
		rank_novel novel = null;

		try {
			connection = C3P0Utils.getConnection();
			String sql = "SELECT AWcid,COUNT(*) FROM andwrite GROUP BY AWcid ORDER BY COUNT(*) DESC";
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			while (result.next()) {
				novel = new rank_novel();
				AWcid = result.getInt("AWcid");
				String sql2 = "select * from chapter where Cid=" + AWcid;
				state2 = connection.prepareStatement(sql2);
				result2 = state2.executeQuery();
				while (result2.next()) {
					if(Caid==result2.getInt("Caid")){
						continue;
					}else{
						Caid = result2.getInt("Caid");
						novel.setUpdatetime(result2.getString("Ccreatetime"));
						novel.setIsfinish(result2.getString("isfinish"));
					
						}
						String sql4 = "select * from article where Aid=" + Caid;
						state4 = connection.prepareStatement(sql4);
						result4 = state4.executeQuery();
						while (result4.next()) {
							novel.setAid(result4.getInt("Aid"));
							novel.setTitle(result4.getString("Atitle"));
							
							String sql3 = "select * from user where Uid="
									+ result4.getInt("Aauthor");
							state3 = connection.prepareStatement(sql3);
							result3 = state3.executeQuery();
							while (result3.next()) {
								novel.setNickname(result3.getString("Unickname"));
								sta2 = connection
										.prepareStatement("select Ipath from image where Iid="
												+ result3.getInt("Uhead"));
								res2 = sta2.executeQuery();
								while (res2.next()) {
									novel.setHead(res2.getString("Ipath"));
								}
								
							sta = connection.prepareStatement("select Ipath from image where Iid ="+ result4.getInt("Acoverimg"));
							res = sta.executeQuery();
						
							while (res.next()) {
								novel.setCoverimg(res.getString("Ipath"));
							}
						}
					}
					
				}
				list.add(novel);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
			C3P0Utils.close(result3, state3, connection);
			C3P0Utils.close(result4, state4, connection);
			C3P0Utils.close(res, sta, connection);
			C3P0Utils.close(res2, sta2, connection);
		}
		return list;
	}
}
