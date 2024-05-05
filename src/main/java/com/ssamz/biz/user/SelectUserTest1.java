package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssamz.biz.common.JDBCUtil;

public class SelectUserTest1 {
	public static void main(String[] args) {
		//JDBC 관련 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			//JDBC 3단계 : Statement 생성
			String sql = "SELECT * FROM USERS";
			stmt = conn.prepareStatement(sql);
			
			//JDBC 4단계 : SQL 전송
			//insert, update, delete 의 경우는 executeUpdate()를 사용, select에서는 executeQuery()로 실행결과를 ResultSet객체에 리턴
			rs = stmt.executeQuery();
			
			//JDBC 5단계 : 조회 결과 사용
			System.out.println("[ USER LIST ]");
			
			//rs.next()는 row가 존재하는지 판단여부를 정할때 while문과함께 사용되나 여기에서는 단 하나의 row만 출력하기위해 그냥 실행시켜서 다음 커서로 이동시키고 끝.
//			rs.next();	
//			System.out.print(rs.getString("ID") + " : ");
//			System.out.print(rs.getString("PASSWORD") + " : ");
//			System.out.print(rs.getString("NAME") + " : ");
//			System.out.println(rs.getString("ROLE"));
			
			//while 사용으로 모든 row의 data를 출력하는 case
			while(rs.next()) {
				System.out.print(rs.getString("ID") + " : ");
				System.out.print(rs.getString("PASSWORD") + " : ");
				System.out.print(rs.getString("NAME") + " : ");
				System.out.println(rs.getString("ROLE"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, stmt, conn);
		}
	}
}
