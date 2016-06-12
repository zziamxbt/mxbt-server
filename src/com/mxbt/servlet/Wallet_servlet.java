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
import com.mxbt.beans.WalletBean;
import com.mxbt.dao.GetArticle_complete;
import com.mxbt.dao.GetWallet;

/**
 * Servlet implementation class Wallet_servlet
 */
@WebServlet("/wallet_servlet")
public class Wallet_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter mPrintWriter;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Wallet_servlet() {
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
		int Uid = 0;
		int goldNum = 0;
		int authorId=0;
		String gold = request.getParameter("gold");

		List<WalletBean> mList = new ArrayList<WalletBean>();
		GetWallet mGetWallet = new GetWallet();

		if (gold.equals("true")) {
			if (request.getParameter("goldNum") == null) {
				Uid = Integer.valueOf(request.getParameter("User_id"));
			} else {
				Uid = Integer.valueOf(request.getParameter("User_id"));
				goldNum = Integer.valueOf(request.getParameter("goldNum"));
				mGetWallet.SetGoldNum(Uid, goldNum);
			}
		} else {
			if (request.getParameter("goldNum") == null) {
				Uid = Integer.valueOf(request.getParameter("User_id"));
			} else {
				Uid = Integer.valueOf(request.getParameter("User_id"));
				goldNum = Integer.valueOf(request.getParameter("goldNum"));
				authorId=Integer.valueOf(request.getParameter("Authorid"));
				mGetWallet.SetRewardGold(Uid, authorId, goldNum);
			}

		}

		mPrintWriter = response.getWriter();

		mList = mGetWallet.getWallet(Uid);

		Gson gson = new Gson();
		String result = gson.toJson(mList);
		mPrintWriter.write(result);
		mPrintWriter.close();

	}

}
