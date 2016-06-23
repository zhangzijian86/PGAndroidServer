package com.pg.web;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.net.URL;  
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
  
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
  
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
      
        String aFileName = request.getParameter("fileName");   
        String online = request.getParameter("online");  
        FileInputStream in = null;   
        ServletOutputStream out = null;   
        boolean isOnLine = online != null ? true : false ;  
        try {  
          
              
            if(isOnLine){   
                URL u = new URL("file:///"+fileDir + aFileName);  
                response.setContentType(u.openConnection().getContentType());  
                response.setHeader("Content-Disposition", "inline; filename="+aFileName);     
             }  
            else{   
                response.setContentType("application/x-msdownload");   
                response.setHeader("Content-Disposition", "attachment; filename=" + aFileName);   
            }  
          
            in = new FileInputStream(fileDir + aFileName);   
            out = response.getOutputStream();  
            out.flush();  
            int aRead = 0;  
            while ((aRead = in.read()) != -1 & in != null) {  
                out.write(aRead);  
             }  
            out.flush();  
              
        } catch (Throwable e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                in.close();  
                out.close();  
            } catch (Throwable e) {  
                e.printStackTrace();  
            }  
        }  
  
    }  
  
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
                    item.write(new File(fileDir + newFileName));  
                } else { // 表单  
                    String fieldName = item.getFieldName();  
                    if ("uploader".equals(fieldName)) {  
                        uploader = item.getString();  
                    } else if ("date".equals(fieldName)) {  
                        date = item.getString();  
                    }  
                }  
            }  
            request.setAttribute("fileNames",fileNames);  
            request.getRequestDispatcher("uploadFileResult.jsp").forward(request, response);  
        } catch (Exception e) {  
  
        }  
    }  
  
}  