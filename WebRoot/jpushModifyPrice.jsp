<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.pg.bean.Pgdr_price"  %>
<%@ page import="com.pg.web.OrderManager"  %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id= new String(request.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
OrderManager om = new OrderManager();
Pgdr_price  pp = om.getOnePrice(id);
String price_name = "";
String price_isvalid = "";
String price_type = "";
String price_price = "";
String price_explain = "";
if(pp!=null){
	if(pp.getPrice_name()!=null){
		price_name = pp.getPrice_name();
	}
	if(pp.getPrice_isvalid()!=null){
		price_isvalid = pp.getPrice_isvalid();
	}
	if(pp.getPrice_type()!=null){
		price_type = pp.getPrice_type();
	}
	if(pp.getPrice_price()!=null){
		price_price = pp.getPrice_price();
	}
	if(pp.getPrice_explain()!=null){
		price_explain = pp.getPrice_explain();
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
                        	<label class="control-label" for="inputEmail">名称</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="mingcheng" size="10"  placeholder="名称"  value =<%=price_name%> >
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">类型</label>
							<div class="controls">
							<%
								if (price_type.equals("shouji")) {
							%>
						   <input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="手机回收" >							
							<%
								} else if (price_type.equals("jiuyifu")) {
							%>
							<input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="衣服回收" >		
							<%
								} else if (price_type.equals("suliaoping")) {
							%>
							<input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="塑料瓶回收" >
							<%
								} else if (price_type.equals("yilaguan")) {
							%>
							<input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="易拉罐回收" >
							<%
								} else if (price_type.equals("zhi")) {
							%>
							<input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="纸箱回收" >
							<%
								} else if (price_type.equals("dianzi")) {
							%>
							<input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="电子设备回收" >
							<%
								} else if (price_type.equals("jiadian")) {
							%>
							<input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="家电回收" >
							<%
								} else if (price_type.equals("qita")) {
							%>
							<input class="input-xxlarge" type="text" id="shoujihao" size="10" readonly placeholder="类型" value ="其他回收" >
							<%
								}
							%>							
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">价格</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="jiage" size="10"  placeholder="价格"  value ="<%=price_price %>" >
							</div>
                        </td>
                    </tr>   
                     <tr>                    
                    		<td>
                      		<label class="control-label" for="inputEmail">说明</label>
                            <div class="controls">
                                <textarea class="input-xxlarge" id="shuoming"  rows="3" placeholder="说明"  ><%=price_explain%></textarea>
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
		var mingcheng = document.getElementById("mingcheng");
		var jiage = document.getElementById("jiage");
		var shuoming =  document.getElementById("shuoming");
	    $.ajax({
	        type: "Post",
	        url: "jpushModifyPriceResult.jsp?id="+<%=id%>+"&mingcheng="+mingcheng.value
	        		+"&jiage="+jiage.value+"&shuoming="+shuoming.value,
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
	        		alert("修改价格失败");
	        	}
	        },
	       error: function( msg ) { 
	    	   alert("修改价格失败"); 
	        }
		});		
	}
</script>
</html>

