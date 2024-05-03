package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertUserTest {
	public static void main(String[] args) {
		//JDBC 관련 변수
		Connection conn = null;
		
		
		try {
			//JDBC 1단계 : 드라이버 객체 로딩
			DriverManager.registerDriver(new org.h2.Driver());
			
			//JDBC 2단계 : 커넥션 연결
			String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa", "");
			
			if(conn != null) {
				System.out.println("H2 연결 성공 : " + conn.toString());
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
