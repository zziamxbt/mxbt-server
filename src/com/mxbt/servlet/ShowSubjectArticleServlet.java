package com.mxbt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mxbt.beans.IndexBean;
import com.mxbt.beans.SubjectArticleBean;
import com.mxbt.beans.SubjectBean;
import com.mxbt.dao.SelectSubject;
import com.mxbt.dao.ThemeContent;

/**
 * Servlet implementation class ShowSubjectArticle
 */
@WebServlet("/showSubjectArticleServlet")
public class ShowSubjectArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSubjectArticleServlet() {
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
		int  sid=Integer.parseInt(request.getParameter("sid"));
		List<SubjectArticleBean> mList=null;
		ThemeContent select=new ThemeContent();
		PrintWriter pw=response.getWriter();
		mList=select.selectAllArticles(sid);
		if(mList!=null){
			Gson gson=new Gson();
			String result=gson.toJson(mList);
			pw.write(result);
			pw.close();
			System.out.println("每个专题对应的文章已查询！");
			System.out.println(result.toString());
		}
		
		
	}

}
