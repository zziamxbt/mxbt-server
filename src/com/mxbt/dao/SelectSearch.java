package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import com.mxbt.beans.SearchBean;
import com.mxbt.utils.C3P0Utils;

public class SelectSearch {
	
	// 查询所有搜索
		public List<SearchBean> selectAllSearch() {
			List<SearchBean> mList = new ArrayList<SearchBean>();
			SearchBean search = null;
			Connection connection = C3P0Utils.getConnection();
			PreparedStatement statement = null;
			PreparedStatement statement2 = null;
			PreparedStatement statement3 = null;
			PreparedStatement statement4 = null;
			ResultSet resultSet = null;
			ResultSet resultSet2 = null;
			ResultSet resultSet3 = null;
			ResultSet resultSet4 = null;
			int Uid;// 作者id
			String Uhead;// 用户头像
			String Ubk;// 用户背景
			String Unickname;// 昵称
			String Uname;// 帐号
			String Usex;// 性别
			String Ucountry;// 国家
			String Usign;// 个性签名
			String Upassword;// 密码
			 int Aid;//文章id
		     String Atitle;//文章标题
			int headid;
			int bkid;

			try {
				String sql = "SELECT * FROM USER";
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					search = new SearchBean();
					Uid = resultSet.getInt("Uid");
					Unickname = resultSet.getString("Unickname");
					headid = resultSet.getInt("Uhead");
					bkid = resultSet.getInt("Ubk");
					Uname = resultSet.getString("Uname");
					Usex = resultSet.getString("Usex");
					Ucountry = resultSet.getString("Ucountry");
					Usign = resultSet.getString("Usign");
					Upassword = resultSet.getString("Upassword");

					search.setUid(Uid);// 1,id
					search.setUnickname(Unickname);// 2,nickname
					search.setUname(Uname);// 3,name
					search.setUsex(Usex);// 4,sex
					search.setUcountry(Ucountry);// 5,country
					search.setUsign(Usign);// 6,sign
					search.setUpassword(Upassword);// 7,password

					String sql2 = "SELECT Ipath FROM image WHERE Iid=" + headid;// 头像id对应的path
					statement2 = connection.prepareStatement(sql2);
					resultSet2 = statement2.executeQuery();
					while (resultSet2.next()) {
						Uhead = resultSet2.getString("Ipath");
						search.setUhead(Uhead);// 8,head
					}

					String sql3 = "SELECT Ipath FROM image WHERE Iid=" + bkid;// 背景id对应的path
					statement3 = connection.prepareStatement(sql3);
					resultSet3 = statement3.executeQuery();
					while (resultSet3.next()) {
						Ubk = resultSet3.getString("Ipath");
						search.setUbk(Ubk);// 9,bk
					}
					
					String sql4 = "SELECT Aid,Atitle FROM article WHERE Aauthor=" + Uid;// 文章id，title
					statement4 = connection.prepareStatement(sql4);
					resultSet4 = statement4.executeQuery();
					while (resultSet4.next()) {
						Aid=resultSet4.getInt("Aid");
						Atitle=resultSet4.getString("Atitle");
						search.setAid(Aid);
						search.setAtitle(Atitle);
					}

					mList.add(search);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				C3P0Utils.close(resultSet3, statement3, connection);
				C3P0Utils.close(resultSet2, statement2, connection);
				C3P0Utils.close(resultSet, statement, connection);
			}
			return mList;

		}
}
