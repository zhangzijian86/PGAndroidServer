<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.web.OrderManager"  %>
<%@ page import="com.pg.bean.Pgdr_user"  %>
<%
request.setCharacterEncoding("UTF-8");
String id= new String(request.getParameter("id").getBytes("iso-8859-1"), "utf-8");
String xingming= new String(request.getParameter("xingming").getBytes("iso-8859-1"), "utf-8");
String shoujihao= new String(request.getParameter("shoujihao").getBytes("iso-8859-1"), "utf-8");
String dianziyouxiang= new String(request.getParameter("dianziyouxiang").getBytes("iso-8859-1"), "utf-8");
String dizhi= new String(request.getParameter("dizhi").getBytes("iso-8859-1"), "utf-8");
Pgdr_user pu= new Pgdr_user();
pu.setUser_id(id);
pu.setUser_name(xingming);
pu.setUser_mobile(shoujihao);
pu.setUser_email(dianziyouxiang);
pu.setUser_address(dizhi);
OrderManager om = new OrderManager();
int result = 0;
result = om.updateOneUser(pu);
out.print(result);
%>

