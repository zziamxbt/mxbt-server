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

import com.mxbt.beans.ArticleCommentBean;
import com.mxbt.utils.C3P0Utils;

public class AndWriteComment_excute {
	private Connection connection;
	private PreparedStatement state;
	private ResultSet result;
	private PreparedStatement state2;
	private ResultSet result2;
	private PreparedStatement sta;
	private ResultSet res;
	private PreparedStatement sta2;
	private ResultSet res2;
	List<ArticleCommentBean> list;
	List<ArticleCommentBean> list2;
	int Uid;
	//int [] data=new int[500];
	List<Integer> data=new ArrayList<Integer>();
	int i=0;
	boolean flag=true;//用来标识数据数据是否重复
	public List<ArticleCommentBean> data_select(int AWid){
		list=new ArrayList<ArticleCommentBean>();
		ArticleCommentBean bean=null;
		try {
			connection=C3P0Utils.getConnection();
			state=connection.prepareStatement("SELECT * FROM andwritting_comment WHERE AWCawid="+AWid+" ORDER BY AWCcreatetime DESC");
			result=state.executeQuery();
     		while(result.next()){
				flag=true;
				 bean=new ArticleCommentBean();
				 Uid=result.getInt("AWCuid");
				 bean.setUid(Uid);

				 bean.setContent(result.getString("AWCcontent"));
				 bean.setCreatetime(result.getString("AWCcreatetime"));
				 state2=connection.prepareStatement("select * from user where Uid="+result.getInt("AWCuid"));
				 result2=state2.executeQuery();
				 while(result2.next()){
					 bean.setNickname(result2.getString("Unickname"));
					 sta=connection.prepareStatement("select Ipath from image where Iid="+result2.getInt("Uhead"));
					 	res=sta.executeQuery();
					 	while(res.next()){
					 		bean.setHead(res.getString("Ipath"));
					 	}
				 }
				 list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
			if(result!=null){
				
					result.close();
				
			}
			if(result2!=null){
				
				result2.close();
			
		}
			if(state!=null){
				
				state.close();
			
		}
			if(state2!=null){
				
				state2.close();
			
		}
			if(res!=null){
				
				res.close();
			
		}
			if(sta!=null){
				
				sta.close();
			
		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
		
	}
	public void data_insert(int AWid,int Uid,String createtime,String content){
		try {
			connection=C3P0Utils.getConnection();
			state=connection.prepareStatement("insert into andwritting_comment (AWCawid,AWCuid,AWCcontent,AWCcreatetime) values(?,?,?,?)");
			state.setInt(1, AWid);
			state.setInt(2, Uid);
			state.setString(3, content);
			state.setString(4, createtime);
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
		}
	}
	public List<ArticleCommentBean> reply_select(int AWid){
		list2=new ArrayList<ArticleCommentBean>();
		ArticleCommentBean article=null;
		try {
			connection=C3P0Utils.getConnection();
			state=connection.prepareStatement("select * from andwritting_reply where AWRawid="+AWid+" order by AWRcreatetime DESC");
			
//			 System.out.println(data);
//			for(int j=0;j<data.size();j++){
//				state=connection.prepareStatement("select * from article_reply where AREreplyed="+data.get(j)+" order by AREcreatetime DESC");
				result=state.executeQuery();
				while(result.next()){
					article=new ArticleCommentBean();
					article.setUid(result.getInt("AWRreplyer"));
					article.setContent(result.getString("AWRcontent"));
					article.setCreatetime(result.getString("AWRcreatetime"));
					state2=connection.prepareStatement("select * from user where Uid="+result.getInt("AWRreplyer"));
					result2=state2.executeQuery();
					while(result2.next()){
						article.setNickname(result2.getString("Unickname"));
						sta=connection.prepareStatement("select Ipath from image where Iid="+result2.getInt("Uhead"));
						res=sta.executeQuery();
						while(res.next()){
							article.setHead(res.getString("Ipath"));
						}
					}
					list2.add(article);
				}
		//	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(result!=null){
					
						result.close();
					
				}
				if(result2!=null){
					
					result2.close();
				
			}
				if(state!=null){
					
					state.close();
				
			}
				if(state2!=null){
					
					state2.close();
				
			}
				if(res!=null){
					
					res.close();
				
			}
				if(sta!=null){
					
					sta.close();
				
			}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return list2;
		
	}
	
	public void reply_insert(int AWid,int Uid,int replyed,String content,String createtime){
		String zifu="";
		try {
			connection=C3P0Utils.getConnection();
			state2=connection.prepareStatement("select * from user where Uid="+replyed);
			result2=state2.executeQuery();
			while(result2.next()){
				zifu=result2.getString("Unickname");
			}
			state=connection.prepareStatement("insert into andwritting_reply (AWRawid,AWRreplyer,AWRreplyed,AWRcontent,AWRcreatetime) values(?,?,?,?,?)");
			state.setInt(1, AWid);
			state.setInt(2, Uid);
			state.setInt(3, replyed);
			state.setString(4, "回复:'"+zifu+"':"+content);
			state.setString(5, createtime);
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.close(result, state, connection);
		}
	}
	
	
	public List<ArticleCommentBean> getall(List<ArticleCommentBean> list,List<ArticleCommentBean> list2){
		List<ArticleCommentBean> ALLlist=new ArrayList<ArticleCommentBean>();
		int i=0,j=0;
		
		//	System.out.println(""+list.get(0).getCreatetime()+":"+list2.get(0).getCreatetime());
			while(i<list.size()&&j<list2.size()){
				 try {
						String  before=list.get(i).getCreatetime();
						 String after=list2.get(j).getCreatetime();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date1=sdf.parse(before);
					Date date2=sdf.parse(after);
					if((date1.getTime()>date2.getTime())){
						ALLlist.add(list.get(i));
						
							i++;		
					}else {
						ALLlist.add(list2.get(j));
						
						j++;
					}
				 } catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			while(i<list.size()){
				ALLlist.add(list.get(i));
				i++;
			}
			while(j<list2.size()){
				ALLlist.add(list2.get(j));
				j++;
			}
		return ALLlist;
		
	}
}
