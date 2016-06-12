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
import com.mxbt.beans.SearchBean;
import com.mxbt.dao.SelectBeauty;
import com.mxbt.dao.SelectSearch;

/**
 * Servlet implementation class ShowSearchServlet
 */
@WebServlet("/showSearchServlet")
public class ShowSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSearchServlet() {
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
		
		List<SearchBean> mList=null;
		SelectSearch select=new SelectSearch();
		PrintWriter pw=response.getWriter();
		mList=select.selectAllSearch();
		if(mList!=null){
			Gson gson=new Gson();
			String result=gson.toJson(mList);
			pw.write(result);
			pw.close();
			System.out.println("已获取后台所有的SearchBean！"+mList.size());
			System.out.println(result);
		}
	}

}
