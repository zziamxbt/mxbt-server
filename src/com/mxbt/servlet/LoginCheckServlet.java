package com.mxbt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mxbt.beans.Users;
import com.mxbt.dao.SelectUser;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/loginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheckServlet() {
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
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		System.out.println("帐号："+username+"密码："+password);
		SelectUser s=new SelectUser();
		Users user=s.loginUser(username, password);
		PrintWriter pw=response.getWriter();
		//返回true,说明查到该用户，success
		if(user!=null){	
			Gson gson = new Gson();
			String result  = gson.toJson(user);
			pw.write(result);
			pw.close();
			System.out.println("登陆成功！");
		}
		else {
			//返回false,说明没有此用户，failure
			System.out.println("登录失败，请重新登录！");
			
		}
	}

}
