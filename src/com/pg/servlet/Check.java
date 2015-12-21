package com.pg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
			 String c="no"+"yanzhengma";
			 out.write(c);
		}
		out.flush();
		out.close();
	}

}
