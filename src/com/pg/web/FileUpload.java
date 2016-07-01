package com.pg.web;

import java.io.BufferedInputStream;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Arrays;
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
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.UUID;

import com.mysql.jdbc.Statement;
import com.pg.bean.Pgdr_user;
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
      
    	request.setCharacterEncoding("UTF-8");    	
    	
    	String sqlzong = "";
    	
    	Date nowTime=new Date(); 
    	SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");   
    	SimpleDateFormat time1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    	
    	String aFileName = new String(request.getParameter("fileName").getBytes("iso-8859-1"), "utf-8");   
        
        String dizhi = request.getParameter("dizhi");   
        String duankou = request.getParameter("duankou");
        String mingzi = request.getParameter("mingzi");
        String zhanghu = request.getParameter("zhanghu");
        String mima = request.getParameter("mima");
        String danwei = request.getParameter("danwei");
        String renyuan = request.getParameter("renyuan");       
        
        System.out.println("=========aFileName============"+aFileName);
        System.out.println("=========dizhi============"+dizhi);
        System.out.println("=========duankou============"+duankou);
        System.out.println("=========mingzi============"+mingzi);
        System.out.println("=========zhanghu============"+zhanghu);
        System.out.println("=========mima============"+mima);
        System.out.println("=========danwei============"+danwei);
        System.out.println("=========renyuan============"+renyuan);
        
    	GetExcelConn getExcelConn=new GetExcelConn();
    	Connection conn=getExcelConn.getConnection(dizhi,duankou,mingzi,zhanghu,mima);          
      
        String FaultCode = "EMF";
        FaultCode = FaultCode+time.format(nowTime).toString()+(int)(Math.random()*100);
        
        String ReportOrganiseUnit = "00000000-0000-0000-0000-000000000000";//"10570bed-54ff-11e5-8ec1-64006a4cb35a";//申报单位id(来自于SDMS_OrganiseUnit表OrganiseUnitID) 
        
		ResultSet rs = null;		
    	try {
    		PreparedStatement ps=conn.prepareStatement("select OrganiseUnitID from SDMS_OrganiseUnit where  StandardCode =? and IsDelete = 0");
    		ps.setString(1,danwei);
    		rs=ps.executeQuery();
			if(rs!=null){    					
	    		rs.next();
	    		ReportOrganiseUnit = rs.getString("OrganiseUnitID");
    		}
    	} catch (SQLException e) {   
    		e.printStackTrace();   
    	}    
    	
    	System.out.println("=========danwei=====id======="+ReportOrganiseUnit);  
        
        System.out.println("=========FaultCode============"+FaultCode);

    	String EquipmentID = "00000000-0000-0000-0000-000000000000"; //EMB_Equipment 表 equipmentcode 转化为 e7d64b65-b847-11e5-a82b-64006a4cb35a
        
        String FaultType = "1"; //FaultType 随便写 3
        
        String ReportUser = renyuan;
        
        String ReportTime = time1.format(nowTime);//当前时间 2016-06-21 14:52:45
        
        String IsRFIDScan = "0";
        
        String IsBarCodeScan = "0";
        
        String Status = "100";       
        
        String EffectiveStatus = "-1";
        
        String CreatedBy = renyuan;
        
        String CreatedDate = time1.format(nowTime);
        
        String ModifiedBy = renyuan;

        String ModifiedDate = time1.format(nowTime);
        
        String weixiunshijian= "维修时间"; //维修时间   RepairDate
        
        String guzhangbuwei = "故障部位"; //故障部位    Explain '故障情况的说明',
        
        String guzhangchengdu = "故障程度"; //故障程度   Explain '故障情况的说明',
        
        String weixiunneirong = "维修内容"; //维修内容   RepairExplain 维修情况说明  
        
        String beizhu = "备注"; //备注      RepairExplain 维修情况说明  
        
        String weixiuren = "维修人"; //维修人 RepairPerson 维修人姓名(填写)
        
        String Explain = "";
        String RepairExplain = "";
        
        int rowSize = 0;
        FileInputStream input = new FileInputStream(new File("/tmp/"+aFileName));  //读取的文件路径   
        XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));   
        XSSFCell cell = null;
        try {
        	sqlzong = "insert into emf_faultrepair(id,FaultCode,ReportOrganiseUnit,EquipmentID,FaultType,ReportUser,ReportTime,"+
            		"IsRFIDScan,IsBarCodeScan,Status,EffectiveStatus,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,RepairDate,"+
            		"emf_faultrepair.Explain,RepairPerson,RepairExplain)"+
            		"values"+
            		"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        	conn.setAutoCommit(false);  
        	PreparedStatement pstmt=conn.prepareStatement(sqlzong);      	  
	        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
	            XSSFSheet st = wb.getSheetAt(sheetIndex);
	            // 第一行为标题，不取
	            for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
	                XSSFRow row = st.getRow(rowIndex);
	                if (row == null) {
	                    continue;
	                }              
	                int tempRowSize = row.getLastCellNum() + 1;
	                if (tempRowSize > rowSize) {
	                    rowSize = tempRowSize;
	                }
	                String[] values = new String[rowSize];
	                Arrays.fill(values, "");
	                boolean hasValue = false;
	                if(row.getLastCellNum()==7){
		                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
		                    String value = "";
		                    cell = row.getCell(columnIndex);
		                    if (cell != null) {
		                        switch (cell.getCellType()) {
		                        case XSSFCell.CELL_TYPE_STRING:
		                            value = cell.getStringCellValue();
		                            break;
		                        case XSSFCell.CELL_TYPE_NUMERIC:
		                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
		                               Date date = cell.getDateCellValue();
		                               if (date != null) {
		                                   value = new SimpleDateFormat("yyyy-MM-dd")
		                                          .format(date);
		                               } else {
		                                   value = "";
		                               }
		                            } else {
		                               value = new DecimalFormat("0").format(cell
		                                      .getNumericCellValue());
		                            }
		                            break;
		                        case XSSFCell.CELL_TYPE_FORMULA:
		                            // 导入时如果为公式生成的数据则无值
		                            if (!cell.getStringCellValue().equals("")) {
		                               value = cell.getStringCellValue();
		                            } else {
		                               value = cell.getNumericCellValue() + "";
		                            }
		                            break;
		                        case XSSFCell.CELL_TYPE_BLANK:
		                            break;
		                        case XSSFCell.CELL_TYPE_ERROR:
		                            value = "";
		                            break;
		                        case XSSFCell.CELL_TYPE_BOOLEAN:
		                            value = (cell.getBooleanCellValue() == true ? "Y"
		                                   : "N");
		                            break;
		                        default:
		                            value = "";
		                        }
		                    }
			                if(columnIndex == 0){
			                	weixiunshijian = value;
			                	System.out.println("=========维修时间============"+weixiunshijian);
			                }
			                if(columnIndex == 1){
			                	Explain = "故障部位:"+value;
			                }		                
			                if(columnIndex == 2){
			                	Explain = Explain+" 故障程度:"+value;
			                	System.out.println("====故障部位+故障程度======"+Explain);
			                }
			                if(columnIndex == 3){
			                	RepairExplain = value;
			                }
			                if(columnIndex == 4){
			                	weixiuren = value;
			                	System.out.println("====维修人======"+weixiuren);
			                }
			                if(columnIndex == 5){
			                	RepairExplain = RepairExplain+" 备注:"+value;
			                	System.out.println("====维修内容+备注======"+RepairExplain);
			                }
			                if(columnIndex == 6){		                	
			                	System.out.println("====设备编码======"+value);
			            		rs = null;		
			                	try {
			                		PreparedStatement ps=conn.prepareStatement("select id from EMB_Equipment where IsDelete = 0 and EquipmentCode = ?");
			                		ps.setString(1,danwei);
			                		rs=ps.executeQuery();
			            			if(rs!=null){    					
			            	    		rs.next();
			            	    		EquipmentID = rs.getString("id");
			                		}
			                	} catch (SQLException e) {   
			                		e.printStackTrace();   
			                	}
			                	System.out.println("====设备id======"+EquipmentID);
			                }
		                }
//		                sqlzong = "insert into emf_faultrepair(id,FaultCode,ReportOrganiseUnit,EquipmentID,FaultType,ReportUser,ReportTime,"+
//		                		"IsRFIDScan,IsBarCodeScan,Status,EffectiveStatus,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,RepairDate,"+
//		                		"emf_faultrepair.Explain,RepairPerson,RepairExplain)"+
//		                		"values"+
//		                		"('"+id+"','"+FaultCode+"','"+ReportOrganiseUnit+"','"+EquipmentID+"','"+FaultType+"','"+ReportUser+"','"
//		                		+ReportTime+"','"+IsRFIDScan+"','"+IsBarCodeScan+"','"+Status+"','"+EffectiveStatus+"','"+CreatedBy+"','"
//		                		+CreatedDate+"','"+ModifiedBy+"','"+ModifiedDate+"','"+weixiunshijian+"','"
//		                		+Explain+"','"+weixiuren+"','"+RepairExplain+"')";		 
		                String id = UUID.randomUUID().toString();  
		                System.out.println("=========uuid============"+id);
                        pstmt.setString(1, id); 
                        pstmt.setString(2, FaultCode); 
                        pstmt.setString(3, ReportOrganiseUnit); 
                        pstmt.setString(4, EquipmentID); 
                        pstmt.setString(5, FaultType); 
                        pstmt.setString(6, ReportUser); 
                        pstmt.setString(7, ReportTime); 
                        pstmt.setString(8, IsRFIDScan); 
                        pstmt.setString(9, IsBarCodeScan); 
                        pstmt.setString(10, Status); 
                        pstmt.setString(11, EffectiveStatus); 
                        pstmt.setString(12, CreatedBy); 
                        pstmt.setString(13, CreatedDate); 
                        pstmt.setString(14, ModifiedBy); 
                        pstmt.setString(15, ModifiedDate); 
                        pstmt.setString(16, weixiunshijian); 
                        pstmt.setString(17, Explain); 
                        pstmt.setString(18, weixiuren); 
                        pstmt.setString(19, RepairExplain); 
                        //加入批处理 
                        pstmt.addBatch(); 
		                
		                System.out.println("====sqlzong===new==="+pstmt.toString());
		                if (sheetIndex % 10000 == 0){
		                	pstmt.executeBatch();
		                	conn.commit();
		                }
	                }else{
	                	if(rowIndex==1){
		                	System.out.println("=========文件数据不正确============");
		                	break;
	                	}
	                }               
	            }
	        }
	        pstmt.executeBatch();    //执行批处理 
	        conn.commit();
	        pstmt.close(); 
        } catch (SQLException e) {   
    		e.printStackTrace();   
    	}  
    	getExcelConn.closeconn(conn);      
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