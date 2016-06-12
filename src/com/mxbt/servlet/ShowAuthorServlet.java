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

import com.mxbt.beans.SubjectBean;
import com.mxbt.beans.Users;
import com.mxbt.dao.SelectAuthor;
import com.mxbt.dao.SelectSubject;

/**
 * Servlet implementation class ShowAuthorServlet
 */
@WebServlet("/showAuthorServlet")
public class ShowAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAuthorServlet() {
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
		List<Users> mList=null;
		SelectAuthor select=new SelectAuthor();
		PrintWriter pw=response.getWriter();
		mList=select.selectAllAuthor();
		if(mList!=null){
			Gson gson=new Gson();
			String result=gson.toJson(mList);
			pw.write(result);
			pw.close();
			System.out.println("已获取后台所有的Users！");
			System.out.println(result);
		}
	}

}
