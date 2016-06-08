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
import com.mxbt.beans.SubjectArticleBean;
import com.mxbt.dao.LableContent;
import com.mxbt.dao.ThemeContent;

/**
 * Servlet implementation class ShowLableArticleServlet
 */
@WebServlet("/showLableArticleServlet")
public class ShowLableArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLableArticleServlet() {
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
		//int Lid=1;
		int  Lid=Integer.parseInt(request.getParameter("Lid"));
		List<SubjectArticleBean> mList=null;
		LableContent select=new LableContent();
		PrintWriter pw=response.getWriter();
		mList=select.selectAllArticles(Lid);
		if(mList!=null){
			Gson gson=new Gson();
			String result=gson.toJson(mList);
			pw.write(result);
			pw.close();
			System.out.println("标签id为："+Lid+"对应的文章已查询！");
			System.out.println(result.toString());
		}
	}

}
