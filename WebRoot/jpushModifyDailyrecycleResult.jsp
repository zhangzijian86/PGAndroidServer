<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.web.OrderManager"  %>
<%@ page import="com.pg.bean.Ppdr_dailyrecycle"  %>
<%
request.setCharacterEncoding("UTF-8");
String id= new String(request.getParameter("id").getBytes("iso-8859-1"), "utf-8");
String xingming= new String(request.getParameter("xingming").getBytes("iso-8859-1"), "utf-8");
String shoujihao= new String(request.getParameter("shoujihao").getBytes("iso-8859-1"), "utf-8");
String huishouriqi= new String(request.getParameter("huishouriqi").getBytes("iso-8859-1"), "utf-8");
String leixing= new String(request.getParameter("leixing").getBytes("iso-8859-1"), "utf-8");
String beizhu= new String(request.getParameter("beizhu").getBytes("iso-8859-1"), "utf-8");
String dizhi= new String(request.getParameter("dizhi").getBytes("iso-8859-1"), "utf-8");
Ppdr_dailyrecycle pdr= new Ppdr_dailyrecycle();
pdr.setDailyrecycle_id(id);
pdr.setDailyrecycle_name(xingming);
pdr.setDailyrecycle_user_mobile(shoujihao);
pdr.setDailyrecycle_date(huishouriqi);
pdr.setDailyrecycle_type(leixing);
pdr.setDailyrecycle_explain(beizhu);
pdr.setDailyrecycle_address(dizhi);
OrderManager om = new OrderManager();
int result = 0;
result = om.updateRecycle(pdr);
out.print(result);
%>

