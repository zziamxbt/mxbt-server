package com.mxbt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mxbt.beans.ArticleCommentBean;
import com.mxbt.dao.ArticleComment_excute;

/**
 * Servlet implementation class ArticeComment_Servlet
 */
@WebServlet("/ArticeComment_Servlet")
public class ArticeComment_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       PrintWriter printWriter;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticeComment_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		printWriter=response.getWriter();
		int Aid=Integer.parseInt(request.getParameter("Aid"));
		int Uid=Integer.parseInt(request.getParameter("Uid"));
        String createtime=request.getParameter("createtime");
        String content=request.getParameter("content");
        int replyed=Integer.parseInt(request.getParameter("replyed"));
        String flag=request.getParameter("flag");
		List<ArticleCommentBean> list=new ArrayList<ArticleCommentBean>();
		List<ArticleCommentBean> list2=new ArrayList<ArticleCommentBean>();
		List<ArticleCommentBean> ALLlist=new ArrayList<ArticleCommentBean>();
		ArticleComment_excute a1=new ArticleComment_excute();
		if(flag.equals("false")){
			if(createtime!=null&&content!=null){
				//插入评论表
				String as=new String(content.getBytes("iso-8859-1"),"UTF-8");
				a1.data_insert(Aid, Uid, createtime, as);
				System.out.println("插入执行了一次");
			}
		}else if(flag.equals("true")){
			if(replyed!=0&&createtime!=null&&content!=null){
				//插入回复表
				String as=new String(content.getBytes("iso-8859-1"),"UTF-8");
				a1.reply_insert(Aid,Uid, replyed, as, createtime);
			}
		}
		
		list=a1.data_select(Aid);
		list2=a1.reply_select(Aid);
		
		ALLlist=a1.getall(list, list2);
			
		Gson gson=new Gson();
		String result=gson.toJson(ALLlist);
		String result2=gson.toJson(list);
		String result3=gson.toJson(list2);
		System.out.println("NNN"+result);
		System.out.println("SSS"+result2);
		System.out.println("AAA"+result3);
		printWriter.write(result);
		printWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
