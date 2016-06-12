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
	private PreparedStatement mStatement, mStatement_Uhead, mStatement_Ubk,mStatement_gold,mStatement_reward,mStatement_rewarded;
	private ResultSet mResultSet, mResultSet_Uhead, mResultSet_Ubk,mResultSet_reward,mResultSet_rewarded;

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
//购买金币数插入数据库中
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
	
	//打赏的金币插入数据库中
	public void SetRewardGold(int Uid,int Authorid,int goldNum) {
		try {
			mConnection = C3P0Utils.getConnection();
			mStatement = mConnection
					.prepareStatement("SELECT COUNT(*) FROM reward WHERE RErewarder="+Uid
							+ " AND RErewarded="+Authorid);
			mResultSet=mStatement.executeQuery();
			if(mResultSet.next()){
				if(mResultSet.getInt(1)==0){
					mStatement_gold=mConnection.prepareStatement("INSERT INTO reward(RErewarder,RErewarded,REmoney) VALUES("+Uid
							+ ","+Authorid
							+ ","+goldNum+ ")");
					mStatement_gold.executeUpdate();

				}else{
					mStatement_gold=mConnection.prepareStatement("UPDATE reward SET REmoney="+goldNum
							+ " WHERE RErewarder="+Uid
							+ " AND RErewarded="+Authorid);
					mStatement_gold.executeUpdate();
				}
				mStatement_reward=mConnection.prepareStatement("UPDATE wallet SET Wbalance=Wbalance-"+goldNum+" WHERE WUid="+Uid);
				mStatement_rewarded=mConnection.prepareStatement("UPDATE wallet SET Wbalance=Wbalance+"+goldNum+" WHERE WUid="+Authorid);
				mStatement_reward.executeUpdate();
				mStatement_rewarded.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(mResultSet, mStatement, mConnection);
			C3P0Utils.close(null, mStatement_gold, null);
			C3P0Utils.close(null, mStatement_reward, null);
			C3P0Utils.close(null, mStatement_rewarded, null);
		}
		
	}
}
