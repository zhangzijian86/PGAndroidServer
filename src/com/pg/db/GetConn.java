package com.pg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {
	public Connection getConnection() 
	{ 
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:/home/zzj/web/webWorks/PGAndroidServer/PGAndroidServer.db");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {	
			e.printStackTrace();
		}   	
		return connection;
	}
	
	public void closeconn(Connection connection)
	{  	 
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
