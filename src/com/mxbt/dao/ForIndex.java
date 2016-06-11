package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.IndexBean;
import com.mxbt.utils.C3P0Utils;

public class ForIndex {
	private Connection connection = null;
	private PreparedStatement state = null;
	private ResultSet result = null;
	private PreparedStatement state2 = null;
	private ResultSet result2 = null;
	private PreparedStatement state3 = null;
	private ResultSet result3 = null;
	private PreparedStatement state4 = null;
	private ResultSet result4 = null;
	private PreparedStatement state5= null;
	private ResultSet result5 = null;
	private PreparedStatement state6 = null;
	private ResultSet result6 = null;

	int aid;
	int collectNum;// 收藏数量
	int recomendNum;// 推荐数量
	int commentNum;// 评论数量
	IndexBean indexBean;

	
	
	
	public List<IndexBean> getIndexData() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "select * from article order by Adatetime desc";
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
				
aid=result.getInt("Aid");
				
				String sql4="SELECT COUNT(*) FROM Collect WHERE COaid="+aid;
				state4=connection.prepareStatement(sql4);
				result4=state4.executeQuery();
				if(result4.next()){
					collectNum=result4.getInt("COUNT(*)");
					indexBean.setCollectNum(collectNum);
				}
				
				String sql5="SELECT COUNT(*) FROM Recommend WHERE REaid="+aid;
				state5=connection.prepareStatement(sql5);
				result5=state5.executeQuery();
				if(result5.next()){
					recomendNum=result5.getInt("COUNT(*)");
					indexBean.setRecomendNum(recomendNum);
				}
				
				String sql6="SELECT COUNT(*) FROM Article_Comment WHERE ACOaid="+aid;
				state6=connection.prepareStatement(sql6);
				result6=state6.executeQuery();
				if(result6.next()){
					commentNum=result6.getInt("COUNT(*)");
					indexBean.setCommentNum(commentNum);
				}
				
				
				
				
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
				for (int i = 0; i < 5; i++)
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}
	
	

	// 查找真事分类,按照最新查找
	public List<IndexBean> getIndexDataOfZhenShiByNewest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "select * from article where Akind = 1 order by Adatetime desc";
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
				for (int i = 0; i < 5; i++)
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	// 查找真事分类，按照热门排序
	public List<IndexBean> getIndexDataOfZhenShiByHotest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " article.`Adatetime`,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM recentread , article "
					+ "WHERE Aid =RECaid AND Akind = 1 "
					+ "GROUP BY RECaid ORDER BY COUNT(*) DESC";
			state = connection.prepareStatement(sql);
			result = state.executeQuery();

			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String
				// content)

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

				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	// 查找创作分类，按最新排序
	public List<IndexBean> getIndexDataOfChuangZuoByNewest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "select * from article where Akind = 2 order by Adatetime desc";
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
				for (int i = 0; i < 5; i++)
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	// 查找创作分类 ,按照最热排序
	public List<IndexBean> getIndexDataOfChuangZuoByHotest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " article.`Adatetime`,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM recentread , article "
					+ "WHERE Aid =RECaid AND Akind = 2 "
					+ "GROUP BY RECaid ORDER BY COUNT(*) DESC";
			state = connection.prepareStatement(sql);
			result = state.executeQuery();

			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String
				// content)

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

				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	// 查找灵异分类，按最新排序
	public List<IndexBean> getIndexDataOfLingYiByNewest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "select * from article where Akind = 3 order by Adatetime desc";
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
				for (int i = 0; i < 5; i++)
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	// 查找LingYi分类 ,按照最热排序
	public List<IndexBean> getIndexDataOfLingYiByHotest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " article.`Adatetime`,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM recentread , article "
					+ "WHERE Aid =RECaid AND Akind = 3 "
					+ "GROUP BY RECaid ORDER BY COUNT(*) DESC";
			state = connection.prepareStatement(sql);
			result = state.executeQuery();

			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String
				// content)

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

				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	// 查找生活分类，按最新排序
	public List<IndexBean> getIndexDataOfShengHuoByNewest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "select * from article where Akind = 4 order by Adatetime desc";
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
				for (int i = 0; i < 5; i++)
					list.add(indexBean);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	// 查找生活分类 ,按照最热排序
	public List<IndexBean> getIndexDataOfShengHuoByHotest() {
		List<IndexBean> list = new ArrayList<IndexBean>();

		try {
			// 获取连接
			connection = C3P0Utils.getConnection();

			// 查询文章表
			String sql = "SELECT article.`Aauthor` , article.`Acoverimg` ,"
					+ " article.`Adatetime`,article.`Aid`,"
					+ "article.`Akind`,article.`Atitle` FROM recentread , article "
					+ "WHERE Aid =RECaid AND Akind = 4 "
					+ "GROUP BY RECaid ORDER BY COUNT(*) DESC";
			state = connection.prepareStatement(sql);
			result = state.executeQuery();

			while (result.next()) {
				IndexBean indexBean = new IndexBean();
				// public IndexBean(String headImg, String nickName, String
				// dateTime,
				// String kind, String backGround, String title, String
				// content)

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

				result.close();
				result2.close();
				result3.close();
				state.close();
				state2.close();
				state3.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

}
