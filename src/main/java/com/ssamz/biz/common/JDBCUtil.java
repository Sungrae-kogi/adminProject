package com.ssamz.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//static메소드로 구현하면 객체 생성 없이 바로 호출이 가능.

public class JDBCUtil {
	public static Connection getConnection() {
		//JDBC 관련 변수
		Connection conn = null;
		
		try {
			//JDBC 1단계 : 드라이버 객체 로딩
			DriverManager.registerDriver(new org.h2.Driver());
			
			//JDBC 2단계 : 커넥션 연결
			String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa", "");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static void close(PreparedStatement stmt, Connection conn) {
		//JDBC 5단계 : 연결 해제
		try {
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Overloading	-	SELECT에서 사용하는 ResultSet까지 close()
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		//JDBC 5단계 : 연결 해제
		try {
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
