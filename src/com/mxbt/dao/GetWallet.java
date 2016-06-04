package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.WalletBean;
import com.mxbt.utils.C3P0Utils;

public class GetWallet {
	private List<WalletBean> mList = new ArrayList<WalletBean>();
	private WalletBean mBean;
	private Connection mConnection;
	private PreparedStatement mStatement, mStatement_Uhead, mStatement_Ubk,mStatement_gold;
	private ResultSet mResultSet, mResultSet_Uhead, mResultSet_Ubk;

	public List<WalletBean> getWallet(int Uid) {

		try {
			mConnection = C3P0Utils.getConnection();
			mStatement = mConnection
					.prepareStatement("SELECT Wbalance,Unickname,Usex,Uhead,Ubk FROM USER,wallet WHERE Uid=WUid AND Uid="
							+ Uid);
			mResultSet = mStatement.executeQuery();
			if (mResultSet.next()) {
				mBean = new WalletBean();
				mBean.setUserName(mResultSet.getString("Unickname"));
				mBean.setUserSex(mResultSet.getString("Usex"));
				mBean.setWbalance(mResultSet.getInt("Wbalance"));
				// 查找头像路径
				mStatement_Uhead = mConnection
						.prepareStatement("SELECT Ipath FROM image,USER WHERE "
								+ mResultSet.getInt("Uhead") + "=Iid AND Uid="
								+ Uid);
				mResultSet_Uhead = mStatement_Uhead.executeQuery();
				if (mResultSet_Uhead.next()) {
					mBean.setUserHead(mResultSet_Uhead.getString("Ipath"));
				}
				// 查找背景路径
				mStatement_Ubk = mConnection
						.prepareStatement("SELECT Ipath FROM image,USER WHERE "
								+ mResultSet.getInt("Ubk") + "=Iid AND Uid="
								+ Uid);
				mResultSet_Ubk = mStatement_Ubk.executeQuery();
				if (mResultSet_Ubk.next()) {
					mBean.setUserBk(mResultSet_Ubk.getString("Ipath"));
				}
				mList.add(mBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0Utils.close(mResultSet, mStatement, mConnection);
			C3P0Utils.close(mResultSet_Ubk, mStatement_Ubk, null);
			C3P0Utils.close(mResultSet_Uhead, mStatement_Uhead, null);
		}

		return mList;
	}

	public void SetGoldNum(int Uid,int goldNum) {
		try {
			mConnection = C3P0Utils.getConnection();
			mStatement_gold = mConnection
					.prepareStatement("UPDATE wallet SET Wbalance="+goldNum+" WHERE WUid="+Uid);
			mStatement_gold.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(null, mStatement_gold, mConnection);
		}
		
	}
}
