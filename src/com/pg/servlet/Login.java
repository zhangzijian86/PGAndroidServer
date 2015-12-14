package com.pg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pg.daoimpl.UserDaoImpl;


@SuppressWarnings("serial")
public class Login extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	    PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		out.write("登录成功==0="+username); 
		out.write("登录成功==1="+password); 
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		boolean b=userDaoImpl.login(username,password);
		if (b) 
		{
			out.write("登录成功");
		}
		else 
		{
			out.write("登录失败");
		}
		out.flush();
		out.close();
	}

}
