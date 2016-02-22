package com.pg.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.pg.bean.Ppdr_dailyrecycle;
import com.pg.db.GetConn;

public class OrderManager {
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
}
