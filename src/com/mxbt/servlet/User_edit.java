package com.mxbt.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.mxbt.beans.Users;
import com.mxbt.dao.ForEdit;

/**
 * Servlet implementation class User_edit
 */
@WebServlet("/user_edit")
public class User_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userJson = request.getParameter("user");
		Gson gson = new Gson();
	    Type type = new TypeToken<Users>() {
        }.getType();
        Users user = gson.fromJson(userJson, type);
        List<Integer> list = new ArrayList<>();
        ForEdit forEdit = new ForEdit();
        list = forEdit.editImg(user);
        int uHeadId = list.get(1);
        int uBk = list.get(0);
        forEdit.editUser(user, uHeadId, uBk);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
