<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.pg.bean.Pgdr_price"  %>
<%@ page import="com.pg.web.OrderManager"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String currentPage = "0";
if(request.getParameter("currentPage")!=null){
	currentPage = new String(request.getParameter("currentPage").getBytes("ISO-8859-1"),"utf-8");
}
String eachPage = "10";
if(request.getParameter("eachPage")!=null){
	eachPage = new String(request.getParameter("eachPage").getBytes("ISO-8859-1"),"utf-8");
}
int pageCount = 0;
int count = 0;
Pgdr_price [] pp = null;
OrderManager om = new OrderManager();
pp = om.getPrices(currentPage,eachPage);
count = om.getCount("PGDR_PRICE","where PRICE_ISVALID=1 ");
pageCount =  (new Double(Math.ceil(((double)count/Double.valueOf(eachPage))))).intValue();
%>
<html>
<head>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>价格列表</title>
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
                        <th colspan="3"><h3>价格列表</h3></th>  
                        <th><h3><a href="javascript:add();">新建价格</a></h3></th>     
                        <th><h3><a href="javascript:history.go(-1)">返回</a></h3></th>                                                                      
                    </tr>
                </thead>
                <tbody>
				 <tr class="warning">
                        <td width="20%">
                           名称
                        </td >
                       <td width="20%">
                        	价格
                        </td>
                        <td width="20%">
                        	类型
                        </td> 
                        <td width="20%">
                        	说明
                        </td> 
                        <td width="20%">
                        	操作
                        </td>
                    </tr>
                    <%if(pp!=null&&pp.length>0){
						for(int i = 0;i<pp.length;i++){ %>
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
                        <td width="20%">
                        <a href="javascript:modify(<%=pp[i].getPrice_id()%>);" target="_blank"><%out.print(pp[i].getPrice_name()); %></a>         
                        </td >
                        <td width="20%">
                    		<%
                    		if(pp[i].getPrice_price()==null){
                    			
                    		}else{
                    			out.print(pp[i].getPrice_price());
                    		}
                    		 %>
                        </td>
                        <td width="20%">
                        	<%
                    		if(pp[i].getPrice_type()==null){
                    			
                    		}else{
                    	        if(pp[i].getPrice_type().equals("shouji")){
                    	        	out.print("手机回收");
                    	        }else if(pp[i].getPrice_type().equals("jiuyifu")){                    	        	
                    	        	out.print("衣服回收");
                    	         }
                    	        else if(pp[i].getPrice_type().equals("suliaoping")){                    	        	
                    	        	out.print("塑料瓶回收");
                    	         }
                    	        else if(pp[i].getPrice_type().equals("yilaguan")){                    	        	
                    	        	out.print("易拉罐回收");
                    	         }
                    	        else if(pp[i].getPrice_type().equals("zhi")){                    	        	
                    	        	out.print("纸箱回收");
                    	         }
                    	        else if(pp[i].getPrice_type().equals("dianzi")){                    	        	
                    	        	out.print("电子设备回收");
                    	         }
                    	        else if(pp[i].getPrice_type().equals("jiadian")){                    	        	
                    	        	out.print("家电回收");
                    	         }
                    	        else if(pp[i].getPrice_type().equals("qita")){
                    	        	out.print("其他回收");
                    	         }
                    		} %>
                        </td>
                        <td width="20%">                        	
                        	<%
                    		if(pp[i].getPrice_explain()==null){
                    			
                    		}else{
                    			out.print(pp[i].getPrice_explain());
                    		} %>           
                        </td>  
                         <td width="20%">                        	
                            <%
                    		if(pp[i].getPrice_isvalid()!=null){
                    			if(pp[i].getPrice_isvalid().equals("1")){
                    			%>
                    				<a href="javascript:confirm(<%=pp[i].getPrice_id()%>);" target="_blank">删除</a>
                    			<% 
	                    		} else{
	                    			out.print("已删除");
	                    		}
                    		}%>  
                        </td>  
                    </tr>
                    <%}
					}%>
					<tr class="warning">
                        <td colspan="1" >

                        </td >
                        <td colspan="3" align="center">
                        <a href="javascript:paging(1);" target="_blank">首页</a>
                        		<%
                        		for(int j = 1; j<pageCount+1 ; j++){ 
                        		%>
                        		<a href="javascript:paging(<%=j%>);" target="_blank"><%=j%></a>
                        		<%
                        		}
                        		%>
						<a href="javascript:paging(<%=pageCount%>);" target="_blank">末页</a>
                        </td>
                        <td colspan="1" >

                        </td> 
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
<script type="text/javascript" src="js/jquery-1.4.1.js"></script>
<script type="text/javascript">

	function paging(currentPage) {
		window.location.href = "jpushPriceList.jsp?currentPage="
				+ currentPage + "&eachPage=10";
	}
	function confirm(id) {
		$.ajax({
			type : "Post",
			url : "jpushPriceListResult.jsp?id=" + id,
			dataType : "html",
			data : {
			//organiseUnitID: selorganiseUnitID,
			//CharType: 'CockiptTrendChange'
			},
			success : function(data) {
				if (data > 0) {
					window.location.reload();
				} else {
					alert("删除失败请重试");
				}
			},
			error : function(msg) {
				alert("删除失败请重试");
			}
		});
	}
	function modify(id) {
		window.open("jpushModifyPrice.jsp?id=" + id, "",
				"height=700, width=800");
	}
	function add() {
		window.open("jpushNewPrice.jsp", "",
				"height=700, width=800");
	}
</script>
</html>

