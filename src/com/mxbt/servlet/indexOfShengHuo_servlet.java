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
@WebServlet("/indexofshenghuo_servlet")
public class indexOfShengHuo_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name=null;
	PrintWriter mPrintWriter;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexOfShengHuo_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String mySelect = request.getParameter("select");
			if(mySelect.equals("newest")){
			mPrintWriter = response.getWriter();
		
			ForIndex forindex = new ForIndex();
			List<IndexBean> list = new ArrayList<IndexBean>();
			
			list= forindex.getIndexDataOfShengHuoByNewest();
			Gson gson = new Gson();
			String result  = gson.toJson(list);
			mPrintWriter.write(result);
			mPrintWriter.close();
			}else if(mySelect.equals("hotest")){
				mPrintWriter = response.getWriter();
				
				ForIndex forindex = new ForIndex();
				List<IndexBean> list = new ArrayList<IndexBean>();
				
				list= forindex.getIndexDataOfShengHuoByHotest();
				Gson gson = new Gson();
				String result  = gson.toJson(list);
				mPrintWriter.write(result);
				mPrintWriter.close();
			
			}
			
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
