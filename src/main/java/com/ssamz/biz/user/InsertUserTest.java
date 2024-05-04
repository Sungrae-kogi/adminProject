package com.ssamz.biz.user;

import com.ssamz.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class InsertUserTest {
	public static void main(String[] args) {
		//JDBC 관련 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = JDBCUtil.getConnection();
			
			//JDBC 3단계 : Statement 생성
			String sql = "INSERT INTO USERS(id, password, name, role) values(?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			//JDBC 4단계 : SQL 전송
			//값 설정 -> parameter ? 는 자동으로 sql이 실해오디는 순서대로 번호가 부여된다
			//그 과정에서 반드시 column의 type을 확인하여 setString, setInt, setDate등을 맞게사용
			stmt.setString(1, "ssamz3");
			stmt.setString(2, "ssamz123");
			stmt.setString(3, "쌤즈");
			stmt.setString(4, "ADMIN");
			
			//SQL 전송
			//executeUpdate() 실행결과(처리된 row의 갯수)를 int값으로 반환 -> SELECT 제외 다른 구문수행시 (INSERT, DELETE, UPDATE), CREATE / DROP 관련에서는 -1 리턴
			int count = stmt.executeUpdate();
			System.out.println(count + " data process success");
			
			
//			if(stmt != null) {
//				System.out.println("Statement Object : " + stmt.toString());
//			}
			
//			if(conn != null) {
//				System.out.println("H2 connected : " + conn.toString());
//			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {	//일반적으로 연결해제 작업은 예외발생과 무관하게 실행되는 finally 블록에 작성한다.
			//JDBC 5단계 : 연결 해제
			JDBCUtil.close(stmt, conn);
		}
		
	}
}
