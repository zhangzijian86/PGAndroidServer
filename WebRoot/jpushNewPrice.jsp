<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>新建价格</title>
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
                        <th><h3>新建价格</h3></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">名称</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="mingcheng" size="10"  placeholder="名称"   >
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">类型</label>
							<div class="controls">
								<select id="leixing">
									<option value="shouji">手机回收</option>
									<option value="jiuyifu">衣服回收</option>									
									<option value="zhi">纸箱回收</option>									
									<option value="jiadian">家电回收</option>									
									<option value="suliaoping">塑料瓶回收</option>
									<option value="yilaguan">易拉罐回收</option>
									<option value="dianzi">电子设备回收</option>
									<option value="qita">其他回收</option>
								</select>
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label" for="inputEmail">价格</label>
							<div class="controls">
								<input class="input-xxlarge" type="text" id="jiage" size="10"  placeholder="价格"   >
							</div>
                        </td>
                    </tr>   
                     <tr>                    
                    		<td>
                      		<label class="control-label" for="inputEmail">说明</label>
                            <div class="controls">
                                <textarea class="input-xxlarge" id="shuoming"  rows="3" placeholder="说明"  ></textarea>
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
		
		var leixing = document.getElementById("leixing");
		var index = leixing.selectedIndex; // 选中索引
//		var text = leixing.options[index].text; // 选中文本
		var leixingvalue = leixing.options[index].value;
		
	    $.ajax({
	        type: "Post",
	        url: "jpushNewPriceResult.jsp?mingcheng="+mingcheng.value
	        		+"&jiage="+jiage.value+"&shuoming="+shuoming.value
	        		+"&leixing="+leixingvalue,
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
	        		alert("新增价格失败");
	        	}
	        },
	       error: function( msg ) { 
	    	   alert("新增价格失败"); 
	        }
		});		
	}
</script>
</html>

