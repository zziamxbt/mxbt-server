package com.mxbt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mxbt.dao.SelectUser;

/**
 * Servlet implementation class RegistCheckServlet
 */
@WebServlet("/registCheckServlet")
public class RegistCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistCheckServlet() {
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
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		System.out.println("帐号：" + username + "密码：" + password);
		SelectUser s = new SelectUser();
		boolean config = s.selecUser(username);
		PrintWriter pw = response.getWriter();
		if (username == null || password == null) {
			System.out.println("Failure,帐号和密码不能为空！");
		}	
		else if (config) {
			// 返回true,说明查到该用户，跳转到注册失败页面
			pw.write("Failure,该手机号已被注册！");
			System.out.println("Failure,该手机号已被注册！");
		}
		else {
			//返回false,说明没有此用户，跳转到注册成功页面\
			String token=s.gettoken(username);
			s.addUser(username, password,token);

			pw.write("Success,注册用户成功！");
			System.out.println("Success,注册用户成功！");
		}
		
	}

}
