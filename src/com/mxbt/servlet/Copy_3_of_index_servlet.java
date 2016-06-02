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
import com.mxbt.beans.IndexBean;
import com.mxbt.dao.ForIndex;

/**
 * Servlet implementation class index_servlet
 */
@WebServlet("/copyofindex3")
public class Copy_3_of_index_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name=null;
	PrintWriter mPrintWriter;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Copy_3_of_index_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			mPrintWriter = response.getWriter();
		
			ForIndex forindex = new ForIndex();
			List<IndexBean> list = new ArrayList<IndexBean>();
			
			list= forindex.getIndexData();
			Gson gson = new Gson();
			String result  = gson.toJson(list);
			mPrintWriter.write(result);
			mPrintWriter.close();
			System.out.println(result);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
