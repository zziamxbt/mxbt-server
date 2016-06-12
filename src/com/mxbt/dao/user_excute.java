package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.user_info;
import com.mxbt.utils.C3P0Utils;

public class user_excute {
	List<user_info> list;
	Connection connection;
	PreparedStatement state;
	ResultSet result;
	PreparedStatement state2;
	ResultSet result2;
	
	public List<user_info> alluser(){
		list=new ArrayList<user_info>();
		user_info info=null;
		connection=C3P0Utils.getConnection();
		try {
			state=connection.prepareStatement("select Uname,Unickname,Uhead from user");
			result=state.executeQuery();
			while(result.next()){
				info=new user_info();	
				info.setUname(result.getString("Uname"));
				info.setUnickname(result.getString("Unickname"));
				state2=connection.prepareStatement("select Ipath from image where Iid ="+result.getInt("Uhead"));
				result2=state2.executeQuery();
				while(result2.next()){
					info.setUhead(result2.getString("Ipath"));
				}
				list.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
		}
		return list;
		
	}
}
