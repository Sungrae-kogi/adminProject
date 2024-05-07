package com.ssamz.web.user;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ssamz.biz.user.UserDAO;
import com.ssamz.biz.user.UserVO;

/**
 * Servlet implementation class InsertUserServlet
 */
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("init() method started");
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
		System.out.println("service() method started");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 사용자 입력 정보 추출
		//HttpServletRequest 객체에 사용자에서 입력한 정보들이 헤더, body, 쿠키 등에 포함
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		String selfInfo = request.getParameter("selfInfo");
		String[] languages = request.getParameterValues("languages");
		String age = request.getParameter("age");
		
		//2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setRole(role);
		
		UserDAO dao = new UserDAO();
		dao.insertUser(vo);
		
		//3. 화면 이동
		//sendRedirect(String redirectPage) 메소드는 응답받은 브라우저가 다시 한번 redirectPage로 지정한 요청('login.html')을 서버에 전달하도록 한다.
		response.sendRedirect("login.html");
		
		
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + password);
		System.out.println("이름 : " + name);
		System.out.println("권한 : " + role);
		System.out.println("자기 소개 : " + selfInfo);
		System.out.println("언어 경험");
		for(String language : languages) {
			System.out.print(language + ", ");
		}
		System.out.println();
		System.out.println("나이 : " + age);
	}

	


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("Tomcat 종료 Servlet을 destory합니다.");
	}

}
