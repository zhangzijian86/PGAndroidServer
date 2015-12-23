package com.pg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pg.daoimpl.UserDaoImpl;


@SuppressWarnings("serial")
public class Check extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	   doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String user_mobile=request.getParameter("phoneNumber");
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		boolean b=userDaoImpl.check(user_mobile);
		if (b)  //手机号存在
		{
			String a="yesphoneNumber";
			out.write(a);
		}
		else  //手机号不存在
		{
			 Random rd = new Random();
			 int result =rd.nextInt(900000)+100000;
			 String c="no"+result;
			 out.write(c);
		}
		out.flush();
		out.close();
	}

}
