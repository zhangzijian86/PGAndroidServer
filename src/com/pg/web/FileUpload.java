package com.pg.web;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.net.URL;  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.Iterator;  
import java.util.List;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
import java.util.UUID;
import com.pg.db.GetExcelConn;
  
/** 
 * Servlet implementation class FileUpload 
 */  
public class FileUpload extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    private static final String fileDir = "/tmp/";  
    /** 
     * Default constructor. 
     */  
    public FileUpload() {  
          
    }  
  
    protected void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
      
    	Date nowTime=new Date(); 
    	SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");   
    	SimpleDateFormat time1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    	
        String aFileName = request.getParameter("fileName");   
        
        String dizhi = request.getParameter("dizhi");   
        String duankou = request.getParameter("duankou");
        String mingzi = request.getParameter("mingzi");
        String zhanghu = request.getParameter("zhanghu");
        String mima = request.getParameter("mima");
        
        System.out.println("=========aFileName============"+aFileName);
        System.out.println("=========dizhi============"+dizhi);
        System.out.println("=========duankou============"+duankou);
        System.out.println("=========mingzi============"+mingzi);
        System.out.println("=========zhanghu============"+zhanghu);
        System.out.println("=========mima============"+mima);
        
        
        
        String id = UUID.randomUUID().toString();        
      
        String FaultCode = "EMF";
        FaultCode = FaultCode+time.format(nowTime).toString()+(int)(Math.random()*100);
        
        String ReportOrganiseUnit = "10570bed-54ff-11e5-8ec1-64006a4cb35a";//申报单位id(来自于SDMS_OrganiseUnit表OrganiseUnitID) 
        
        String EquipmentID = "100000416"; //EMB_Equipment 表 equipmentcode 转化为 e7d64b65-b847-11e5-a82b-64006a4cb35a
        
        String FaultType = "3"; //FaultType 随便写 3
        
        String ReportUser = "aofengadmin";
        
        String ReportTime = time1.format(nowTime);//当前时间 2016-06-21 14:52:45
        
        String IsRFIDScan = "0";
        
        String IsBarCodeScan = "0";
        
        String Status = "100";       
        
        String EffectiveStatus = "-1";
        
        String CreatedBy = ReportUser;
        
        String CreatedDate = time1.format(nowTime);
        
        String ModifiedBy = ReportUser;

        String ModifiedDate = time1.format(nowTime);
        
        String weixiunshijian= "维修时间"; //维修时间
        
        String guzhangbuwei = "故障部位"; //故障部位
        
        String guzhangchengdu = "故障程度"; //故障程度
        
        String weixiunneirong = "维修内容        "; //维修内容        
        
        String weixiuren = "维修人"; //维修人
        
        String beizhu = "备注"; //备注        
        
        
        
        
        System.out.println("=========uuid============"+id);
        System.out.println("=========FaultCode============"+FaultCode);
        
        GetExcelConn getExcelConn=new GetExcelConn();
		ResultSet rs = null;
		Connection conn=getExcelConn.getConnection(dizhi,duankou,mingzi,zhanghu,mima);
		String sql="select count(*) as pageCount from emf_faultrepair";
    	int i=0;   
    	try {
    		PreparedStatement ps=conn.prepareStatement(sql);
    		rs=ps.executeQuery();
    		if(rs!=null){    					
    			rs.next();  
    			i=rs.getInt("pageCount");  
    		}
    	} catch (SQLException e) {   
    		e.printStackTrace();   
    	}   
    	getExcelConn.closeconn(conn);
    	System.out.println("=========i============"+i);        
        request.setAttribute("fileName",aFileName);  
        request.setAttribute("type","1");  
        request.getRequestDispatcher("uploadFileResult.jsp").forward(request, response);  
  
    }  
    
//    protected void doGet(HttpServletRequest request,  
//            HttpServletResponse response) throws ServletException, IOException {  
//      
//    	Date nowTime=new Date(); 
//    	SimpleDateFormat time=new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss"); 
//        String aFileName = request.getParameter("fileName");   
//        aFileName = aFileName + "-" + time.format(nowTime).toString();
//        String online = request.getParameter("online");  
//        FileInputStream in = null;   
//        ServletOutputStream out = null;   
//        boolean isOnLine = online != null ? true : false ;  
//        try {  
//          
//              
//            if(isOnLine){   
//                URL u = new URL("file:///"+fileDir + aFileName);  
//                response.setContentType(u.openConnection().getContentType());  
//                response.setHeader("Content-Disposition", "inline; filename="+aFileName);     
//             }  
//            else{   
//                response.setContentType("application/x-msdownload");   
//                response.setHeader("Content-Disposition", "attachment; filename=" + aFileName);   
//            }  
//          
//            in = new FileInputStream(fileDir + aFileName);   
//            out = response.getOutputStream();  
//            out.flush();  
//            int aRead = 0;  
//            while ((aRead = in.read()) != -1 & in != null) {  
//                out.write(aRead);  
//             }  
//            out.flush();  
//              
//        } catch (Throwable e) {  
//            e.printStackTrace();  
//        } finally {  
//            try {  
//                in.close();  
//                out.close();  
//            } catch (Throwable e) {  
//                e.printStackTrace();  
//            }  
//        }  
//  
//    }  
  
    protected void doPost(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        try {  
            DiskFileItemFactory fileFactory = new DiskFileItemFactory();  
            ServletFileUpload fu = new ServletFileUpload(fileFactory);  
            List fileItems = fu.parseRequest(request);  
            Iterator iter = fileItems.iterator();  
            String uploader = null;  
            String date = null;  
            List<String> fileNames = new ArrayList<String>();  
            List<String> fileNamesNew = new ArrayList<String>();  
            while (iter.hasNext()) {  
                FileItem item = (FileItem) iter.next();  
                if (!item.isFormField()) { // 文件  
                    String oldFileName = item.getName();  
                    String newFileName = null;  
                    int delimiter = oldFileName.lastIndexOf("/");  
                    if (delimiter == -1)  
                        newFileName = oldFileName.substring(delimiter + 1);  
                    else  
                        newFileName = oldFileName;  
                    fileNames.add(newFileName);  
                	Date nowTime=new Date(); 
                	SimpleDateFormat time=new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss"); 
                	newFileName = newFileName.substring(0, newFileName.lastIndexOf(".")) + "-" + 
                										time.format(nowTime).toString()+newFileName.substring(newFileName.lastIndexOf("."),newFileName.length());
                    item.write(new File(fileDir + newFileName));  
                    fileNamesNew.add(newFileName);
                } else { // 表单  
                    String fieldName = item.getFieldName();  
                    if ("uploader".equals(fieldName)) {  
                        uploader = item.getString();  
                    } else if ("date".equals(fieldName)) {  
                        date = item.getString();  
                    }  
                }  
            }  
            request.setAttribute("fileNames",fileNamesNew); 
            request.setAttribute("type","0");  
            request.getRequestDispatcher("uploadFileResult.jsp").forward(request, response);  
        } catch (Exception e) {  
  
        }  
    }  
  
}  