<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.pg.bean.Ppdr_dailyrecycle"  %>
<%@ page import="com.pg.web.OrderManager"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Ppdr_dailyrecycle[] pdr = null;
OrderManager udi = new OrderManager();
pdr = udi.getRecycle();
%>
<html>
<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>推送数据列表</title>
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
                        <th><h3>推送数据列表</h3></th>
                        <th colspan="4"></th>
                        <th><h3><a href="jpushContentNewPush.jsp">新数据推送</a></h3></th>                        
                    </tr>
                </thead>
                <tbody>
				 <tr class="warning">
                    	<td width="15%">
                        	标题
                        </td>
                        <td width="15%">
                        	内容
                        </td >
                        <td width="15%">
                        	发布日期
                        </td> 
                        <td width="15%">
                        	类别
                        </td> 
                        <td width="15%">
                        	推送tags
                        </td> 
                        <td width="15%">
                        	推送时间
                        </td> 
                        <td width="10%">
                        	操作
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
                    	<td width="15%">
                        	<%out.print(pdr[i].getDailyrecycle_name()); %>
                        </td>
                        <td width="15%">
                        	<%out.print(pdr[i].getDailyrecycle_date()); %>
                        </td >
                        <td width="15%">
                        	<%out.print(pdr[i].getDailyrecycle_user_mobile()); %>
                        </td> 
                        <td width="15%">
                        <%
                        if(pdr[i].getDailyrecycle_user_mobile().equals("laonianjibing")){
                        %>
                        		老年疾病
                        <%}
                        if(pdr[i].getDailyrecycle_user_mobile().equals("laonianyongyao")){
                        %>
                        		老年用药
                        <%}
                        if(pdr[i].getDailyrecycle_user_mobile().equals("laonianhuli")){                       
                        %>
                        		老年护理
                        <%}%>
                        </td> 
                        <td width="15%">
                        
                        	<%if(pdr[i].getDailyrecycle_user_mobile()==""&&pdr[i].getDailyrecycle_user_mobile()==""){
                        	%>
                        		暂未推送
                        	<%
                        	}else{
                        		out.print(pdr[i].getDailyrecycle_user_mobile()); 
                        	}%>
                        </td> 
                        <td width="15%">
                            <%if(pdr[i].getDailyrecycle_user_mobile()==""&&pdr[i].getDailyrecycle_user_mobile()==""){
                        	%>
                        		暂未推送
                        	<%
                        	}else{
                        		out.print(pdr[i].getDailyrecycle_user_mobile()); 
                        	}%>
                        </td> 
                        <td width="10%">
                            <%if(pdr[i].getDailyrecycle_user_mobile()==""&&pdr[i].getDailyrecycle_user_mobile()==""){
                        	%>
                        	<a href="jpushContentListPush.jsp?title=<%=pdr[i].getDailyrecycle_user_mobile()%>&content=<%=pdr[i].getDailyrecycle_user_mobile()%>&id=<%=pdr[i].getDailyrecycle_user_mobile()%>">操作</a>
                        	<%
                        	}else{
                            	%>
                        		推送完成
                        		<%
                        	}%>
                        	
                        </td>
                    </tr>
                    <%}
					}%>
                </tbody>
            </table>
        </form>
    </body>
</html>
