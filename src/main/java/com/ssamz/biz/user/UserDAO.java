package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssamz.biz.common.JDBCUtil;

//DAO pattern
//DAO class가 DB에 접근하여 실질적인 DB연동작업을 담당

public class UserDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// USERS 테이블 관련 SQL 명령어
	private String USER_LIST = "SELECT * FROM USERS";
	private String USER_INSERT = "INSERT INTO USERS values(?, ?, ?, ?)";
	private String USER_UPDATE = "UPDATE USERS SET name = ?, role = ? where id = ?";
	private String USER_DELETE = "DELETE USERS WHERE id = ?";
	private String USER_GET = "SELECT * FROM USERS WHERE id = ?";

	// USERS 테이블 관련 CRUD 메소드
	// 회원 목록 조회
	public List<UserVO> getUserList(){	//generic type?
		List<UserVO> userList = new ArrayList<UserVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
				userList.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}

	// 회원 등록
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	
	//회원 수정
	public void updateUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_UPDATE);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getRole());
			stmt.setString(3, vo.getId());
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	
	//회원 삭제
	public void deleteUser(String id) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_DELETE);
			stmt.setString(1, id);
			stmt.executeUpdate();
					
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//회원 상세 조회
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				user = new UserVO();	//여기에서 UerVO() 객체를 생성 및 할당.
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		return user;
	}
	
	
}
