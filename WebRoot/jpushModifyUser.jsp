<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.pg.bean.Pgdr_user"  %>
<%@ page import="com.pg.web.OrderManager"  %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id= new String(request.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
OrderManager om = new OrderManager();
Pgdr_user  pu = om.getOneUser(id);
String user_name = "";
String user_mobile = "";
String user_address = "";
String user_email = "";
String user_type = "";
if(pu!=null){
	if(pu.getUser_name()!=null){
		user_name = pu.getUser_name();
	}
	if(pu.getUser_mobile()!=null){
		user_mobile = pu.getUser_mobile();
	}
	if(pu.getUser_email()!=null){
		user_email = pu.getUser_email();
	}
	if(pu.getUser_type()!=null){
		user_type = pu.getUser_type();
	}
	if(pu.getUser_address()!=null){
		user_address = pu.getUser_address();
	}
}
%>
<html>
<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>订单修改</title>
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
    	<form class="form-horizontal"  method="post"
			action="jpushContentEntryResult.jsp"
			 name="form1" id="form1">
        	<table class="table table-hover">
            	<thead>
                	<tr>
                        <th><h3>订单修改</h3></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">姓名</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="xingming" size="10"  placeholder="姓名"  value =<%=user_name %> >
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">手机号</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="shoujihao" size="10"  placeholder="手机号" value =<%=user_mobile %> >
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">电子邮箱</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="dianziyouxiang" size="10"  placeholder="电子邮箱"  value ="<%=user_email %>" >
							</div>
                        </td>
                    </tr>                
                                            <%
                        if(user_type.equals("0")){
		                    %>
                        	<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">用户类型</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" readonly size="10" value ="普通用户" >
								</div>
	                        </td>
                    		</tr>  
                        	<%
							}else if(user_type.equals("1")){
			                %>
                        	<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">用户类型</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" readonly size="10" value ="在申请状态用户" >
								</div>
	                        </td>
                    		</tr>                         		
                        	<%
							}else{
			                %>
			                <tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">用户类型</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" readonly size="10" value ="小贩" >
								</div>
	                        </td>
                    		</tr>   
			                <%
							}
			                %>
                     <tr>                    
                    		<td>
                      		<label class="control-label" for="inputEmail">地址</label>
                            <div class="controls">
                                <textarea class="input-xxlarge" id="dizhi"  rows="3" placeholder="地址"  ><%=user_address%></textarea>
                            </div>
                        	</td>
                     </tr>                
                    <tr>
                    	<td>
                        	<div class="controls">                        	    
                        	    <input type="hidden" value="" name="title" id ="title" >
                        	    <input type="hidden" value="" name="content" id ="content" >
                        	    <input type="hidden" value="" name="group" id ="group" >
                                <button class="btn btn-primary"  type="button" onclick="confirm()">确定</button>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
<script type="text/javascript">
	function confirm()
	{
		var xingming = document.getElementById("xingming");
		var shoujihao = document.getElementById("shoujihao");
		var dianziyouxiang =  document.getElementById("dianziyouxiang");
		var dizhi =  document.getElementById("dizhi");
	    $.ajax({
	        type: "Post",
	        url: "jpushModifyUserResult.jsp?id="+<%=id%>+"&xingming="+xingming.value
	        		+"&shoujihao="+shoujihao.value+"&dianziyouxiang="+dianziyouxiang.value
	        		+"&dizhi="+dizhi.value,
	        dataType: "html",
	        data: {
	            //organiseUnitID: selorganiseUnitID,
	            //CharType: 'CockiptTrendChange'
	        },
	        success: function (data) {
	        	if(data>0){
	        		window.opener.location.href = window.opener.location.href;
	        		window.close();  
	        	}else{
	        		alert("修改人员信息失败");
	        	}
	        },
	       error: function( msg ) { 
	    	   alert("修改人员信息失败"); 
	        }
		});		
	}
</script>
</html>

