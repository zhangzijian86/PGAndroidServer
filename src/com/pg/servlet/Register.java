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
import com.pg.bean.User;
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
		List<User> list=jsonUtil.StringFromJson(jsondata);
		User user=list.get(0);
	    System.out.println(user.getSex());
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		boolean b=userDaoImpl.register(user);
		
		List<User> list1=new ArrayList<User>();
		User user1=new User("444", "ddd", "nan", "88","aaa");
		User user2=new User("555", "eee", "nan", "99","bbb");
		User user3=new User("666", "fff", "nan", "00","ccc");
		Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串		
		list1.add(user1);
		list1.add(user2);
		list1.add(user3);
		String jsonstring=gson.toJson(list1);
		System.out.println("======jsonstring========="+jsonstring);
		out.write(jsonstring);
//		if (b) 
//		{
//			out.write("t");
//		}
//		else {
//			out.write("f");
//		}
		out.flush();
		out.close();

	}

}
