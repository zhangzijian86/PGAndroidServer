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
import com.pg.bean.Ppdr_dailyrecycle;
import com.pg.daoimpl.UserDaoImpl;


@SuppressWarnings("serial")
public class GetRecycle extends HttpServlet {

	
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
		String phoneNumber=request.getParameter("phoneNumber");
		System.out.println("===GetRecycle===phoneNumber========="+phoneNumber);
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		List<Ppdr_dailyrecycle> list1=userDaoImpl.getRecycle(phoneNumber);
		Gson gson=new Gson();
		String jsonstring=gson.toJson(list1);
		System.out.println("===GetRecycle===jsonstring========="+jsonstring);
		out.write(jsonstring);
		out.flush();
		out.close();
	}

}
