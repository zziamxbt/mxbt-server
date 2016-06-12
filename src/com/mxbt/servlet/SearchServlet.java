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
import com.mxbt.beans.BeautyBean;
import com.mxbt.beans.LableBean;
import com.mxbt.beans.SubjectBean;
import com.mxbt.beans.Users;
import com.mxbt.dao.SelectAuthor;
import com.mxbt.dao.SelectBeauty;
import com.mxbt.dao.SelectLable;
import com.mxbt.dao.SelectSubject;

/**
 * Servlet implementation class SearchThemeServlet
 */
@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		//String search="searchBeauty";
		String search=request.getParameter("search");
		if(search.equals("searchTheme")){
			List<SubjectBean> mList=null;
			SelectSubject select=new SelectSubject();
			PrintWriter pw=response.getWriter();
			mList=select.selectPartSubject();
			if(mList!=null){
				Gson gson=new Gson();
				String result=gson.toJson(mList);
				pw.write(result);
				pw.close();
				System.out.println("已获取后台部分专题Bean！");
				System.out.println(result);
			}
		}
		
		
		if(search.equals("searchLable")){
			List<LableBean> mList=null;
			SelectLable select=new SelectLable();
			PrintWriter pw=response.getWriter();
			mList=select.selectPartLable();
			if(mList!=null){
				Gson gson=new Gson();
				String result=gson.toJson(mList);
				pw.write(result);
				pw.close();
				System.out.println("已获取后台部分LableBean！");
				System.out.println(result);
			}
		}
		
		if(search.equals("searchAuthor")){
			List<Users> mList=null;
			SelectAuthor select=new SelectAuthor();
			PrintWriter pw=response.getWriter();
			mList=select.selectPartAuthor();
			if(mList!=null){
				Gson gson=new Gson();
				String result=gson.toJson(mList);
				pw.write(result);
				pw.close();
				System.out.println("已获取后台部分Users！");
				System.out.println(result);
			}
		}
		
		
		if(search.equals("searchBeauty")){
			List<BeautyBean> mList=null;
			SelectBeauty select=new SelectBeauty();
			PrintWriter pw=response.getWriter();
			mList=select.selectPartBeauty();
			if(mList!=null){
				Gson gson=new Gson();
				String result=gson.toJson(mList);
				pw.write(result);
				pw.close();
				System.out.println("已获取后台部分BeautyBean！");
				System.out.println(result);
			}
		}
		
		
	}

}
