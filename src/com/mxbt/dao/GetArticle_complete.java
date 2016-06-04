package com.mxbt.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mxbt.beans.JavaBean_Article;
import com.mxbt.utils.C3P0Utils;
import com.mxbt.utils.HttpString;

public class GetArticle_complete {
	private Connection mConnection;
	private HttpString httpString = new HttpString();
	private PreparedStatement mStatement, mStatement_cover, mStatement_type,
			mStatement_author, mStatement_uhead, mStatement_ubk,
			mStatement_chapter_author, mStatement_chapter_content,
			mStatement_focus, mStatement_reader, mStatement_recommand,
			mStatement_collect;
	private ResultSet mResultSet, mResultSet_cover, mResultSet_type,
			mResultSet_author, mResultSet_uhead, mResultSet_ubk,
			mResultSet_chapter_author, mResultSet_chapter_content,
			mResultSet_focus, mResultSet_reader, mResultSet_recommand,
			mResultSet_collect;
	private JavaBean_Article mBean_Article;
	private List<JavaBean_Article> mlist = new ArrayList<JavaBean_Article>();
	private List<String> mResultSet_chapter_authorList = new ArrayList<String>();
	private List<String> mResultSet_author_chapter_headList = new ArrayList<String>();
	private List<String> mResultSet_chapter_contentList = new ArrayList<String>();
	private List<String> mResultSet_chapter_numberList = new ArrayList<String>();
	private List<String> mResultSet_create_chapter_timeList = new ArrayList<String>();

	// 得到文章的id，获取文章的封面

	public List<JavaBean_Article> SelectArticle(int aid, int User_Id) {
		int Article_id = aid;
		// System.out.print(Article_id+"RRRRRRRRRRRRRRRRRRRRRRR");
		try {
			mConnection = C3P0Utils.getConnection();
			mStatement = mConnection
					.prepareStatement("SELECT * FROM article WHERE Aid="
							+ Article_id);
			mResultSet = mStatement.executeQuery();
			if (mResultSet.next()) {
				mBean_Article = new JavaBean_Article();

				mStatement_cover = mConnection
						.prepareStatement("SELECT Ipath FROM image WHERE Iid="
								+ mResultSet.getString("Acoverimg"));
				mStatement_type = mConnection
						.prepareStatement("SELECT Content FROM kind WHERE Kid="
								+ mResultSet.getString("Akind"));
				mStatement_author = mConnection
						.prepareStatement("SELECT Unickname,Uhead,Ubk,Usex FROM user WHERE Uid="
								+ mResultSet.getString("Aauthor"));
				mStatement_chapter_author = mConnection
						.prepareStatement("SELECT Unickname,Ipath FROM USER,chapter,image  WHERE  Cauthor=Uid AND Caid="
								+ Article_id + " AND Iid=Uid ORDER BY Cnum ASC");
				// 获取文章的内容,章节号和章节创建的时间
				mStatement_chapter_content = mConnection
						.prepareStatement("SELECT Cid,Tpath,Ctitle,Ccreatetime FROM TEXT,chapter  WHERE Ctid=Tid AND Caid="
								+ Article_id + " ORDER BY Cnum ASC");
				mStatement_focus = mConnection
						.prepareStatement("SELECT COUNT(Fbefocused) FROM focus,article WHERE Aid="
								+ Article_id + " AND Aauthor=Fbefocused");
				mStatement_reader = mConnection
						.prepareStatement("SELECT COUNT(RECuid) FROM recentread WHERE RECaid="
								+ Article_id);
				// 判断此文章是否被推荐
				mStatement_recommand = mConnection
						.prepareStatement("SELECT COUNT(*) FROM recommend WHERE REaid="
								+ aid + " AND REuid=" + User_Id);

				// 判断此文章是否被收藏
				mStatement_collect = mConnection
						.prepareStatement("SELECT COUNT(*) FROM collect WHERE COaid="
								+ aid + " AND COuid=" + User_Id);
				mResultSet_recommand = mStatement_recommand.executeQuery();
				mResultSet_collect = mStatement_collect.executeQuery();
				mResultSet_reader = mStatement_reader.executeQuery();
				mResultSet_cover = mStatement_cover.executeQuery();
				mResultSet_type = mStatement_type.executeQuery();
				mResultSet_author = mStatement_author.executeQuery();
				mResultSet_chapter_author = mStatement_chapter_author
						.executeQuery();
				mResultSet_chapter_content = mStatement_chapter_content
						.executeQuery();
				mResultSet_focus = mStatement_focus.executeQuery();

				// 文章名
				mBean_Article.setArticle_title(mResultSet.getString("Atitle"));
				
				// 获取读者
				if (mResultSet_reader.next()) {
					mBean_Article.setReader_number(mResultSet_reader.getInt(1));
				}
				// 获取关注数
				if (mResultSet_focus.next()) {
					mBean_Article.setFocus_number(mResultSet_focus.getInt(1));
				}
				// 文章类型
				if (mResultSet_type.next()) {
					mBean_Article.setArticle_type(mResultSet_type
							.getString("Content"));
				}
				// 文章作者,背景图,性别和作者头像
				if (mResultSet_author.next()) {
					mBean_Article.setAuthor_sex(mResultSet_author
							.getString("Usex"));
					mBean_Article.setAuthor_name(mResultSet_author
							.getString("Unickname"));

					mStatement_uhead = mConnection
							.prepareStatement("SELECT Ipath FROM image WHERE Iid="
									+ mResultSet_author.getString("Uhead"));
					mStatement_ubk = mConnection
							.prepareStatement("SELECT Ipath FROM image WHERE Iid="
									+ mResultSet_author.getString("Ubk"));

					mResultSet_uhead = mStatement_uhead.executeQuery();
					mResultSet_ubk = mStatement_ubk.executeQuery();
					if (mResultSet_uhead.next()) {
						mBean_Article.setAuthor_headportrait(mResultSet_uhead
								.getString("Ipath"));
					}
					if (mResultSet_ubk.next()) {
						mBean_Article.setArticle_background(mResultSet_ubk
								.getString("Ipath"));
					}

				}
				// 文章封面
				if (mResultSet_cover.next()) {
					mBean_Article.setArticle_cover(mResultSet_cover
							.getString("Ipath"));
				}
				// 文章每个章节的作者
				while (mResultSet_chapter_author.next()) {

					mResultSet_chapter_authorList.add(mResultSet_chapter_author
							.getString("Unickname"));
					mResultSet_author_chapter_headList
							.add(mResultSet_chapter_author.getString("Ipath"));

				}
				mBean_Article
						.setAuthor_chapter_name(mResultSet_chapter_authorList);
				mBean_Article
						.setAuthor_chapter_head(mResultSet_author_chapter_headList);
				// 判断是否被推荐
				if (mResultSet_recommand.next()) {
                       if(mResultSet_recommand.getInt(1)==0){
                    	   mBean_Article.setRecommandFalg(false);
                       }else{
                    	   mBean_Article.setRecommandFalg(true);
                       }
				}
				// 判断是否被收藏
				if (mResultSet_collect.next()) {
					 if(mResultSet_collect.getInt(1)==0){
                  	   mBean_Article.setCollectFalg(false);
                     }else{
                  	   mBean_Article.setCollectFalg(true);
                     }
				}
				// 文章每个章节的内容，章节号和创建章节时间
				while (mResultSet_chapter_content.next()) {
					if (mResultSet_chapter_content.getString("Tpath") != null) {
						mResultSet_chapter_contentList.add(httpString
								.getHttpString(mResultSet_chapter_content
										.getString("Tpath")));
						mResultSet_chapter_numberList
								.add(mResultSet_chapter_content
										.getString("Ctitle"));
						mResultSet_create_chapter_timeList
								.add(mResultSet_chapter_content
										.getString("Ccreatetime"));
					} else {
						mBean_Article.setChapter_id(mResultSet_chapter_content
								.getInt("Cid"));
					}

				}
				mBean_Article.setChapter_number(mResultSet_chapter_numberList);
				mBean_Article
						.setChapter_content(mResultSet_chapter_contentList);
				mBean_Article
						.setCreate_chapter_time(mResultSet_create_chapter_timeList);
			}
			// 获取关注数

			mlist.add(mBean_Article);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0Utils.close(mResultSet, mStatement, mConnection);
			C3P0Utils.close(mResultSet_author, mStatement_author, null);
			C3P0Utils.close(mResultSet_chapter_author,
					mStatement_chapter_author, null);
			C3P0Utils.close(mResultSet_chapter_content,
					mStatement_chapter_content, null);
			C3P0Utils.close(mResultSet_cover, mStatement_cover, null);
			C3P0Utils.close(mResultSet_focus, mStatement_focus, null);
			C3P0Utils.close(mResultSet_reader, mStatement_reader, null);
			C3P0Utils.close(mResultSet_type, mStatement_type, null);
			C3P0Utils.close(mResultSet_ubk, mStatement_ubk, null);
			C3P0Utils.close(mResultSet_uhead, mStatement_uhead, null);
			C3P0Utils.close(mResultSet_recommand, mStatement_recommand, null);
			C3P0Utils.close(mResultSet_collect, mStatement_collect, null);

		}
		return mlist;

	}

	// 对推荐表进行修改
	public void ChargeRecommend(String flag, int aid, int User_Id) {
		String sql = null;
		if (flag.equals("true")) {
			sql = "INSERT INTO recommend(REaid,REuid,REcreatetime) VALUES('"
					+ aid + "','" + User_Id + "','" + NowTime() + "')";
		} else {
			sql = "DELETE FROM recommend WHERE REaid=" + aid + " AND REuid="
					+ User_Id;
		}

		try {
			mConnection = C3P0Utils.getConnection();
			mStatement = mConnection.prepareStatement(sql);
			mStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0Utils.close(null, mStatement, mConnection);
		}

	}

	// 对收藏表进行修改
	public void ChargeCollect(String flag, int aid, int User_Id) {
		String sql = null;
		if (flag.equals("true")) {
			sql = "INSERT INTO collect(COaid,COuid,COcreatetime) VALUES('"
					+ aid + "','" + User_Id + "','" + NowTime() + "')";
		} else {
			sql = "DELETE FROM collect WHERE COaid=" + aid + " AND COuid="
					+ User_Id;
		}
		try {
			mConnection = C3P0Utils.getConnection();
			mStatement = mConnection.prepareStatement(sql);
			mStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0Utils.close(null, mStatement, mConnection);
		}
	}

	// 系统当前时间
	public String NowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间

	}

}
