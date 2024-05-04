package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssamz.biz.common.JDBCUtil;

public class UpdateUserTest {
	public static void main(String[] args) {
		//JDBC 관련 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			//JDBC 3단계 : Statement 생성
			String sql = "UPDATE USERS SET name=?, role=? WHERE id=?";
			stmt = conn.prepareStatement(sql);
			
			//JDBC 4단계 : SQL 전송
			//? 값 설정
			stmt.setString(1, "수정");
			stmt.setString(2, "USER");
			stmt.setString(3, "ssamz2");
			
			//SQL 전송
			int count = stmt.executeUpdate();
			System.out.println(count + " data process success!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
