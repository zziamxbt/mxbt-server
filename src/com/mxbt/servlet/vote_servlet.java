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
import com.mxbt.beans.vote_content;
import com.mxbt.dao.vote_excute;

/**
 * Servlet implementation class vote_servlet
 */
@WebServlet("/vote_servlet")
public class vote_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       PrintWriter printWriter;
       boolean F;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vote_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		printWriter=response.getWriter();
		int AWid=Integer.parseInt(request.getParameter("AWid"));
		String flag=request.getParameter("flag");
		String Cid=request.getParameter("Cid");
		response.setCharacterEncoding("UTF-8");

		
		vote_excute v1=new vote_excute();
		List<vote_content> list=new ArrayList<vote_content>();
		if(flag!=null&&AWid!=0){
			//开始对赞表进行计数，并插入到续写表中
			v1.select_good_insert(AWid);
		}
		
		
		list=v1.getdata(Cid);
		Gson gson=new Gson();
		String result=gson.toJson(list);
		System.out.println("AAA"+result);
		printWriter.write(result);
		printWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
