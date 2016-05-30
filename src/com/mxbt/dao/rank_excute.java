package com.mxbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mxbt.beans.rank_author;

import com.mxbt.utils.C3P0Utils;



public class rank_excute {
	private Connection connection;
	private PreparedStatement state;
	private ResultSet result;
	private PreparedStatement state2;
	private ResultSet result2;
	private PreparedStatement state3;
	private ResultSet result3;
	private PreparedStatement state4;
	private ResultSet result4;
	private PreparedStatement sta;
	private ResultSet res;
	private PreparedStatement sta2;
	private ResultSet res2;
	int AWauthor;
	int Cauthor;
	int Fid;
	int Uhead;
	String Unickname;
	
	String title="";
	StringBuilder content;

	public List<rank_author> getfocus(){
		List<rank_author> list=new ArrayList<rank_author>();
		rank_author  rank_author=null;
	
		try {
			connection=C3P0Utils.getConnection();
			String sql="SELECT focus.Fbefocused, COUNT(*) FROM focus GROUP BY focus.Fbefocused ORDER BY COUNT(*) DESC ";
			state=connection.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()){
				    rank_author=new rank_author();
				 Fid=result.getInt("Fbefocused");
					String sql2="select * from user where Uid="+Fid;
					state2=connection.prepareStatement(sql2);
					result2=state2.executeQuery();
					while (result2.next())
					{
					
						 Unickname=result2.getString("Unickname");
						 rank_author.setUnickname(Unickname);
						 Uhead=result2.getInt("Uhead");
						 sta=connection.prepareStatement("select Ipath from image where Iid ="+Uhead);
						 res=sta.executeQuery();
						 while(res.next()){
							 rank_author.setUhead(res.getString("Ipath"));
						 }
					
					}
				String sql3="select * from chapter where Cauthor="+Fid+" ORDER BY Cgood DESC limit 3" ;
				state3=connection.prepareStatement(sql3);
				result3=state3.executeQuery();
				content=new StringBuilder();
				
				int Caid=0;
				while(result3.next()){
					if(Caid==result3.getInt("Caid"))
					{
						
						continue;
					}else{
						Caid=result3.getInt("Caid");
						String sql4="select * from article where Aid="+Caid;
						state4=connection.prepareStatement(sql4);
						result4=state4.executeQuery();
						while(result4.next()){					
							title=result4.getString("Atitle");
							content.append("《"+title+"》,");
					
						}
						rank_author.setUcontent(content.toString());
					}
					
				}
				list.add(rank_author);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
			C3P0Utils.close(result3, state3, connection);
			C3P0Utils.close(result4, state4, connection);
			C3P0Utils.close(res, sta, connection);
			C3P0Utils.close(res2, sta2, connection);
		}
		return list;
	}
	public List<rank_author> getgood(){
		List<rank_author> list=new ArrayList<rank_author>();
		rank_author  rank_author=null;
		try {
			connection=C3P0Utils.getConnection();
			String sql="SELECT Cauthor , SUM(cgood) FROM chapter GROUP BY chapter.Cauthor ORDER BY SUM(cgood) DESC";
			state=connection.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()){
				  rank_author=new rank_author();
				 Cauthor=result.getInt("Cauthor");
				String sql2="select * from user where Uid="+Cauthor;
				state2=connection.prepareStatement(sql2);
				result2=state2.executeQuery();
				while(result2.next()){

					 Unickname=result2.getString("Unickname");
					 rank_author.setUnickname(Unickname);
					 Uhead=result2.getInt("Uhead");
					 sta=connection.prepareStatement("select Ipath from image where Iid ="+Uhead);
					 res=sta.executeQuery();
					 while(res.next()){
						 rank_author.setUhead(res.getString("Ipath"));
					 }
				}
				String sql3="select * from chapter where Cauthor="+Cauthor+" ORDER BY Cgood DESC limit 3" ;
				state3=connection.prepareStatement(sql3);
				result3=state3.executeQuery();
				content=new StringBuilder();
				int Caid=0;
				while(result3.next()){
					if(Caid==result3.getInt("Caid")){
						continue;
					}else{
						Caid=result3.getInt("Caid");
						String sql4="select * from article where Aid="+Caid;
						state4=connection.prepareStatement(sql4);
						result4=state4.executeQuery();
						while(result4.next()){
							title=result4.getString("Atitle");
							content.append("《"+title+"》,");
						}
						rank_author.setUcontent(content.toString());
					}
				
				}
				list.add(rank_author);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
			C3P0Utils.close(result3, state3, connection);
			C3P0Utils.close(result4, state4, connection);
			C3P0Utils.close(res, sta, connection);
			C3P0Utils.close(res2, sta2, connection);
		}
		
		return list;
	}
	public List<rank_author> getwritebest(){
		List<rank_author> list=new ArrayList<rank_author>();
		rank_author  rank_author=null;
		
		
		try {
			connection=C3P0Utils.getConnection();
			String sql="SELECT AWauthor,COUNT(*) FROM andwrite GROUP BY AWauthor ORDER BY COUNT(*) DESC";
			state=connection.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()){
				 rank_author=new rank_author();
				AWauthor=result.getInt("AWauthor");
				String sql2="select * from user where Uid="+AWauthor;
				state2=connection.prepareStatement(sql2);
				result2=state2.executeQuery();
				while(result2.next()){

					 Unickname=result2.getString("Unickname");
					 rank_author.setUnickname(Unickname);
					 Uhead=result2.getInt("Uhead");
					 sta=connection.prepareStatement("select Ipath from image where Iid ="+Uhead);
					 res=sta.executeQuery();
					 while(res.next()){
						 rank_author.setUhead(res.getString("Ipath"));
					 }
				}
				String sql3="select * from chapter where Cauthor="+AWauthor+" ORDER BY Cgood DESC limit 3" ;
				state3=connection.prepareStatement(sql3);
				result3=state3.executeQuery();
				content=new StringBuilder();
				int Caid=0;
				while(result3.next()){
					if(Caid==result3.getInt("Caid")){
						continue;
					}else{
						Caid=result3.getInt("Caid");
						String sql4="select * from article where Aid="+Caid;
						state4=connection.prepareStatement(sql4);
						result4=state4.executeQuery();
						while(result4.next()){
							title=result4.getString("Atitle");
							content.append("《"+title+"》,");
						}
						rank_author.setUcontent(content.toString());	
					}
				
				}
				list.add(rank_author);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
			C3P0Utils.close(result2, state2, connection);
			C3P0Utils.close(result3, state3, connection);
			C3P0Utils.close(result4, state4, connection);
			C3P0Utils.close(res, sta, connection);
			C3P0Utils.close(res2, sta2, connection);
		}
		return list;
	}
}
