package com.pg.web;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.pg.bean.Pgdr_user;
import com.pg.bean.Ppdr_dailyrecycle;
import com.pg.db.GetConn;

public class OrderManager {
	
	private static final String appKey = "a21bcb87918d9c6c5f28d05a";   //7004f4cfd9fbb5ff19cc1a7d    cai 934ee5200b34ec97d469a7f1
	private static final String masterSecret = "3984c07df41e404e579c81f6"; 
	JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	
	public int updateUser(String id,String photonumber)
	{
		PushPayload payload  = null;
		GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update PGDR_DAILYRECYCLE "
													+ "set DAILYRECYCLE_RECYCLINGMANPHONE = ? "
													+ "where DAILYRECYCLE_ID = ? "
													);
			ps.setString(1,photonumber);
			ps.setString(2,id);		
			System.out.println("=updateUser=sql="+ps.toString());
			i=ps.executeUpdate();
			if(i>0){
				payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(photonumber))
                .setNotification(Notification.alert("您有一个订单需要提取！"))
                .build();//				      
		        PushResult result = jpushClient.sendPush(payload);
		        System.out.println("=updateUser=result="+result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (APIConnectionException ex) {
			// Connection error, should retry later
			System.out.println("Connection error, should retry later"+ ex.toString());
		} catch (APIRequestException ex) {
			// Should review the error, and fix the request
	    	 System.out.println("Should review the error, and fix the request"+ ex);
	    	 System.out.println("Should review the error, and fix the request"+ ex);
	    	 System.out.println("HTTP Status: " + ex.getStatus());
	    	 System.out.println("Error Code: " + ex.getErrorCode());
	    	 System.out.println("Error Message: " + ex.getErrorMessage());
		}
		getConn.closeconn(conn);
		return i;		
	}
	
	public Pgdr_user[] getUsers() 
	{
		int rows;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		Pgdr_user[] pusers = null;
		try {
			PreparedStatement ps=conn.prepareStatement("select USER_ID,USER_MOBILE,USER_NAME,USER_PASSWORD"
					+ ",USER_ADDRESS,USER_EMAIL,USER_STATUS,USER_TYPE,USER_PHOTO"
					+ " from PGDR_USER where USER_TYPE = '2'");
			rs=ps.executeQuery();
			if(rs!=null){    		
	    		rs.last();
	    		rows = rs.getRow();
	    		rs.beforeFirst();
	    		pusers = new Pgdr_user[rows];
	    		for(int i=0;i<rows;i++)
		    	{	    			
		    		rs.next();
		    		pusers[i] = new Pgdr_user();
		    		pusers[i].setUser_id(rs.getString("USER_ID"));
		    		pusers[i].setUser_name(rs.getString("USER_NAME"));
		    		pusers[i].setUser_password(rs.getString("USER_PASSWORD"));
		    		pusers[i].setUser_mobile(rs.getString("USER_MOBILE"));
		    		pusers[i].setUser_address(rs.getString("USER_ADDRESS"));
		    		pusers[i].setUser_email(rs.getString("USER_EMAIL"));
		    		pusers[i].setUser_status(rs.getString("USER_STATUS"));
		    		pusers[i].setUser_type(rs.getString("USER_TYPE"));
		    		pusers[i].setUser_photo(rs.getString("USER_PHOTO"));
		    		pusers[i].setUser_return(true);
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pusers;
	}
	
	public Pgdr_user[] getApplyUsers() 
	{
		int rows;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		Pgdr_user[] pusers = null;
		try {
			PreparedStatement ps=conn.prepareStatement("select USER_ID,USER_MOBILE,USER_NAME,USER_PASSWORD"
					+ ",USER_ADDRESS,USER_EMAIL,USER_STATUS,USER_TYPE,USER_PHOTO"
					+ " from PGDR_USER order by USER_TYPE desc");
			rs=ps.executeQuery();
			if(rs!=null){    		
	    		rs.last();
	    		rows = rs.getRow();
	    		rs.beforeFirst();
	    		pusers = new Pgdr_user[rows];
	    		for(int i=0;i<rows;i++)
		    	{	    			
		    		rs.next();
		    		pusers[i] = new Pgdr_user();
		    		pusers[i].setUser_id(rs.getString("USER_ID"));
		    		pusers[i].setUser_name(rs.getString("USER_NAME"));
		    		pusers[i].setUser_password(rs.getString("USER_PASSWORD"));
		    		pusers[i].setUser_mobile(rs.getString("USER_MOBILE"));
		    		pusers[i].setUser_address(rs.getString("USER_ADDRESS"));
		    		pusers[i].setUser_email(rs.getString("USER_EMAIL"));
		    		pusers[i].setUser_status(rs.getString("USER_STATUS"));
		    		pusers[i].setUser_type(rs.getString("USER_TYPE"));
		    		pusers[i].setUser_photo(rs.getString("USER_PHOTO"));
		    		pusers[i].setUser_return(true);
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pusers;
	}
	
	public int updateUserType(String photonumber)
	{
		PushPayload payload  = null;
		GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update PGDR_USER "
													+ "set USER_TYPE = 2 "
													+ "where USER_MOBILE = ? "
													);
			ps.setString(1,photonumber);	
			System.out.println("=updateUser=sql="+ps.toString());
			i=ps.executeUpdate();
			if(i>0){
				payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(photonumber))
                .setNotification(Notification.alert("您的取货资格审核通过！"))
                .build();//				      
		        PushResult result = jpushClient.sendPush(payload);
		        System.out.println("=updateUser=result="+result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (APIConnectionException ex) {
			// Connection error, should retry later
			System.out.println("Connection error, should retry later"+ ex.toString());
		} catch (APIRequestException ex) {
			// Should review the error, and fix the request
	    	 System.out.println("Should review the error, and fix the request"+ ex);
	    	 System.out.println("Should review the error, and fix the request"+ ex);
	    	 System.out.println("HTTP Status: " + ex.getStatus());
	    	 System.out.println("Error Code: " + ex.getErrorCode());
	    	 System.out.println("Error Message: " + ex.getErrorMessage());
		}
		getConn.closeconn(conn);
		return i;		
	}
	
	
	public Ppdr_dailyrecycle[] getRecycle() 
	{
		GetConn getConn=new GetConn();
		Ppdr_dailyrecycle[] pdr = null;
		ResultSet rs = null;
		int rows;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement(
					  "select "
					+ "DAILYRECYCLE_ID,DAILYRECYCLE_USER_MOBILE,DAILYRECYCLE_DATE,"
					+ "DAILYRECYCLE_WEEK,DAILYRECYCLE_ISCYCLE,DAILYRECYCLE_CYCLETYPE,"
					+ "DAILYRECYCLE_ISVALID,DAILYRECYCLE_STATUS,DAILYRECYCLE_RECYCLINGMANPHONE,"
					+ "DAILYRECYCLE_FINISHTIME,DAILYRECYCLE_TYPE,DAILYRECYCLE_EXPLAIN,"
					+ "DAILYRECYCLE_ADDRESS,DAILYRECYCLE_NAME "
					+ "from PGDR_DAILYRECYCLE where "
					+ "DAILYRECYCLE_ISVALID = 1 "
					+ "order by DAILYRECYCLE_STATUS "
					);
			System.out.println("=getRecycle=sql="+ps.toString());
			rs=ps.executeQuery();
			if(rs!=null){    		
	    		rs.last();
	    		rows = rs.getRow();
	    		rs.beforeFirst();
	    		pdr = new Ppdr_dailyrecycle[rows];
	    		for(int i=0;i<rows;i++)
		    	{	    			
		    		rs.next();
		    		pdr[i] = new Ppdr_dailyrecycle();
		    		pdr[i].setDailyrecycle_id(rs.getString("DAILYRECYCLE_ID"));
		    		pdr[i].setDailyrecycle_user_mobile(rs.getString("DAILYRECYCLE_USER_MOBILE"));				
		    		pdr[i].setDailyrecycle_date(rs.getString("DAILYRECYCLE_DATE"));				
		    		pdr[i].setDailyrecycle_week(rs.getString("DAILYRECYCLE_WEEK"));
		    		pdr[i].setDailyrecycle_iscycle(rs.getString("DAILYRECYCLE_ISCYCLE"));
		    		pdr[i].setDailyrecycle_cycletype(rs.getString("DAILYRECYCLE_CYCLETYPE"));				
		    		pdr[i].setDailyrecycle_isvalid(rs.getString("DAILYRECYCLE_ISVALID"));
					pdr[i].setDailyrecycle_status(rs.getString("DAILYRECYCLE_STATUS"));
					pdr[i].setDailyrecycle_recyclingmanphone(rs.getString("DAILYRECYCLE_RECYCLINGMANPHONE"));				
					pdr[i].setDailyrecycle_finishtime(rs.getString("DAILYRECYCLE_FINISHTIME"));
					pdr[i].setDailyrecycle_type(rs.getString("DAILYRECYCLE_TYPE"));
					pdr[i].setDailyrecycle_explain(rs.getString("DAILYRECYCLE_EXPLAIN"));				
					pdr[i].setDailyrecycle_address(rs.getString("DAILYRECYCLE_ADDRESS"));
					pdr[i].setDailyrecycle_name(rs.getString("DAILYRECYCLE_NAME"));
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("=getRecycle==pdrlist==="+pdr.length);
		return pdr;
	}
	
	public Ppdr_dailyrecycle getModifyRecycle(String id) 
	{
		GetConn getConn=new GetConn();
		Ppdr_dailyrecycle pdr = null;
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement(
					  "select "
					+ "DAILYRECYCLE_ID,DAILYRECYCLE_USER_MOBILE,DAILYRECYCLE_DATE,"
					+ "DAILYRECYCLE_WEEK,DAILYRECYCLE_ISCYCLE,DAILYRECYCLE_CYCLETYPE,"
					+ "DAILYRECYCLE_ISVALID,DAILYRECYCLE_STATUS,DAILYRECYCLE_RECYCLINGMANPHONE,"
					+ "DAILYRECYCLE_FINISHTIME,DAILYRECYCLE_TYPE,DAILYRECYCLE_EXPLAIN,"
					+ "DAILYRECYCLE_ADDRESS,DAILYRECYCLE_NAME "
					+ "from PGDR_DAILYRECYCLE where "
					+ "DAILYRECYCLE_ISVALID = 1 and DAILYRECYCLE_ID = ? "
					+ "order by DAILYRECYCLE_STATUS "
					);
			ps.setString(1,id);	
			System.out.println("=getRecycle=sql="+ps.toString());
			rs=ps.executeQuery();
			if(rs!=null){    		
				rs.next();
	    		pdr = new Ppdr_dailyrecycle();    			
	    		pdr.setDailyrecycle_id(rs.getString("DAILYRECYCLE_ID"));
	    		pdr.setDailyrecycle_user_mobile(rs.getString("DAILYRECYCLE_USER_MOBILE"));				
	    		pdr.setDailyrecycle_date(rs.getString("DAILYRECYCLE_DATE"));				
	    		pdr.setDailyrecycle_week(rs.getString("DAILYRECYCLE_WEEK"));
	    		pdr.setDailyrecycle_iscycle(rs.getString("DAILYRECYCLE_ISCYCLE"));
	    		pdr.setDailyrecycle_cycletype(rs.getString("DAILYRECYCLE_CYCLETYPE"));				
	    		pdr.setDailyrecycle_isvalid(rs.getString("DAILYRECYCLE_ISVALID"));
	    		pdr.setDailyrecycle_status(rs.getString("DAILYRECYCLE_STATUS"));
	    		pdr.setDailyrecycle_recyclingmanphone(rs.getString("DAILYRECYCLE_RECYCLINGMANPHONE"));				
	    		pdr.setDailyrecycle_finishtime(rs.getString("DAILYRECYCLE_FINISHTIME"));
	    		pdr.setDailyrecycle_type(rs.getString("DAILYRECYCLE_TYPE"));
	    		pdr.setDailyrecycle_explain(rs.getString("DAILYRECYCLE_EXPLAIN"));				
	    		pdr.setDailyrecycle_address(rs.getString("DAILYRECYCLE_ADDRESS"));
	    		pdr.setDailyrecycle_name(rs.getString("DAILYRECYCLE_NAME"));		    	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pdr;
	}
}
