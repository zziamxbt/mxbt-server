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
 * Servlet implementation class novel_servlet
 */
@WebServlet("/novel_servlet")
public class novel_servlet extends HttpServlet {
	PrintWriter printWriter;
	String dropflag;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public novel_servlet() {
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
		dropflag = request.getParameter("drop1");
		printWriter = response.getWriter();
		System.out.println("BBB" + dropflag);
		if (dropflag == null) {
			// 默认为上周最多页面
			rank_excute2 d2 = new rank_excute2();
			List<rank_novel> list = new ArrayList<rank_novel>();

			list = d2.getweekbest();
			Gson gson = new Gson();
			String result = gson.toJson(list);
			printWriter.write(result);
			printWriter.close();

		} else {

			if (dropflag.equals("lastweekbest")) {
				rank_excute2 d2 = new rank_excute2();
				List<rank_novel> list = new ArrayList<rank_novel>();

				list = d2.getweekbest();
				Gson gson = new Gson();
				String result = gson.toJson(list);
				System.out.println("AA" + result);
				printWriter.write(result);
				printWriter.close();
			}
			if (dropflag.equals("historybest")) {
				rank_excute2 d2 = new rank_excute2();
				List<rank_novel> list = new ArrayList<rank_novel>();

				list = d2.getbest();
				Gson gson = new Gson();
				String result = gson.toJson(list);
				System.out.println("BB" + result);
				printWriter.write(result);
				printWriter.close();
			}
			if (dropflag.equals("writerbest")) {
				rank_excute2 d2 = new rank_excute2();
				List<rank_novel> list = new ArrayList<rank_novel>();

				list = d2.getwriterbest();
				// System.out.println("AAAAAAAAAAAAA"+list.get(1).getHead());
				Gson gson = new Gson();
				String result = gson.toJson(list);
				System.out.println("CC" + result);
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
		doGet(request, response);

	}

}
