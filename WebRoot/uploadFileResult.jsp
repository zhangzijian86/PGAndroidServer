<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
  
<%@page import="java.util.List"%><html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Insert title here</title>  
</head>  
<body>  
   
 <br>  
 
      <%         
        String type = (String)request.getAttribute("type");   
      	if(type.equals("0")){
        List<String> fileNames = (List<String> )request.getAttribute("fileNames");   
        for(String fileName : fileNames) {  
      %>  
      --------------是否导入------------  
      <br> 
        <form action="FileUpload" method="get">  
            <input type="hidden" name="fileName" value="<%=fileName %>" />  
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