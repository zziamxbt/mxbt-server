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
	private PreparedStatement mStatement;
	private ResultSet mResultSet;
	
	public List<Write_ReadBean> SelectWrite_Chapter(int AWid){
		
		try {
			mConnection = C3P0Utils.getConnection();
			mStatement = mConnection
					.prepareStatement("SELECT Atitle,AWtitle,AWcreatetime,Tpath,Unickname,Ipath "
							+ "FROM TEXT,article,chapter,andwrite,USER,image "
							+ "WHERE AWauthor=Uid AND Uhead=Iid AND AWcid=Cid AND Caid=Aid AND AWtid=Tid AND AWid="
							+ AWid);
			mResultSet = mStatement.executeQuery();
			if(mResultSet.next()){
				mWrite_ReadBean=new Write_ReadBean();
				mWrite_ReadBean.setWrite_articleName(mResultSet.getString("Atitle"));
				mWrite_ReadBean.setWrite_ChapterName(mResultSet.getString("AWtitle"));
				mWrite_ReadBean.setWrite_ChapterContent(httpString.getHttpString(mResultSet.getString("Tpath")));
				mWrite_ReadBean.setWrite_ChapterAuthor(mResultSet.getString("Ipath"));
				mWrite_ReadBean.setWrite_ChapterAuthorName(mResultSet.getString("Unickname"));
				mWrite_ReadBean.setWrite_ArticleTime(mResultSet.getString("AWcreatetime"));
				mList.add(mWrite_ReadBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(mResultSet, mStatement, mConnection);
		}
		
		return mList;
	}
}
