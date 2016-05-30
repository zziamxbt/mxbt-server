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
import com.mxbt.beans.rank_author;
import com.mxbt.beans.rank_novel;
import com.mxbt.dao.rank_excute;
import com.mxbt.dao.rank_excute2;

/**
 * Servlet implementation class user_servlet
 */
@WebServlet("/user_servlet")
public class user_servlet extends HttpServlet {
	PrintWriter printWriter;
	String dropflag;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public user_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		dropflag = request.getParameter("drop");
		printWriter = response.getWriter();
		System.out.println("AAAAAAAAAA"+dropflag);
		if (dropflag == null) {
			//默认为关注度页面
//			rank_excute d1 = new rank_excute();
//			List<rank_author> list = new ArrayList<rank_author>();
//
//			list = d1.getfocus();
//			// System.out.println("AAAAAAAAAAAAA"+list.get(1).getHead());
//			Gson gson = new Gson();
//			String result = gson.toJson(list);
//			printWriter.write(result);
//			printWriter.close();	
//			
			rank_excute d1 = new rank_excute();
			List<rank_author> list = new ArrayList<rank_author>();

			list = d1.getwritebest();
			// System.out.println("AAAAAAAAAAAAA"+list.get(1).getHead());
			Gson gson = new Gson();
			String result = gson.toJson(list);
			System.out.println("WWW"+result);
			printWriter.write(result);
			printWriter.close();
			
		} else {
			if (dropflag.equals("focus")) {
				rank_excute d1 = new rank_excute();
				List<rank_author> list = new ArrayList<rank_author>();

				list = d1.getfocus();
				// System.out.println("AAAAAAAAAAAAA"+list.get(1).getHead());
				Gson gson = new Gson();
				String result = gson.toJson(list);
				System.out.println("WWW"+result);
				printWriter.write(result);
				printWriter.close();
			}
			if (dropflag.equals("good")) {
				
				rank_excute d1 = new rank_excute();
				List<rank_author> list = new ArrayList<rank_author>();
				list = d1.getgood();
				Gson gson = new Gson();
				String result = gson.toJson(list);
				System.out.println("WWW"+result);
				printWriter.write(result);
				printWriter.close();
			}
			if (dropflag.equals("writebest")) {
				rank_excute d1 = new rank_excute();
				List<rank_author> list = new ArrayList<rank_author>();

				list = d1.getwritebest();
				// System.out.println("AAAAAAAAAAAAA"+list.get(1).getHead());
				Gson gson = new Gson();
				String result = gson.toJson(list);
				System.out.println("WWW"+result);
				printWriter.write(result);
				printWriter.close();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
