<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
  
<%@page import="java.util.List"%><html>  
<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>导入数据</title>
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
   
 <br>  
 
      <%         
        String type = (String)request.getAttribute("type");   
      	if(type.equals("0")){
        List<String> fileNames = (List<String> )request.getAttribute("fileNames");   
        for(String fileName : fileNames) {  
      %>  
      --------上传完成----------
      <br> 
      --------是否导入----------
      <br> 
        <form action="FileUpload" method="get">  
        	<input type="text" name="dizhi" size="10"  placeholder="数据库ip地址"  value ="192.8.50.206" >
        	<br> 
        	<input type="text" name="duankou" size="10"  placeholder="数据库端口"  value ="3306" >
        	<br> 
        	<input type="text" name="mingzi" size="10"  placeholder="数据库名字"  value ="sdisplat_bfyk" >
        	<br> 
        	<input type="text" name="zhanghu" size="10"  placeholder="数据库账户"  value ="root" >
        	<br> 
        	<input type="password" name="mima" size="10"  placeholder="数据库密码"  value ="root" >
        	<br> 
            <input type="hidden" name="fileName" value="<%=fileName %>" />  
            <br> 
            <input type="submit" value="导入文件:<%=fileName %>" />  
        </form>  
     <br> 
     <%  
      	}   
      }else{
    	  String aFileName = (String)request.getAttribute("aFileName");   
      %>  
     	导入成功
      <%  
        }  
      %>  
     </br>  
  
</body>  
</html>  