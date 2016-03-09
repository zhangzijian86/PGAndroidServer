<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.bean.Ppdr_dailyrecycle"  %>
<%@ page import="com.pg.web.OrderManager"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String currentPage = "0";
if(request.getParameter("currentPage")!=null){
	currentPage = new String(request.getParameter("currentPage").getBytes("ISO-8859-1"),"utf-8");
}
String eachPage = "10";
if(request.getParameter("eachPage")!=null){
	eachPage = new String(request.getParameter("eachPage").getBytes("ISO-8859-1"),"utf-8");
}
int pageCount = 0;
int count = 0;
Ppdr_dailyrecycle[] pdr = null;
OrderManager udi = new OrderManager();
count = udi.getCount("PGDR_DAILYRECYCLE","where DAILYRECYCLE_ISVALID = 1");
pdr = udi.getRecycle(currentPage,eachPage);
pageCount =  (new Double(Math.ceil(((double)count/Double.valueOf(eachPage))))).intValue();
%>
<html>
<head>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>订单数据列表</title>
    	<!-- Bootstrap -->
    	<link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    	<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
        <script type="text/javascript" src="js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	</head>
    <body>
    	<form class="form-horizontal">
        	<table class="table table-hover table-bordered">
            	<thead>
                	<tr>
                        <th colspan="2"><h3>订单数据列表</h3></th>
                        <th colspan="4"></th>
                        <th><h3><a href="jpushPriceList.jsp">价格列表</a></h3></th>   
                        <th><h3><a href="jpushUserList.jsp">人员列表</a></h3></th>                        
                    </tr>
                </thead>
                <tbody>
				 <tr class="warning">
                        <td width="10%">
                        	手机号
                        </td >
                        <td width="8%">
                        	姓名
                        </td>
                        <td width="12%">
                        	日期
                        </td> 
                        <td width="8%">
                        	周期
                        </td> 
                        <td width="32%">
                        	地址
                        </td> 
                        <td width="10%">
                        	回收人手机号
                        </td> 
                        <td width="10%">
                        	类型
                        </td>
                        <td width="10%">
                        	状态
                        </td>
                    </tr>
                    <%if(pdr!=null&&pdr.length>0){
						for(int i = 0;i<pdr.length;i++){ %>
                    <tr
                    <%if(i%3==0){ %>
                     class="success"
                     <%} %>
                     <%if(i%3==1){ %>
                     class="error"
                     <%} %>
                     <%if(i%3==2){ %>
                     class="info"
                     <%} %>
                    >

                        <td width="10%">
                        	<a href="javascript:modify(<%=pdr[i].getDailyrecycle_id()%>);" target="_blank"><%out.print(pdr[i].getDailyrecycle_user_mobile()); %></a>         
                        </td >
                        <td width="8%">
                            <%
                    		if(pdr[i].getDailyrecycle_name()==null){
                    			
                    		}else{
                    			out.print(pdr[i].getDailyrecycle_name());
                    		} %>             	
                        </td>
                        <td width="12%">
                            <%
                    		if(pdr[i].getDailyrecycle_date()==null){
                    			
                    		}else{
                    			out.print(pdr[i].getDailyrecycle_date());
                    		} %>  
                        </td> 
                        <td width="8%">
                        <%
                        if(pdr[i].getDailyrecycle_iscycle()!=null){
	                        if(pdr[i].getDailyrecycle_iscycle().equals("1")){
	                        	 if(pdr[i].getDailyrecycle_cycletype()!=null){
										if(pdr[i].getDailyrecycle_cycletype().equals("0")){
					                    %>
			                        		每周
			                        	<%
										}else{
						                %>
			                        		每月
			                        	<%
										}
	                        	  }else{
	                                  %>
		                    			非周期
		                    		<%
	                        	  }
	                        }else{
				                %>
	                    			非周期
	                    		<%
	                        	}
                        }%>
                        </td> 
                        <td width="32%">                        
                        	<%
                    		if(pdr[i].getDailyrecycle_address()==null){
                    			
                    		}else{
                    			out.print(pdr[i].getDailyrecycle_address());
                    		} %>
                        </td> 
                        <td width="10%">
                            <%
                    		if(pdr[i].getDailyrecycle_recyclingmanphone()==null){
                    			
                    		}else{
                    			out.print(pdr[i].getDailyrecycle_recyclingmanphone());
                    		} %>
                        </td> 
                        <td width="10%">
                            <%
                    		if(pdr[i].getDailyrecycle_type()==null){
                    			
                    		}else{
                    			out.print(pdr[i].getDailyrecycle_type());
                    		} %>                      	
                        </td>
                           <td width="10%">
                            <%if(pdr[i].getDailyrecycle_status()!=null&&pdr[i].getDailyrecycle_status().equals("0")){
                        	%>
                        	<a href="javascript:confirm(<%=pdr[i].getDailyrecycle_id()%>);" target="_blank">订单未完成</a>
                        	<%
                        	}else{
                            	%>
                        		订单完成
                        		<%
                        	}%>                        	
                        </td>
                    </tr>
                    <%}
					}%>
					<tr class="warning">
                        <td colspan="2" >

                        </td >
                        <td colspan="4" align="center">
                        <a href="javascript:paging(1);" target="_blank">首页</a>
                        		<%
                        		for(int j = 1; j<pageCount+1 ; j++){ 
                        		%>
                        		<a href="javascript:paging(<%=j%>);" target="_blank"><%=j%></a>
                        		<%
                        		}
                        		%>
						<a href="javascript:paging(<%=pageCount%>);" target="_blank">末页</a>
                        </td>
                        <td colspan="2" >

                        </td> 
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
<script type="text/javascript">
	function paging(currentPage) {
		window.location.href = "jpushDailyrecycleList.jsp?currentPage="+currentPage+"&eachPage=10";
	}
	function confirm(id) {
		window.open("jpushDailyrecycleManList.jsp?id=" + id, "",
				"height=700, width=800");
	}
	function modify(id) {
		window.open("jpushModifyDailyrecycle.jsp?id=" + id, "",
				"height=700, width=800");
	}
</script>
</html>
