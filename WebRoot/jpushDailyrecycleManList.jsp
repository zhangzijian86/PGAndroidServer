<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.pg.bean.Pgdr_user"  %>
<%@ page import="com.pg.web.OrderManager"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id= new String(request.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
Pgdr_user [] pu = null;
OrderManager om = new OrderManager();
pu = om.getUsers();
%>
<html>
<head>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>选择提货人员</title>
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
                        <th colspan="2"><h3>提货人员列表</h3></th>
                        <th colspan="2" align="right"><button class="btn btn-primary"  type="button" onclick="confirm()">确定</button></th>                        
                    </tr>
                </thead>
                <tbody>
				 <tr class="warning">
				        <td width="15%">
                        	
                        </td>
                    	<td width="15%">
                        	姓名
                        </td>
                        <td width="20%">
                        	手机号
                        </td >
                        <td width="50%">
                        	地址
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
                    	<td width="8%">
                        	<input type="radio" id="phone" name = "phonenumber" value="<%=pu[i].getUser_mobile()%>">
                        </td>
                    	<td width="8%">
                    	    <%
                    		if(pu[i].getUser_name()==null){
                    			
                    		}else{
                    			out.print(pu[i].getUser_name());
                    		} %>  
                        </td>
                        <td width="10%">
                           <%
                    		if(pu[i].getUser_mobile()==null){
                    			
                    		}else{
                    			out.print(pu[i].getUser_mobile());
                    		} %>                          
                        </td >
                        <td width="12%">
                            <%
                    		if(pu[i].getUser_address()==null){
                    			
                    		}else{
                    			out.print(pu[i].getUser_address());
                    		} %>   
                        </td>  
                    </tr>
                    <%}
					}%>
                </tbody>
            </table>
        </form>
    </body>
<script type="text/javascript" src="js/jquery-1.4.1.js"></script>
<script type="text/javascript">
	function confirm()
	{
		var radios = document.getElementsByName("phonenumber");  
		var flag = 0;		
		var phonenumber = 0;		
		for(var i=0;i<radios.length;i++)  
	    {   
	        //判断那个单选按钮为选中状态  
	        if(radios[i].checked)  
	        {  
	            //弹出选中单选按钮的值  
	            phonenumber = radios[i].value;
	            flag++;
	        }   
	    }	
		if(flag==0){
			alert("请选择提货人员！");
		}else{
		    $.ajax({
		        type: "Post",
		        url: "jpushDailyrecycleManListResult.jsp?id="+<%=id%>+"&phonenumber="+phonenumber,
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
		        		alert("分配提货人员失败");
		        	}
		        },
		       error: function( msg ) { 
		    	   alert("分配提货人员失败");
		        }
			});
		}
	}
</script>
</html>

