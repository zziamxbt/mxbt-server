package com.mxbt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mxbt.beans.JavaBean_Article;
import com.mxbt.dao.GetArticle_complete;

/**
 * Servlet implementation class Article_complete
 */
@WebServlet("/article_complete")
public class Article_complete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter mPrintWriter;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Article_complete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int aid=Integer.valueOf(request.getParameter("article_id"));
		System.out.println(aid+"XXXXXXXXXXXXXXXXXXX");
		mPrintWriter=response.getWriter();
		
		List<JavaBean_Article> mList=new ArrayList<JavaBean_Article>();
		GetArticle_complete mGetArticle=new GetArticle_complete();
		
		mList=mGetArticle.SelectArticle(aid);
		
		Gson gson=new Gson();
		String result=gson.toJson(mList);
		mPrintWriter.write(result);
	    mPrintWriter.close();
	}

}
