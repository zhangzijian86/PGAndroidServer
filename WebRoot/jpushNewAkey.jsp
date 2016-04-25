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
	</head>
    <body>
    	<form  method="post" 
			action="jpushContentEntryResult.jsp"
			 name="form1" id="form1" >
        	<table  width="180" align="center">
            	<thead>
                	<tr>
                        <th><h3 align="center">微信预约</h3></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td align="center">    
								<input class="input-xxlarge" type="text" id="shouji" size="10"  placeholder="手机号码"   >
							</td>
                    </tr>             
                    <tr>
                    	<td align="center">                    	    
                           <button class="btn btn-primary"  type="button" onclick="confirm()">一键预约</button>
                     </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
<script type="text/javascript">
	function confirm()
	{
		var shouji = document.getElementById("shouji");
		if(shouji.value.trim()!=""){
			if(shouji.value.trim().length==11){
				var str = shouji.value.trim();
				if (!isNaN(str)){
					$.ajax({
				        type: "Post",
				        url: "jpushNewAkeyResult.jsp?shouji="+shouji.value,
				        dataType: "html",
				        data: {
				            //organiseUnitID: selorganiseUnitID,
				            //CharType: 'CockiptTrendChange'
				        },
				        success: function (data) {
				        	if(data>0){
				        		alert("预约成功,请等待工作人员和您联系");
				        	}else{
				        		alert("预约失败1");
				        	}
				        },
				       error: function( msg ) { 
				    	   alert("预约失败2"); 
				        }
					});	
				}else{
					alert("手机号码应为数字");
				}			    
			}else{
				alert("手机号码应为11位");
			}
		}else{
			alert("请输入手机号码");
		}
	}
</script>
</html>

