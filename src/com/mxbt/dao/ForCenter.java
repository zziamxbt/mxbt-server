package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.IndexBean;
import com.mxbt.utils.C3P0Utils;

public class ForCenter {

	
	private Connection connection = null;
	private PreparedStatement state = null;
	private ResultSet result = null;
	private PreparedStatement state2 = null;
	private ResultSet result2 = null;
	private PreparedStatement state3 = null;
	private ResultSet result3 = null;
	//我的故事分页
	public List<IndexBean> MyStory(int uid) {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " AWcreatetime ,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM chapter , andwrite , article "
					+ "WHERE AWauthor = " + uid + " AND AWcid = Cid And Caid = Aid "
					+ "ORDER BY AWcreatetime DESC";
			
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			
			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String content)

				// 根据Uid 查找头像id 和 用户昵称
				indexBean.setArticleId(result.getInt("Aid"));
				indexBean.setDateTime("" + result.getTimestamp("AWcreatetime"));
				indexBean.setTitle(result.getString("Atitle"));
				state2 = connection
						.prepareStatement("select Uhead, Unickname from user where Uid ="
								+ result.getInt("Aauthor"));
				result2 = state2.executeQuery();
				if (result2.next()) {

					indexBean.setNickName(result2.getString("Unickname"));
					// 根据头像id 查找 头像地址
					state3 = connection
							.prepareStatement("select Ipath from image where Iid = "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setHeadImg(result3.getString("Ipath"));
					}
				}
				// 根据 分类id 查找所属分类
				state2 = connection
						.prepareStatement("select Kind.content from Kind where Kid = "
								+ result.getInt("Akind"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(result2.getString(1));
				}

				// 根据 背景id 查找背景图片
				state2 = connection
						.prepareStatement("select Ipath from image where Iid = "
								+ result.getInt("Acoverimg"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setBackGround(result2.getString(1));
				}
				// 查找第一章的内容
				state2 = connection
						.prepareStatement("select ctid,chapter.isfinish from chapter where cnum=1 and caid =  "
								+ result.getInt("Aid"));

				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(indexBean.getKind() + "·"
							+ result2.getString(2));
					state3 = connection
							.prepareStatement("select Tpath from text where Tid =  "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setContent(result3.getString(1));
					}
				}
			
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

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
	
	
	
	
	public List<IndexBean> MyStory2(int uid) {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " Adatetime ,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM article "
					+ "WHERE Aauthor = " + uid
					+ " ORDER BY Adatetime DESC";
			
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			
			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String content)

				// 根据Uid 查找头像id 和 用户昵称
				indexBean.setArticleId(result.getInt("Aid"));
				indexBean.setDateTime("" + result.getTimestamp("Adatetime"));
				indexBean.setTitle(result.getString("Atitle"));
				state2 = connection
						.prepareStatement("select Uhead, Unickname from user where Uid ="
								+ result.getInt("Aauthor"));
				result2 = state2.executeQuery();
				if (result2.next()) {

					indexBean.setNickName(result2.getString("Unickname"));
					// 根据头像id 查找 头像地址
					state3 = connection
							.prepareStatement("select Ipath from image where Iid = "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setHeadImg(result3.getString("Ipath"));
					}
				}
				// 根据 分类id 查找所属分类
				state2 = connection
						.prepareStatement("select Kind.content from Kind where Kid = "
								+ result.getInt("Akind"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(result2.getString(1));
				}

				// 根据 背景id 查找背景图片
				state2 = connection
						.prepareStatement("select Ipath from image where Iid = "
								+ result.getInt("Acoverimg"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setBackGround(result2.getString(1));
				}
				// 查找第一章的内容
				state2 = connection
						.prepareStatement("select ctid,chapter.isfinish from chapter where cnum=1 and caid =  "
								+ result.getInt("Aid"));

				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(indexBean.getKind() + "·"
							+ result2.getString(2));
					state3 = connection
							.prepareStatement("select Tpath from text where Tid =  "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setContent(result3.getString(1));
					}
				}
			
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

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
	
	public List<IndexBean> MyCollect(int uid) {
		List<IndexBean> list = new ArrayList<IndexBean>();
		
		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " Cocreatetime ,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM collect , article "
					+ "WHERE COuid = " + uid + " And COaid = Aid "
					+ "ORDER BY COcreatetime DESC";
			
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			
			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String content)

				// 根据Uid 查找头像id 和 用户昵称
				indexBean.setArticleId(result.getInt("Aid"));
				indexBean.setDateTime("" + result.getTimestamp("COcreatetime"));
				indexBean.setTitle(result.getString("Atitle"));
				state2 = connection
						.prepareStatement("select Uhead, Unickname from user where Uid ="
								+ result.getInt("Aauthor"));
				result2 = state2.executeQuery();
				if (result2.next()) {

					indexBean.setNickName(result2.getString("Unickname"));
					// 根据头像id 查找 头像地址
					state3 = connection
							.prepareStatement("select Ipath from image where Iid = "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setHeadImg(result3.getString("Ipath"));
					}
				}
				// 根据 分类id 查找所属分类
				state2 = connection
						.prepareStatement("select Kind.content from Kind where Kid = "
								+ result.getInt("Akind"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(result2.getString(1));
				}

				// 根据 背景id 查找背景图片
				state2 = connection
						.prepareStatement("select Ipath from image where Iid = "
								+ result.getInt("Acoverimg"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setBackGround(result2.getString(1));
				}
				// 查找第一章的内容
				state2 = connection
						.prepareStatement("select ctid,chapter.isfinish from chapter where cnum=1 and caid =  "
								+ result.getInt("Aid"));

				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(indexBean.getKind() + "·"
							+ result2.getString(2));
					state3 = connection
							.prepareStatement("select Tpath from text where Tid =  "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setContent(result3.getString(1));
					}
				}
			
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

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
	
	
	
	
	
	
	//我的推荐分页
	public List<IndexBean> MyRecommend(int uid) {
		List<IndexBean> list = new ArrayList<IndexBean>();
		
		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " REcreatetime ,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM recommend , article "
					+ "WHERE REuid = " + uid + " And Reaid = Aid "
					+ "ORDER BY REcreatetime DESC";
			
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			
			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String content)

				// 根据Uid 查找头像id 和 用户昵称
				indexBean.setArticleId(result.getInt("Aid"));
				indexBean.setDateTime("" + result.getTimestamp("REcreatetime"));
				indexBean.setTitle(result.getString("Atitle"));
				state2 = connection
						.prepareStatement("select Uhead, Unickname from user where Uid ="
								+ result.getInt("Aauthor"));
				result2 = state2.executeQuery();
				if (result2.next()) {

					indexBean.setNickName(result2.getString("Unickname"));
					// 根据头像id 查找 头像地址
					state3 = connection
							.prepareStatement("select Ipath from image where Iid = "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setHeadImg(result3.getString("Ipath"));
					}
				}
				// 根据 分类id 查找所属分类
				state2 = connection
						.prepareStatement("select Kind.content from Kind where Kid = "
								+ result.getInt("Akind"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(result2.getString(1));
				}

				// 根据 背景id 查找背景图片
				state2 = connection
						.prepareStatement("select Ipath from image where Iid = "
								+ result.getInt("Acoverimg"));
				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setBackGround(result2.getString(1));
				}
				// 查找第一章的内容
				state2 = connection
						.prepareStatement("select ctid,chapter.isfinish from chapter where cnum=1 and caid =  "
								+ result.getInt("Aid"));

				result2 = state2.executeQuery();
				if (result2.next()) {
					indexBean.setKind(indexBean.getKind() + "·"
							+ result2.getString(2));
					state3 = connection
							.prepareStatement("select Tpath from text where Tid =  "
									+ result2.getInt(1));
					result3 = state3.executeQuery();
					if (result3.next()) {
						indexBean.setContent(result3.getString(1));
					}
				}
			
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

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
}
