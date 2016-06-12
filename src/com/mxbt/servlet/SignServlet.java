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
import com.mxbt.beans.SignBean;
import com.mxbt.beans.Write_ReadBean;
import com.mxbt.dao.GetSign;
import com.mxbt.dao.GetWrite_Chapter;

/**
 * Servlet implementation class SignServlet
 */
@WebServlet("/signServlet")
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter mPrintWriter;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignServlet() {
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

		int User_Id = Integer.valueOf(request.getParameter("Uid"));
		String flag=request.getParameter("Flag");
		System.out.println(flag+"XXXXXXXXXXXXXXXXXXX");
		mPrintWriter = response.getWriter();

		List<SignBean> mList = new ArrayList<SignBean>();
		GetSign mGetSign=new GetSign();
		if(flag.equals("true")){
			mList=mGetSign.getSign(User_Id);		
			Gson gson = new Gson();
			String result = gson.toJson(mList);
			System.out.println(result+"XXXXXXXXXXXXXXXXXXX");
			mPrintWriter.write(result);
			mPrintWriter.close();
		}else{
			mGetSign.UpdateSign(User_Id);
		}
		
	}
}
