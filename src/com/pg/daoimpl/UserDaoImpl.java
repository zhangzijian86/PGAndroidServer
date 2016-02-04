package com.pg.daoimpl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pg.bean.Pgdr_user;
import com.pg.bean.Ppdr_dailyrecycle;
import com.pg.db.GetConn;


public class UserDaoImpl 
{
	public Pgdr_user login(String usermobile,String password) 
	{
		boolean b = false;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		Pgdr_user puser = new Pgdr_user();
		try {
			PreparedStatement ps=conn.prepareStatement("select USER_ID,USER_MOBILE,USER_NAME,USER_PASSWORD"
					+ ",USER_ADDRESS,USER_EMAIL,USER_STATUS,USER_TYPE,USER_PHOTO"
					+ " from PGDR_USER where USER_MOBILE=? and USER_PASSWORD=?");
			ps.setString(1,usermobile);
			ps.setString(2,password);
			rs=ps.executeQuery();
			if (rs.next())
			{
				b=true;
				puser.setUser_id(rs.getString("USER_ID"));
				puser.setUser_name(rs.getString("USER_NAME"));
				puser.setUser_password(rs.getString("USER_PASSWORD"));
				puser.setUser_mobile(rs.getString("USER_MOBILE"));
				puser.setUser_address(rs.getString("USER_ADDRESS"));
				puser.setUser_email(rs.getString("USER_EMAIL"));
				puser.setUser_status(rs.getString("USER_STATUS"));
				puser.setUser_type(rs.getString("USER_TYPE"));
				puser.setUser_photo(rs.getString("USER_PHOTO"));
				puser.setUser_return(true);
			}
			else
			{
				puser.setUser_return(false);
				b=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return puser;
	}
	
	public List<Ppdr_dailyrecycle> getRecycle(String phoneNumber) 
	{
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		List<Ppdr_dailyrecycle> pdrlist = new ArrayList<Ppdr_dailyrecycle>();
		try {
			PreparedStatement ps=conn.prepareStatement(
					  "select * from (select "
					+ "DAILYRECYCLE_ID,DAILYRECYCLE_USER_MOBILE,DAILYRECYCLE_DATE,"
					+ "DAILYRECYCLE_WEEK,DAILYRECYCLE_ISCYCLE,DAILYRECYCLE_CYCLETYPE,"
					+ "DAILYRECYCLE_ISVALID,DAILYRECYCLE_STATUS,DAILYRECYCLE_RECYCLINGMANPHONE,"
					+ "DAILYRECYCLE_FINISHTIME,DAILYRECYCLE_TYPE,DAILYRECYCLE_EXPLAIN,"
					+ "DAILYRECYCLE_ADDRESS,DAILYRECYCLE_NAME "
					+ "from PGDR_DAILYRECYCLE where DAILYRECYCLE_USER_MOBILE=? "
					+ "and DAILYRECYCLE_ISVALID = 1 "
					+ "order by DAILYRECYCLE_STATUS) t1 "
					+ "UNION "
					+ "select * from (select "
					+ "DAILYRECYCLE_ID,DAILYRECYCLE_USER_MOBILE,DAILYRECYCLE_DATE,"
					+ "DAILYRECYCLE_WEEK,DAILYRECYCLE_ISCYCLE,DAILYRECYCLE_CYCLETYPE,"
					+ "DAILYRECYCLE_ISVALID,DAILYRECYCLE_STATUS,DAILYRECYCLE_RECYCLINGMANPHONE,"
					+ "DAILYRECYCLE_FINISHTIME,DAILYRECYCLE_TYPE,DAILYRECYCLE_EXPLAIN,"
					+ "DAILYRECYCLE_ADDRESS,DAILYRECYCLE_NAME "
					+ "from PGDR_DAILYRECYCLE where DAILYRECYCLE_RECYCLINGMANPHONE=? "
					+ "and DAILYRECYCLE_ISVALID = 1 "
					+ "order by DAILYRECYCLE_STATUS) t2 "
					);
			ps.setString(1,phoneNumber);
			ps.setString(2,phoneNumber);
			System.out.println("=getRecycle=sql="+ps.toString());
			rs=ps.executeQuery();
			while (rs.next())
			{
				Ppdr_dailyrecycle pdr = new Ppdr_dailyrecycle();
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
				pdrlist.add(pdr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("=getRecycle==pdrlist==="+pdrlist.size());
		return pdrlist;
	}
	
	public boolean register(Pgdr_user pgdr_user)
	{
		boolean b=false;
		GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("insert into PGDR_USER (USER_MOBILE,USER_PASSWORD) values (?,?)");
			ps.setString(1,pgdr_user.getUser_mobile());
			ps.setString(2,pgdr_user.getUser_password());
			i=ps.executeUpdate();
			if (i>0)
			{
				b=true;
			}
			else
			{
				b=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return b;		
	}
	
	public boolean updateUser(Pgdr_user pgdr_user)
	{
		System.out.println("====UpdateUser=============33======");
		boolean b=false;
		GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update PGDR_USER "
													+ "set USER_NAME = ? , "
													+ "USER_PASSWORD = ? , "
													+ "USER_ADDRESS = ? , "
													+ "USER_EMAIL = ? , "
													+ "USER_STATUS = ? , "
													+ "USER_TYPE = ? , "
													+ "USER_PHOTO = ?  "
													+ "where USER_MOBILE = ? "
													);
			System.out.println("====UpdateUser=============44======");
			ps.setString(1,pgdr_user.getUser_name());
			ps.setString(2,pgdr_user.getUser_password());	
			String address = pgdr_user.getUser_address();
			System.out.println("====UpdateUser=============55======");
			try {
				String addressstr = new String(address.getBytes("UTF-8"));
				ps.setString(3,addressstr);
				System.out.println("====UpdateUser=============66======"+addressstr);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}			
			//ps.setString(3,pgdr_user.getUser_address());
			ps.setString(4,pgdr_user.getUser_email());
			ps.setString(5,pgdr_user.getUser_status());
			ps.setString(6,pgdr_user.getUser_type());
			ps.setString(7,pgdr_user.getUser_photo());
			ps.setString(8,pgdr_user.getUser_mobile());
			System.out.println("====UpdateUser=============77====sql=="+ps.toString());
			i=ps.executeUpdate();
			if (i>0)
			{
				b=true;
			}
			else
			{
				b=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return b;		
	}
	
	public boolean addRecycle(Ppdr_dailyrecycle pgdr_recycle)
	{
		System.out.println("====addRecycle=============33======");
		boolean b=false;
		GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("insert into PGDR_DAILYRECYCLE("
			+ "DAILYRECYCLE_USER_MOBILE,DAILYRECYCLE_DATE,DAILYRECYCLE_WEEK,"
			+ "DAILYRECYCLE_ISCYCLE,DAILYRECYCLE_CYCLETYPE,DAILYRECYCLE_ISVALID,"
			+ "DAILYRECYCLE_STATUS,DAILYRECYCLE_RECYCLINGMANPHONE,DAILYRECYCLE_FINISHTIME,"
			+ "DAILYRECYCLE_TYPE,DAILYRECYCLE_EXPLAIN,DAILYRECYCLE_ADDRESS,DAILYRECYCLE_NAME"
			+ ")values("
			+ "?,?,?,"
			+ "?,?,?,"
			+ "?,?,?,"
			+ "?,?,?,"
			+ "?)"
			);
			System.out.println("====addRecycle=============44=====sql="+ps.toString());
			ps.setString(1,pgdr_recycle.getDailyrecycle_user_mobile());
			ps.setString(2,pgdr_recycle.getDailyrecycle_date());		
			ps.setString(3,pgdr_recycle.getDailyrecycle_week());
			ps.setString(4,pgdr_recycle.getDailyrecycle_iscycle());
			ps.setString(5,pgdr_recycle.getDailyrecycle_cycletype());
			ps.setString(6,pgdr_recycle.getDailyrecycle_isvalid());
			ps.setString(7,pgdr_recycle.getDailyrecycle_status());
			ps.setString(8,pgdr_recycle.getDailyrecycle_recyclingmanphone());
			ps.setString(9,pgdr_recycle.getDailyrecycle_finishtime());
			ps.setString(10,pgdr_recycle.getDailyrecycle_type());
			ps.setString(11,pgdr_recycle.getDailyrecycle_explain());
			String address = pgdr_recycle.getDailyrecycle_address();
			System.out.println("====addRecycle=============55======");
			try {
				String addressstr = new String(address.getBytes("UTF-8"));
				ps.setString(12,addressstr);
				System.out.println("====UpdateUser=============66======"+addressstr);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}			
			ps.setString(13,pgdr_recycle.getDailyrecycle_name());
			System.out.println("====addRecycle=============77====sql=="+ps.toString());
			i=ps.executeUpdate();
			if (i>0)
			{
				b=true;
			}
			else
			{
				b=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return b;		
	}
	
	public List<Pgdr_user> selectAlluser ()
	{
		List<Pgdr_user> list=new ArrayList<Pgdr_user>();
		GetConn getConn=new GetConn();	
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from PGDR_USER");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	Pgdr_user user=new Pgdr_user();
				user.setUser_mobile(rs.getString(1));
				list.add(user);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return list;
	}
	public List<Ppdr_dailyrecycle> selectAllcontact (String username)
	{
		List<Ppdr_dailyrecycle> list=new ArrayList<Ppdr_dailyrecycle>();
		GetConn getConn=new GetConn();	
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select DAILYRECYCLE_ID ,"+
					       "DAILYRECYCLE_USER_MOBILE ,"+
					       "DAILYRECYCLE_DATE ,"+
					       "DAILYRECYCLE_WEEK ,"+
					       "DAILYRECYCLE_ISCYCLE ,"+
					       "DAILYRECYCLE_CYCLETYPE ,"+
					       "DAILYRECYCLE_ISVALID ,"+
					       "DAILYRECYCLE_STATUS ,"+
					       "DAILYRECYCLE_RECYCLINGMANPHONE ,"+
					       "DAILYRECYCLE_FINISHTIME ,"+
					       "from PGDR_DAILYRECYCLE where DAILYRECYCLE_USER_MOBILE=? or DAILYRECYCLE_RECYCLINGMANPHONE =? order by DAILYRECYCLE_USER_MOBILE");
          ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
          while (rs.next()){
            	Ppdr_dailyrecycle ppdr_dailyrecycle=new Ppdr_dailyrecycle();
            	ppdr_dailyrecycle.setDailyrecycle_id(rs.getString(0));
            	ppdr_dailyrecycle.setDailyrecycle_user_mobile(rs.getString(1));
            	ppdr_dailyrecycle.setDailyrecycle_date(rs.getString(2));
            	ppdr_dailyrecycle.setDailyrecycle_week(rs.getString(3));
            	ppdr_dailyrecycle.setDailyrecycle_iscycle(rs.getString(4));
            	ppdr_dailyrecycle.setDailyrecycle_cycletype(rs.getString(5));
            	ppdr_dailyrecycle.setDailyrecycle_isvalid(rs.getString(6));
            	ppdr_dailyrecycle.setDailyrecycle_status(rs.getString(7));
            	ppdr_dailyrecycle.setDailyrecycle_recyclingmanphone(rs.getString(8));
            	ppdr_dailyrecycle.setDailyrecycle_finishtime(rs.getString(9));				
				list.add(ppdr_dailyrecycle);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return list;
	}
	public boolean check(String user_mobile) 
	{
		System.out.println("====check=============00======");
		boolean b = false;
		System.out.println("====check=============11======");
		GetConn getConn=new GetConn();
		System.out.println("====check=============22======");
		ResultSet rs = null;
		System.out.println("====check=============33======");
		Connection conn=getConn.getConnection();
		System.out.println("====check=============44======");
		try {
			System.out.println("====check=========55==========");
			PreparedStatement ps=conn.prepareStatement("select * from PGDR_USER where USER_MOBILE=?");
			System.out.println("====check=========66==========");
			ps.setString(1,user_mobile);
			System.out.println("====check=========77==========");
			rs=ps.executeQuery();
			if (rs.next())
			{
				System.out.println("====check=========88==========");
				b=true;
			}
			else
			{
				System.out.println("====check=========99==========");
				b=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return b;
	}
	
}
