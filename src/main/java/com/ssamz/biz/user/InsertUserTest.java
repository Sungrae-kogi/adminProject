package com.ssamz.biz.user;

import com.ssamz.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class InsertUserTest {
	public static void main(String[] args) {
		//1. UserDAO 객체 생성
		UserDAO dao = new UserDAO();
		
		//2. 회원 정보 등록.
		//VO class 사용
		UserVO vo = new UserVO();
		vo.setName("쌤즈");
		vo.setRole("USER");
		vo.setId("ssamz4");
		vo.setPassword("ssamz123");
		
		
		dao.insertUser(vo);
		
		//3. 목록 조회
		dao.getUserList();
		
	}
}
