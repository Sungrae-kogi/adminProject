package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssamz.biz.common.JDBCUtil;

public class UpdateUserTest {
	public static void main(String[] args) {
		//1. UserDAO 객체 생성
		UserDAO dao = new UserDAO();
		
		//2. 회원 정보 수정.
		UserVO vo = new UserVO();
		vo.setName("수정");
		vo.setRole("USER");
		vo.setId("ssamz4");
		
		dao.updateUser(vo);
		
		//3.목록 조회
		dao.getUserList();
	}
}
