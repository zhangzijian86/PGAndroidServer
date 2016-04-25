<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.web.OrderManager"  %>
<%@ page import="com.pg.bean.Pgdr_price"  %>
<%
request.setCharacterEncoding("UTF-8");
String shouji= new String(request.getParameter("shouji").getBytes("iso-8859-1"), "utf-8");
OrderManager om = new OrderManager();
int result = 0;
result = om.addAkeyRecycle(shouji);
out.print(result);
%>

