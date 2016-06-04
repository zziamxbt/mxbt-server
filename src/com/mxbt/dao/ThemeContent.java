package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.IndexBean;
import com.mxbt.beans.SubjectArticleBean;
import com.mxbt.utils.C3P0Utils;

public class ThemeContent {
	// 查询某个专题所包含的所有文章，返回IndexBean集合交于前台处理(参数为前台传来的专题id)
	public List<SubjectArticleBean> selectAllArticles(int Sid) {
		List<SubjectArticleBean> mList = new ArrayList<SubjectArticleBean>();
		SubjectArticleBean bean = null;
		Connection connection = C3P0Utils.getConnection();
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		PreparedStatement statement5 = null;
		PreparedStatement statement6 = null;
		PreparedStatement statement7 = null;
		PreparedStatement statement8 = null;
		PreparedStatement statement9 = null;
		PreparedStatement statement10 = null;
		PreparedStatement statement11 = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		ResultSet resultSet4 = null;
		ResultSet resultSet5 = null;
		ResultSet resultSet6 = null;
		ResultSet resultSet7 = null;
		ResultSet resultSet8 = null;
		ResultSet resultSet9 = null;
		ResultSet resultSet10 = null;
		ResultSet resultSet11 = null;

		int articleId;// 文章id
		int sid;
		int uid;
		int kindid;
		int coverid;
		int txtid;
		int headid;
		String headImg;// 用户
		String nickName;// 用户昵称
		String dateTime;// 文章创建时间
		String kind;// 文章类型
		String backGround;// 文章背景
		String title;// 文章标题
		String content;// 文章第i一节内容
		int collectNum;// 收藏数量
		int recomendNum;// 推荐数量
		int commentNum;// 评论数量

		try {
			String sql1 = "SELECT Asaid FROM Article_Subject WHERE ASsid="
					+ Sid;
			statement1 = connection.prepareStatement(sql1);
			resultSet1 = statement1.executeQuery();
			while (resultSet1.next()) {
				// 依据专题id，找到该专题下所有的文章id（>=0）——》写到Bean中
				bean = new SubjectArticleBean();
				articleId = resultSet1.getInt("Asaid");
				bean.setArticleId(articleId);// 1,文章id

				/* 依据文章id为1，找到对应的作者id，分类id，封面id，文章标题——》写到Bean中，文章创建时间——》写到Bean中 */
				String sql2 = "SELECT Aauthor,Akind,Acoverimg,Atitle,Adatetime FROM article WHERE Aid="
						+ articleId;
				statement2 = connection.prepareStatement(sql2);
				resultSet2 = statement2.executeQuery();
				if (resultSet2.next()) {
					kindid = resultSet2.getInt("Akind");
					coverid = resultSet2.getInt("Acoverimg");
					uid = resultSet2.getInt("Aauthor");
					title = resultSet2.getString("Atitle");
					bean.setTitle(title);// 2,title
					dateTime = resultSet2.getString("Adatetime");
					bean.setTime(dateTime);// 3,time
					/* 依据文章分类id，找到具体的分类内容——》写到Bean中 */
					String sql3 = "SELECT Content FROM kind WHERE Kid="
							+ kindid;
					statement3 = connection.prepareStatement(sql3);
					resultSet3 = statement3.executeQuery();
					if (resultSet3.next()) {
						kind = resultSet3.getString("Content");
						bean.setKind(kind);// 4,kind
						/* 依据文章封面id，找到具体的封面图片路径——》写到Bean中 */
						String sql4 = "SELECT Ipath FROM image WHERE Iid="
								+ coverid;
						statement4 = connection.prepareStatement(sql4);
						resultSet4 = statement4.executeQuery();
						if (resultSet4.next()) {
							backGround = resultSet4.getString("Ipath");
							bean.setBackground(backGround);// 5,background

							/* 依据文章id和章节数为1，找到具体的文章txt的id */
							String sql5 = "SELECT Ctid FROM chapter WHERE Cnum=1 AND Caid="
									+ articleId;
							statement5 = connection.prepareStatement(sql5);
							resultSet5 = statement5.executeQuery();
							if (resultSet5.next()) {
								txtid = resultSet5.getInt("Ctid");

								/* 依据txt的id找到对应的章节路径——》写到Bean中 */
								String sql6 = "SELECT Tpath FROM TEXT WHERE Tid="
										+ txtid;
								statement6 = connection.prepareStatement(sql6);
								resultSet6 = statement6.executeQuery();
								if (resultSet6.next()) {
									content = resultSet6.getString("Tpath");
									bean.setContent(content);// 6,content

									/* 依据Uid找到用户的头像id，昵称——》写到Bean中 */

									String sql7 = "SELECT Uhead,Unickname FROM USER WHERE Uid="
											+ uid;
									statement7 = connection
											.prepareStatement(sql7);
									resultSet7 = statement7.executeQuery();
									if (resultSet7.next()) {
										headid = resultSet7.getInt("Uhead");
										nickName = resultSet7
												.getString("Unickname");
										bean.setNickName(nickName);// 7，nickname

										/* 依据头像id，找到用户对应的头像地址，——》写到Bean中 */

										String sql8 = "SELECT Ipath FROM image WHERE Iid="
												+ headid;
										statement8 = connection
												.prepareStatement(sql8);
										resultSet8 = statement8.executeQuery();
										if (resultSet8.next()) {
											headImg = resultSet8
													.getString("Ipath");
											bean.setHeadImg(headImg);// 8,headimg
										}
										/* 依据文章id,找到对应的收藏数量 */
										String sql9 = "SELECT COUNT(*) FROM Collect WHERE COaid="
												+ articleId;
										statement9 = connection
												.prepareStatement(sql9);
										resultSet9 = statement9.executeQuery();
										if (resultSet9.next()) {
											collectNum = resultSet9
													.getInt("COUNT(*)");
											bean.setCollectNum(collectNum);// 9,collectNum
										}
										/* 依据文章id,找到对应的推荐数量 */
										String sql10 = "SELECT COUNT(*) FROM Recommend WHERE REaid="
												+ articleId;
										statement10 = connection
												.prepareStatement(sql10);
										resultSet10 = statement10
												.executeQuery();
										if (resultSet10.next()) {
											recomendNum = resultSet10
													.getInt("COUNT(*)");
											bean.setRecomendNum(recomendNum);// 10,recommendtNum
										}

										/* 依据文章id,找到对应的评论数量 */
										String sql11 = "SELECT COUNT(*) FROM Article_Comment WHERE ACOaid="
												+ articleId;
										statement11 = connection
												.prepareStatement(sql11);
										resultSet11 = statement11
												.executeQuery();
										if (resultSet11.next()) {
											commentNum = resultSet11
													.getInt("COUNT(*)");
											bean.setCommentNum(commentNum);
											;// 11,commentNum
										}
									}

								}

							}

						}

					}

				}
				mList.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close(resultSet, statement, connection);
			C3P0Utils.close(resultSet1, statement1, connection);
			C3P0Utils.close(resultSet2, statement2, connection);
			C3P0Utils.close(resultSet3, statement3, connection);
			C3P0Utils.close(resultSet4, statement4, connection);
			C3P0Utils.close(resultSet5, statement5, connection);
			C3P0Utils.close(resultSet6, statement6, connection);
			C3P0Utils.close(resultSet7, statement7, connection);
			C3P0Utils.close(resultSet8, statement8, connection);
			C3P0Utils.close(resultSet9, statement9, connection);
			C3P0Utils.close(resultSet10, statement10, connection);
			C3P0Utils.close(resultSet11, statement11, connection);
		}
		return mList;

	}

}
