package com.mxbt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mxbt.dao.AndWrite_insert;

/**
 * Servlet implementation class AndWrite_InsertServlet
 */
@WebServlet("/AndWrite_InsertServlet")
public class AndWrite_InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndWrite_InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int Uid=Integer.parseInt(request.getParameter("Uid"));
		int Cid=Integer.parseInt(request.getParameter("Cid"));
		String path=request.getParameter("path");
		String title=request.getParameter("title");
		String as=new String(title.getBytes("iso-8859-1"),"UTF-8");
		String createtime=request.getParameter("createtime");
		String isfinish=request.getParameter("isfinish");
		String asd=new String(isfinish.getBytes("iso-8859-1"),"UTF-8");
		if(path!=null&&title!=null&&createtime!=null&&isfinish!=null){
			AndWrite_insert a1=new  AndWrite_insert();
			a1.insertdata(Uid, Cid, path, as, createtime, asd);	
			System.out.println("SS"+Uid+":"+ Cid+":"+ path+":"+ as+":"+ createtime+":"+asd);
		}
		
		System.out.println(""+Uid+":"+ Cid+":"+ path+":"+ as+":"+ createtime+":"+asd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
