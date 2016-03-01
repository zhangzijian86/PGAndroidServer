<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.pg.bean.Ppdr_dailyrecycle"  %>
<%@ page import="com.pg.web.OrderManager"  %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id= new String(request.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
OrderManager om = new OrderManager();
Ppdr_dailyrecycle  pdr  = om.getModifyRecycle(id);
String dailyrecycle_name = "";
String dailyrecycle_user_mobile = "";
String dailyrecycle_date = "";
String dailyrecycle_iscycle = "";
String dailyrecycle_cycletype = "";
String dailyrecycle_status = "";
String dailyrecycle_recyclingmanphone = "";
String dailyrecycle_finishtime = "";
String dailyrecycle_type = "";
String dailyrecycle_explain = "";
String dailyrecycle_address = "";
if(pdr!=null){
	dailyrecycle_name = pdr.getDailyrecycle_name();
	dailyrecycle_user_mobile = pdr.getDailyrecycle_user_mobile();
	dailyrecycle_date = pdr.getDailyrecycle_date();
	dailyrecycle_iscycle = pdr.getDailyrecycle_iscycle();
	dailyrecycle_cycletype = pdr.getDailyrecycle_cycletype();
	dailyrecycle_status  = pdr.getDailyrecycle_status();
	dailyrecycle_recyclingmanphone = pdr.getDailyrecycle_recyclingmanphone();
	dailyrecycle_finishtime = pdr.getDailyrecycle_finishtime();
	dailyrecycle_type = pdr.getDailyrecycle_type();
	dailyrecycle_explain = pdr.getDailyrecycle_explain();
	dailyrecycle_address = pdr.getDailyrecycle_address();
	dailyrecycle_recyclingmanphone = pdr.getDailyrecycle_recyclingmanphone();
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
								<input class="input-xxlarge" type="text" id="xingming" size="10"  placeholder="姓名"  value =<%=dailyrecycle_name %> >
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">手机号</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="shoujihao" size="10"  placeholder="手机号" value =<%=dailyrecycle_user_mobile %> >
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">回收日期</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="huishouriqi" size="10"  placeholder="回收日期"  value ="<%=dailyrecycle_date %>" >
							</div>
                        </td>
                    </tr>                
                                            <%
                        if(dailyrecycle_iscycle.equals("1")){
							if(dailyrecycle_cycletype.equals("0")){
		                    %>
                        	<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">周期</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" readonly size="10"  placeholder="每周"  value ="每周" >
								</div>
	                        </td>
                    		</tr>  
                        	<%
							}else{
			                %>
			             <tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">周期</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" size="10" readonly placeholder="每月"  value ="每月" >									
								</div>
	                        </td>
                    		</tr>                        		
                        	<%
							}
                        }else{
			                %>
                    	<tr>
                        <td>
                        	<label class="control-label" for="inputEmail">周期</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="huishouriqi" size="10" readonly placeholder="非周期"  value ="非周期" >							
							</div>
                        </td>
                    </tr>
                    <%
                        }
						if(dailyrecycle_status.equals("0")){
		             %>
                        	<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">状态</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" readonly size="10"  placeholder="未完成"  value ="未完成" >
								</div>
	                        </td>
                    		</tr>  
                        	<%}else{%>
                        	  <tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">状态</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" readonly size="10"  placeholder="已完成"  value ="已完成" >
								</div>
	                        </td>
                    		</tr>  
                    		<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">完成时间</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishouriqi" readonly size="10"    value ="<%=dailyrecycle_finishtime %>">
								</div>
	                        </td>
                    		</tr>
                    		<%}%>
                    		<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">类型</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="leixing"  size="10" placeholder="类型"   value ="<%=dailyrecycle_type %>">
								</div>
	                        </td>
                    		</tr>
                    		<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">回收人手机号</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="huishourenshoujihao"  size="10" readonly placeholder="回收人手机号"   value ="<%=dailyrecycle_recyclingmanphone %>">
								</div>
	                        </td>
                    		</tr>
                    		<tr>
	                        <td>
	                        	<label class="control-label" for="inputEmail">备注</label>
								<div class="controls">
									<input class="input-xxlarge" type="text" id="beizhu"  size="10" placeholder="备注"   value ="<%=dailyrecycle_explain %>">
								</div>
	                        </td>
                    		</tr>
                    	<tr>                    
                    		<td>
                      		<label class="control-label" for="inputEmail">地址</label>
                            <div class="controls">
                                <textarea class="input-xxlarge" id="dizhi"  rows="3" placeholder="地址"  ><%=dailyrecycle_address%></textarea>
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
		var huishouriqi = document.getElementById("huishouriqi");
		var leixing =  document.getElementById("leixing");
		var beizhu =  document.getElementById("beizhu");
		var dizhi =  document.getElementById("dizhi");
	    $.ajax({
	        type: "Post",
	        url: "jpushModifyDailyrecycleResult.jsp?id="+<%=id%>+"&xingming="+xingming.value
	        		+"&shoujihao="+shoujihao.value+"&huishouriqi="+huishouriqi.value
	        		+"&leixing="+leixing.value+"&beizhu="+beizhu.value+"&dizhi="+dizhi.value,
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
	        		alert("修改订单信息失败");
	        	}
	        },
	       error: function( msg ) { 
	    	   alert("修改订单信息失败"); 
	        }
		});		
	}
</script>
</html>

