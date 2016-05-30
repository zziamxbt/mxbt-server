package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mxbt.beans.vote_content;
import com.mxbt.utils.C3P0Utils;

public class vote_excute {
	private Connection connection;
	private PreparedStatement state;
	private ResultSet result;
	private PreparedStatement state2;
	private ResultSet result2;
	private PreparedStatement sta;
	private ResultSet res;
	private PreparedStatement sta2;
	private ResultSet res2;
	int Uid;
	int x;
	public List<vote_content> getdata(String Cid){
		x=Integer.parseInt(Cid);
		List<vote_content> list=new  ArrayList<vote_content>();
		vote_content vote_content=null;
		try {
			connection=C3P0Utils.getConnection();
			state=connection.prepareStatement("select * from andwrite where AWcid="+x);
		//数据正在待续中
			
			result=state.executeQuery();
			while(result.next()){
			vote_content=new vote_content();
			vote_content.setVote_title(result.getString("AWtitle"));
			vote_content.setAWid(result.getInt("AWid"));
			System.out.println("AAAAAA"+result.getInt("AWid"));
			try {
			 String date=  result.getString("AWcreatetime");
			
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 	Date nowdate=new Date();
			 
				Date beforeDate=sdf.parse(date);
				
				if(nowdate.getTime()>beforeDate.getTime()){
					int day=(int)((nowdate.getTime()-beforeDate.getTime())/1000/60/60/24);
				//	System.out.println("天数"+day);
					if(day==0){
					vote_content.setVote_publishtime("今天发表");
					}else{
						vote_content.setVote_publishtime(day+"天前发表");
					}
					
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
			vote_content.setVote_num(result.getString("AWvote"));
			Uid=result.getInt("AWauthor");
			vote_content.setUid(Uid);
			state2=connection.prepareStatement("select * from user where Uid="+Uid);
			result2=state2.executeQuery();
			while(result2.next()){
					sta=connection.prepareStatement("select Ipath from image where Iid="+result2.getInt("Uhead"));
					res=sta.executeQuery();
					if(res.next()){
						vote_content.setVote_head(res.getString("Ipath"));
					}
					vote_content.setVote_name(result2.getString("Unickname"));
					sta2=connection.prepareStatement("select Ipath from image where Iid="+result2.getInt("Ubk"));
					res2=sta2.executeQuery();
					if(res2.next()){
						vote_content.setVote_coverimg(res2.getString("Ipath"));
					}
			   }
			
			list.add(vote_content);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
			C3P0Utils.close(res, sta, connection);
			C3P0Utils.close(res2, sta2, connection);
		}
		return list;
		
	}
	public void updatenum(int Uid){
	
	try {
		connection=C3P0Utils.getConnection();
		state=connection.prepareStatement("update andwrite set AWvote=AWvote+1 where AWauthor="+Uid+" and AWcid="+x);
		state.executeUpdate();
	   	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		C3P0Utils.close(result, state, connection);
		C3P0Utils.close(result2, state2, connection);
	}
	}
	public void updatenum2(int Uid){
		
		try {
			connection=C3P0Utils.getConnection();
			state=connection.prepareStatement("update andwrite set AWvote=AWvote-1 where AWauthor="+Uid+" and AWcid="+x);
			state.executeUpdate();
		   
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
		}
		}
}
