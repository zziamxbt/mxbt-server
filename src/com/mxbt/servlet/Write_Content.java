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
import com.mxbt.beans.Write_ReadBean;
import com.mxbt.dao.GetArticle_complete;
import com.mxbt.dao.GetWrite_Chapter;

/**
 * Servlet implementation class Write_Content
 */
@WebServlet("/write_Content")
public class Write_Content extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter mPrintWriter;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Write_Content() {
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
		

		int AWid=Integer.valueOf(request.getParameter("AWid"));
		System.out.println(AWid+"XXXXXXXXXXXXXXXXXXX");
		mPrintWriter=response.getWriter();
		
		List<Write_ReadBean> mList=new ArrayList<Write_ReadBean>();
		GetWrite_Chapter getWrite_Chapter=new GetWrite_Chapter();
		mList=getWrite_Chapter.SelectWrite_Chapter(AWid);

		Gson gson=new Gson();
		String result=gson.toJson(mList);
		mPrintWriter.write(result);
	    mPrintWriter.close();
	    
	    System.out.println(result);
		
	}

}
