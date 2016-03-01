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
								<input class="input-xxlarge" type="text" id="huishouriqi" size="10"  placeholder="回收日期"  value =<%=dailyrecycle_date %> >
							</div>
                        </td>
                    </tr>                
                    
                    
                    
                    <tr>
                    	<td>
                            <label class="control-label" for="inputEmail">内容</label>
                            <div class="controls">
                                <textarea class="input-xxlarge" rows="10" placeholder="内容" required id="contenttest"></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                            <label class="control-label" for="inputEmail" >分组</label>
                            <div class="controls">
                                <select id="grouptest">
                                    <option value="laonianjibing">老年疾病</option>
                                    <option value="laonianyongyao">老年用药</option>
                                    <option value="laonianhuli">老年护理</option>
                                </select>
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
                                <button class="btn btn-primary" type="button" onclick="cancel()">取消</button>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
<script type="text/javascript">
	$('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	function confirm()
	{
		var titletest = document.getElementById("titletest");
		var contenttest = document.getElementById("contenttest");
		var grouptest = document.getElementById("grouptest");
		
		var index = grouptest.selectedIndex; // 选中索引
		var text = grouptest.options[index].text; // 选中文本
		var value = grouptest.options[index].value;
		
		//alert("=="+index+"=="+text+"=="+value);	
		var title  = document.getElementById("title");
		var content  = document.getElementById("content");
		var group = document.getElementById("group"); 
		
		title.value=titletest.value;
		content.value=contenttest.value;
		group.value=value;		
        //alert("==confirm=="+title.value+"="+grouptest.value+"="+content.value+"=="+grouptest.value+"==="+group.value);
        document.form1.submit();
	}
	function cancel()
	{
		document.write("==========cancel============");
		//document.form1.submit();
	}
</script>
