package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.Write_ReadBean;
import com.mxbt.utils.C3P0Utils;
import com.mxbt.utils.HttpString;

public class GetWrite_Chapter {
	private HttpString httpString=new HttpString();

	private List<Write_ReadBean> mList=new ArrayList<Write_ReadBean>();
	private Write_ReadBean mWrite_ReadBean;
	private Connection mConnection;
	private PreparedStatement mStatement,mStatementVote,mStatementFocus;
	private ResultSet mResultSet,mResultSetVote,mResultSetFocus;
	
	public List<Write_ReadBean> SelectWrite_Chapter(int AWid,int User_Id){
		mWrite_ReadBean=new Write_ReadBean();
		try {
			mConnection = C3P0Utils.getConnection();
			//获取本章节的内容
			mStatement = mConnection
					.prepareStatement("SELECT Uid,Atitle,AWtitle,AWcreatetime,Tpath,Unickname,Ipath "
							+ "FROM TEXT,article,chapter,andwrite,USER,image "
							+ "WHERE AWauthor=Uid AND Uhead=Iid AND AWcid=Cid AND Caid=Aid AND AWtid=Tid AND AWid="
							+ AWid);
			//判断此章节是否被投票
			mStatementVote=mConnection.prepareStatement("SELECT COUNT(*) FROM andwrite_good WHERE Gawid="+AWid
					+ " AND Guid="+User_Id);
			
			mResultSet = mStatement.executeQuery();
			mResultSetVote=mStatementVote.executeQuery();
			if(mResultSetVote.next()){
				if(mResultSetVote.getInt(1)==1){
					mWrite_ReadBean.setVoteFlag(true);
				}else{
					mWrite_ReadBean.setVoteFlag(false);
				}
				
			}
			if(mResultSet.next()){
				
				mWrite_ReadBean.setWrite_articleName(mResultSet.getString("Atitle"));
				mWrite_ReadBean.setWrite_ChapterName(mResultSet.getString("AWtitle"));
				mWrite_ReadBean.setWrite_ChapterContent(httpString.getHttpString(mResultSet.getString("Tpath")));
				mWrite_ReadBean.setWrite_ChapterAuthor(mResultSet.getString("Ipath"));
				mWrite_ReadBean.setWrite_ChapterAuthorName(mResultSet.getString("Unickname"));
				mWrite_ReadBean.setWrite_ArticleTime(mResultSet.getString("AWcreatetime"));
				//判断此章节是否被关注
				mStatementFocus=mConnection.prepareStatement("SELECT COUNT(*) FROM focus WHERE Ffocus="+User_Id
						+ " AND Fbefocused="+mResultSet.getInt("Uid"));
				mResultSetFocus=mStatementFocus.executeQuery();
				if(mResultSetFocus.next()){
					if(mResultSetFocus.getInt(1)==1){
						mWrite_ReadBean.setfocusFlag(true);
					}else{
						mWrite_ReadBean.setfocusFlag(false);
					}
				}


			}
			
				mList.add(mWrite_ReadBean);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(mResultSet, mStatement, mConnection);
			C3P0Utils.close(mResultSetFocus, mStatementFocus, null);
			C3P0Utils.close(mResultSetVote, mStatementVote, null);
		}
		
		return mList;
	}
	
	
	public List<Write_ReadBean> SelectWrite_ChapterNo(int AWid){
		mWrite_ReadBean=new Write_ReadBean();
		try {
			mConnection = C3P0Utils.getConnection();
			//获取本章节的内容
			mStatement = mConnection
					.prepareStatement("SELECT Uid,Atitle,AWtitle,AWcreatetime,Tpath,Unickname,Ipath "
							+ "FROM TEXT,article,chapter,andwrite,USER,image "
							+ "WHERE AWauthor=Uid AND Uhead=Iid AND AWcid=Cid AND Caid=Aid AND AWtid=Tid AND AWid="
							+ AWid);
			
			
			mResultSet = mStatement.executeQuery();		
			
			if(mResultSet.next()){				
				mWrite_ReadBean.setWrite_articleName(mResultSet.getString("Atitle"));
				mWrite_ReadBean.setWrite_ChapterName(mResultSet.getString("AWtitle"));
				mWrite_ReadBean.setWrite_ChapterContent(httpString.getHttpString(mResultSet.getString("Tpath")));
				mWrite_ReadBean.setWrite_ChapterAuthor(mResultSet.getString("Ipath"));
				mWrite_ReadBean.setWrite_ChapterAuthorName(mResultSet.getString("Unickname"));
				mWrite_ReadBean.setWrite_ArticleTime(mResultSet.getString("AWcreatetime"));

			}
			
				mList.add(mWrite_ReadBean);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(mResultSet, mStatement, mConnection);
		
		}
		
		return mList;
	}
	
	
    //判断是否投票	
	public void isVote(String flag, int AWid, int User_Id){
		String sql=null;
		if(flag.equals("true")){
			sql="INSERT INTO andwrite_good(Gawid,Guid) VALUES("+AWid
					+ ","+User_Id+")";
		}else{
			sql="DELETE FROM andwrite_good  WHERE Gawid="+AWid
					+ " AND Guid="+User_Id;
		}
		mConnection=C3P0Utils.getConnection();
		try {
			mStatementVote=mConnection.prepareStatement(sql);
			mStatementVote.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(mResultSetVote, mStatementVote, mConnection);
		}
		
		
	}
	//判断是否关注	
		public void isFocus(String flag, int AWid, int User_Id){
			String sql_ChapterAuthor="SELECT Cauthor FROM chapter WHERE Cid="+AWid;
			String sql=null;
			
			mConnection=C3P0Utils.getConnection();
			try {
				mStatement=mConnection.prepareStatement(sql_ChapterAuthor);
				mResultSet=mStatement.executeQuery();
				if(mResultSet.next()){
					if(flag.equals("true")){
						sql="INSERT INTO focus(Ffocus,Fbefocused) VALUES("+User_Id
								+ ","+mResultSet.getInt("Cauthor")+")";
					}else{
						sql="DELETE FROM focus  WHERE Ffocus="+User_Id
								+ " AND Fbefocused="+mResultSet.getInt("Cauthor");
					}
				}
				mStatementVote=mConnection.prepareStatement(sql);
				mStatementVote.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				C3P0Utils.close(mResultSet, mStatement, mConnection);
				C3P0Utils.close(mResultSetFocus, mStatementFocus, null);
				
			}
		}
}
