package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssamz.biz.common.JDBCUtil;

public class DeleteUserTest {
	public static void main(String[] args) {
		//1. UserDAO 객체 생성
		UserDAO dao = new UserDAO();
		
		//2. 회우너 정보 삭제
		dao.deleteUser("ssamz3");
		
		//3. 목록 조회
		dao.getUserList();
	}
}
