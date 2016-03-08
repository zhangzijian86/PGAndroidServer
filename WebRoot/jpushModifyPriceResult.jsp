<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.web.OrderManager"  %>
<%@ page import="com.pg.bean.Pgdr_price"  %>
<%
request.setCharacterEncoding("UTF-8");
String id= new String(request.getParameter("id").getBytes("iso-8859-1"), "utf-8");
String mingcheng= new String(request.getParameter("mingcheng").getBytes("iso-8859-1"), "utf-8");
String jiage= new String(request.getParameter("jiage").getBytes("iso-8859-1"), "utf-8");
String shuoming= new String(request.getParameter("shuoming").getBytes("iso-8859-1"), "utf-8");
Pgdr_price pp= new Pgdr_price();
pp.setPrice_id(id);
pp.setPrice_name(mingcheng);
pp.setPrice_price(jiage);
pp.setPrice_explain(shuoming);
OrderManager om = new OrderManager();
int result = 0;
result = om.updateOnePrice(pp);
out.print(result);
%>

