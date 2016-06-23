<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Insert title here</title>  
</head>  
<body>  
 <form action="FileUpload" method="post" enctype="multipart/form-data" id="form">  
 <br>  
   文件(exel文件)：<input type="file" name="file" id = "file"/> <br>  
            <input type="button" value="提交" onclick="ck();"/>  
    
 </form>  
</body>  
<script type="text/javascript">
String.prototype.EndWith=function(s){
	if(s==null||s==""||this.length==0||s.length>this.length){
		return false;
	}		
	if(this.substring(this.length-s.length)==s){
		return true;
	}else{
		return false;
	}
	return true;
};
String.prototype.compare = function(str)
{
	//不区分大小写
	if(this.toLowerCase() == str.toLowerCase())
	{
	   return true; // 正确
	}
	else{
	   return false; // 错误
	}
};
function ck()
{
	var file = "";
	var value = "";
	file = document.getElementById("file");
	value = file.value.toLowerCase();	
	if(value.EndWith(".xlsx")||value.EndWith(".xls")){
		document.getElementById('form').submit();
	}else{
		alert("请上传正确的exel文件");
		return false;
	}
}
</script>
</html>  