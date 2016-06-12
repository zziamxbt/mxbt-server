package com.mxbt.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mxbt.dao.ForCreate;

/**
 * Servlet implementation class ArticleCreater
 */
@WebServlet("/articlecreater")
public class ArticleCreater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleCreater() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String img = request.getParameter("img");
		String title = request.getParameter("title");
		String kind = request.getParameter("kind");
		String content = request.getParameter("content");
		int uid = Integer.parseInt(request.getParameter("uid"));
		System.out.println(img+"  "+title+"  "+kind+"   "+content);
		System.out.println("end");
		
		ForCreate forCreate = new ForCreate();
		
		int imgKey = forCreate.insertImg(img);
		
		int textKey = forCreate.insertText(content);
		
		int kid=1;
		if(kind.equals("真事")){
			kid=1;
		}else if(kind.equals("创作")){
			kid=2;
		}else if(kind.equals("灵异")){
			kid=3;
		}else if(kind.equals("生活")){
			kid=4;
		}
		
	
		
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String time = dateFormat.format( now ); 
		System.out.println(time);
		
		
		int articleKey = forCreate.insertArticle(uid, kid, imgKey, title,time );
		
		
		forCreate.insertChapter(uid, articleKey, textKey, 1, "开篇", time);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request , response);
	}

}
