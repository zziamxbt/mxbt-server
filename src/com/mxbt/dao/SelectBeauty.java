package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.BeautyBean;
import com.mxbt.utils.C3P0Utils;

public class SelectBeauty {

	// 查询部分最美故事
	public List<BeautyBean> selectPartBeauty() {
		List<BeautyBean> mList = new ArrayList<BeautyBean>();
		BeautyBean beauty = null;
		Connection connection = C3P0Utils.getConnection();
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		ResultSet resultSet4 = null;
		int Aid;// 文章id
		String Acoverimg;// 文章封面
		String Atitle;// 文章标题
		String Uhead;// 作者头像
		String Unickname;// 作者昵称
		int coverid;
		int Uid;
		int headid;

		try {
			String sql = "SELECT * FROM article WHERE Aid IN(1,2,3,4)";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				beauty = new BeautyBean();
				Aid = resultSet.getInt("Aid");
				Uid = resultSet.getInt("Aauthor");
				coverid = resultSet.getInt("Acoverimg");
				Atitle = resultSet.getString("Atitle");

				beauty.setAid(Aid);// 1,aid
				beauty.setAtitle(Atitle);// 2,atitle

				String sql2 = "SELECT Uhead,Unickname FROM USER WHERE Uid ="
						+ Uid;// 头像id对应的path
				statement2 = connection.prepareStatement(sql2);
				resultSet2 = statement2.executeQuery();
				while (resultSet2.next()) {
					headid = resultSet2.getInt("Uhead");
					Unickname = resultSet2.getString("Unickname");

					beauty.setUnickname(Unickname);// 3,nickname

					String sql3 = "SELECT Ipath FROM image WHERE Iid="
							+ coverid;// 文章封面id对应的path
					statement3 = connection.prepareStatement(sql3);
					resultSet3 = statement3.executeQuery();
					while (resultSet3.next()) {
						Acoverimg = resultSet3.getString("Ipath");
						beauty.setAcoverimg(Acoverimg);// 4,coverimg
					}

					String sql4 = "SELECT Ipath FROM image WHERE Iid=" + headid;// headid对应的path
					statement4 = connection.prepareStatement(sql4);
					resultSet4 = statement4.executeQuery();
					while (resultSet4.next()) {
						Uhead = resultSet4.getString("Ipath");
						beauty.setUhead(Uhead);// 5,head
					}

				}

				mList.add(beauty);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close(resultSet4, statement4, connection);
			C3P0Utils.close(resultSet3, statement3, connection);
			C3P0Utils.close(resultSet2, statement2, connection);
			C3P0Utils.close(resultSet, statement, connection);
		}
		return mList;

	}
	
	
	
	// 查询部分最美故事
		public List<BeautyBean> selectAllBeauty() {
			List<BeautyBean> mList = new ArrayList<BeautyBean>();
			BeautyBean beauty = null;
			Connection connection = C3P0Utils.getConnection();
			PreparedStatement statement = null;
			PreparedStatement statement2 = null;
			PreparedStatement statement3 = null;
			PreparedStatement statement4 = null;
			ResultSet resultSet = null;
			ResultSet resultSet2 = null;
			ResultSet resultSet3 = null;
			ResultSet resultSet4 = null;
			int Aid;// 文章id
			String Acoverimg;// 文章封面
			String Atitle;// 文章标题
			String Uhead;// 作者头像
			String Unickname;// 作者昵称
			int coverid;
			int Uid;
			int headid;

			try {
				String sql = "SELECT * FROM article";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					beauty = new BeautyBean();
					Aid = resultSet.getInt("Aid");
					Uid = resultSet.getInt("Aauthor");
					coverid = resultSet.getInt("Acoverimg");
					Atitle = resultSet.getString("Atitle");

					beauty.setAid(Aid);// 1,aid
					beauty.setAtitle(Atitle);// 2,atitle

					String sql2 = "SELECT Uhead,Unickname FROM USER WHERE Uid ="
							+ Uid;// 头像id对应的path
					statement2 = connection.prepareStatement(sql2);
					resultSet2 = statement2.executeQuery();
					while (resultSet2.next()) {
						headid = resultSet2.getInt("Uhead");
						Unickname = resultSet2.getString("Unickname");

						beauty.setUnickname(Unickname);// 3,nickname

						String sql3 = "SELECT Ipath FROM image WHERE Iid="
								+ coverid;// 文章封面id对应的path
						statement3 = connection.prepareStatement(sql3);
						resultSet3 = statement3.executeQuery();
						while (resultSet3.next()) {
							Acoverimg = resultSet3.getString("Ipath");
							beauty.setAcoverimg(Acoverimg);// 4,coverimg
						}

						String sql4 = "SELECT Ipath FROM image WHERE Iid=" + headid;// headid对应的path
						statement4 = connection.prepareStatement(sql4);
						resultSet4 = statement4.executeQuery();
						while (resultSet4.next()) {
							Uhead = resultSet4.getString("Ipath");
							beauty.setUhead(Uhead);// 5,head
						}

					}

					mList.add(beauty);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				C3P0Utils.close(resultSet4, statement4, connection);
				C3P0Utils.close(resultSet3, statement3, connection);
				C3P0Utils.close(resultSet2, statement2, connection);
				C3P0Utils.close(resultSet, statement, connection);
			}
			return mList;

		}

}
