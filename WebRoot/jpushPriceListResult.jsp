<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.web.OrderManager"  %>
<%
String id= request.getParameter("id");
OrderManager om = new OrderManager();
int result = 0;
result = om.deletePrice(id);
out.print(result);
%>

