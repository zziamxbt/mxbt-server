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
import com.mxbt.dao.GetArticle_complete;

/**
 * Servlet implementation class Article_complete
 */
@WebServlet("/article_complete")
public class Article_complete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter mPrintWriter;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Article_complete() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 都是标示


		List<JavaBean_Article> mList = new ArrayList<JavaBean_Article>();
		GetArticle_complete mGetArticle = new GetArticle_complete();

		int aid = Integer.valueOf(request.getParameter("article_id"));
		System.out.print("文章id"+aid);
		int User_Id = Integer.valueOf(request.getParameter("User_Id"));
		System.out.print("用户id"+User_Id);
		mPrintWriter = response.getWriter();
		if (User_Id == 0) {
			mList = mGetArticle.SelectArticleNo(aid);
			Gson gson = new Gson();
			String result = gson.toJson(mList);
			System.out.print("吴龙飞"+result);
			mPrintWriter.write(result);
			mPrintWriter.close();
		} else {
			int Num = Integer.valueOf(request.getParameter("Num"));
			if (Num == 0) {
				mList = mGetArticle.SelectArticle(aid, User_Id);
				Gson gson = new Gson();
				String result = gson.toJson(mList);
				System.out.print("有用户id的："+result);
				mPrintWriter.write(result);
				mPrintWriter.close();
			} else if (Num == 1) {
				String RecommendNum = request.getParameter("RecommendNum");
				if (RecommendNum.equals("true")) {
					mGetArticle.ChargeRecommend(RecommendNum, aid, User_Id);
				} else {
					mGetArticle.ChargeRecommend(RecommendNum, aid, User_Id);
				}

			} else if (Num == 2) {
				String CollectNum = request.getParameter("CollectNum");
				if (CollectNum.equals("true")) {
					mGetArticle.ChargeCollect(CollectNum, aid, User_Id);
				} else {
					mGetArticle.ChargeCollect(CollectNum, aid, User_Id);
				}
			}

		}
	}

}
