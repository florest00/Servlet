package com.kh.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	
	public static Connection getConn() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1522:xe", "C##KH", "1234");
		conn.setAutoCommit(false);
		return conn;
		}
}
