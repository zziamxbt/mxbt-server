package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mxbt.beans.SignBean;
import com.mxbt.utils.C3P0Utils;

public class GetSign {
	private Connection mConnection;
	private PreparedStatement mStatement, mStatement_update;
	private ResultSet mResultSet, mResultSet_update;

	public List<SignBean> getSign(int User_Id) {
		List<SignBean> list = new ArrayList<SignBean>();
		SignBean signBean = new SignBean();
		mConnection = C3P0Utils.getConnection();
		try {
			mStatement = mConnection
					.prepareStatement("SELECT COUNT(*),Sflag,Screatetime FROM usersign WHERE Suid="
							+ User_Id);
			mResultSet = mStatement.executeQuery();
			if (mResultSet.next()) {
				if (mResultSet.getInt(1) == 0) {
					mStatement_update = mConnection
							.prepareStatement("INSERT INTO usersign(Suid,Screatetime,Sflag) VALUES("
									+ User_Id + ",'" + NowTime() + "'," + 0 + ")");
					mStatement_update.executeUpdate();
					signBean.setSignFlag(true);
					list.add(signBean);
				} else {
					if (mResultSet.getString("Screatetime").equals(NowTime())) {
						if (mResultSet.getInt("Sflag") == 0) {
							signBean.setSignFlag(true);
						} else {
							signBean.setSignFlag(false);
						}
					} else {
						mStatement_update = mConnection
								.prepareStatement("UPDATE usersign SET Screatetime='"
										+ NowTime()+ "',Sflag=0 WHERE Suid="
										+ User_Id);
						mStatement_update.executeUpdate();
						signBean.setSignFlag(true);
					}
					list.add(signBean);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(mResultSet, mStatement, mConnection);
			C3P0Utils.close(mResultSet_update, mStatement_update, null);
		}
		return list;
	}
	
	public void UpdateSign(int User_Id){
		mConnection=C3P0Utils.getConnection();
		try {
			mStatement=mConnection.prepareStatement("UPDATE usersign SET Screatetime='"
										+ NowTime()+ "',Sflag=1 WHERE Suid="
										+ User_Id);
			mStatement_update=mConnection.prepareStatement("UPDATE wallet SET Wbalance=Wbalance+2 WHERE WUid="+User_Id);
			mStatement_update.executeUpdate();
			mStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(null, mStatement, mConnection);
			C3P0Utils.close(null, mStatement_update, null);
		}
	}

	// 系统当前时间
	public String NowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间

	}
}
