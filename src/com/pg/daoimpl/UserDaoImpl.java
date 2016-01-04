package com.pg.daoimpl;

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
	public boolean login(String usermobile,String password) 
	{
		boolean b = false;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from PGDR_USER where USER_MOBILE=? and USER_PASSWORD=?");
			ps.setString(1,usermobile);
			ps.setString(2,password);
			rs=ps.executeQuery();
			if (rs.next())
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
		return b;
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
