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
import com.mxbt.dao.SelectSubject;

/**
 * Servlet implementation class ShowSubjectServlet
 */
@WebServlet("/showSubjectServlet")
public class ShowSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSubjectServlet() {
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
		List<SubjectBean> mList=null;
		SelectSubject select=new SelectSubject();
		PrintWriter pw=response.getWriter();
		mList=select.selectAllSubject();
		if(mList!=null){
			Gson gson=new Gson();
			String result=gson.toJson(mList);
			pw.write(result);
			pw.close();
			System.out.println("已获取后台所有的SubjectBean！");
			System.out.println(result);
		}
	}

}
