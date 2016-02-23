<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.web.OrderManager"  %>
<%
String id= request.getParameter("id");
String phonenumber= request.getParameter("phonenumber");	
OrderManager om = new OrderManager();
int result = 0;
result = om.updateUser(id, phonenumber);
out.print(result);
%>

