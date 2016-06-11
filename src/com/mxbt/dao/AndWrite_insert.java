package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import com.mxbt.utils.C3P0Utils;

public class AndWrite_insert {
	
	private Connection connection;
	private PreparedStatement state;
	private ResultSet result;
	private PreparedStatement state2;
	private ResultSet result2;
	int AWtid;
	public void insertdata(int Uid,int Cid,String path,String title,String createtime,String isfinish){
		
		try {
			connection=C3P0Utils.getConnection();
			state=connection.prepareStatement("insert into text (Tpath) values(?)",Statement.RETURN_GENERATED_KEYS);
			state.setString(1, path);
			state.executeUpdate();
			result=state.getGeneratedKeys();
			if(result.next()){
				AWtid=result.getInt(1);
				
			}
			
			state2=connection.prepareStatement("insert into andwrite (AWcid,AWtid,AWauthor,AWtitle,AWcreatetime,AWisfinish) values(?,?,?,?,?,?)");
				state2.setInt(1, Cid);
				state2.setInt(2, AWtid);
				state2.setInt(3, Uid);
				state2.setString(4, title);
				state2.setString(5, createtime);
				state2.setString(6, isfinish);
				state2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
		}
		
		
	}
}
