<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.pg.bean.Pgdr_user"  %>
<%@ page import="com.pg.web.OrderManager"  %>
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
Pgdr_user [] pu = null;
OrderManager om = new OrderManager();
pu = om.getApplyUsers(currentPage,eachPage);
count = om.getCount("PGDR_USER","");
pageCount =  (new Double(Math.ceil(((double)count/Double.valueOf(eachPage))))).intValue();
%>
<html>
<head>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>申请人员列表</title>
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
    	<form class="form-horizontal" id = "ajaxFrm" >
        	<table class="table table-hover table-bordered">
            	<thead>
                	<tr>
                        <th colspan="3"><h3>申请人员列表</h3></th>  
                        <th><h3><a href="javascript:history.go(-1)">返回</a></h3></th>                                               
                    </tr>
                </thead>
                <tbody>
				 <tr class="warning">
                        <td width="20%">
                        	手机号
                        </td >
                       <td width="15%">
                        	姓名
                        </td>
                        <td width="50%">
                        	地址
                        </td> 
                        <td width="15%">
                        	审核状态
                        </td>
                    </tr>
                    <%if(pu!=null&&pu.length>0){
						for(int i = 0;i<pu.length;i++){ %>
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
                        <td width="20%">
                        	<a href="javascript:modify(<%=pu[i].getUser_id()%>);" target="_blank"><%out.print(pu[i].getUser_mobile()); %></a>
                        </td >
                        <td width="15%">
                    		<%
                    		if(pu[i].getUser_name()==null){
                    			
                    		}else{
                    			out.print(pu[i].getUser_name());
                    		}
                    		 %>
                        </td>
                        <td width="50%">
                        	<%
                    		if(pu[i].getUser_address()==null){
                    			
                    		}else{
                    			out.print(pu[i].getUser_address());
                    		} %>
                        </td>
                        <td width="15%">                        	
                        	<%if(pu[i].getUser_type().equals("1")){
                        	%>
                        		<a href="javascript:confirm(<%=pu[i].getUser_mobile()%>);" target="_blank">审核通过</a>
                        	<%
                        	}else if(pu[i].getUser_type().equals("2")){
                            	%>
                        		已审核通过的小贩
                        		<%
                        	}else{
                            	%>
                        		普通用户
                        		<%
                        	}%>             
                        </td>  
                    </tr>
                    <%}
					}%>
					<tr class="warning">
                        <td colspan="1" >

                        </td >
                        <td colspan="2" align="center">
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
                        <td colspan="1" >

                        </td> 

                    </tr>
                </tbody>
            </table>
        </form>
    </body>
<script type="text/javascript" src="js/jquery-1.4.1.js"></script>
<script type="text/javascript">

	function paging(currentPage) {
		window.location.href = "jpushUserList.jsp?currentPage="
				+ currentPage + "&eachPage=10";
	}
	function confirm(phonenumber) {
		$.ajax({
			type : "Post",
			url : "jpushUserListResult.jsp?phonenumber=" + phonenumber,
			dataType : "html",
			data : {
			//organiseUnitID: selorganiseUnitID,
			//CharType: 'CockiptTrendChange'
			},
			success : function(data) {
				if (data > 0) {
					window.location.reload();
				} else {
					alert("审核失败请重试");
				}
			},
			error : function(msg) {
				alert("审核失败请重试");
			}
		});
	}
	function modify(id) {
		window
				.open("jpushModifyUser.jsp?id=" + id, "",
						"height=700, width=800");
	}
</script>
</html>

