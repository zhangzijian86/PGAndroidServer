package com.pg.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pg.bean.Pgdr_user;
import com.pg.daoimpl.UserDaoImpl;
import com.pg.json.JsonUtil;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@SuppressWarnings("serial")
public class Register extends HttpServlet {


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
		String jsondata=request.getParameter("jsonstring");
		JsonUtil jsonUtil=new JsonUtil();
		System.out.println(jsondata);
		List<Pgdr_user> list=jsonUtil.StringFromJson(jsondata);
		Pgdr_user pgdr_user=list.get(0);
	    System.out.println(pgdr_user.getUser_mobile());
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		boolean b=userDaoImpl.register(pgdr_user);
		if (b) 
		{
			out.write("yes");
		}
		else {
			out.write("no");
		}
		out.flush();
		out.close();

	}

}
