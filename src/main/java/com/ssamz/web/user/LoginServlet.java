package com.ssamz.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.ssamz.biz.user.UserDAO;
import com.ssamz.biz.user.UserVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("===> LoginServlet 생성");
    }

    public void init() throws ServletException {
    	System.out.println("---> init() 호출");
    }
    
    //HttpServletRequest 객체는 내부 메소드를사용하여 사용자가 입력한 정보를 추출할 수 있다.
    //사용자가 입력한 정보 뿐 아니라 브라우저 및 요청과 관련한 모든 정보를 얻을 수 있고, 서블릿에서는 이 정보를 이용하여 사용자가 요청한 작업을 처리할 수 있다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	//1. 사용자 입력 정보 추출
    	String id = request.getParameter("id");
    	String password = request.getParameter("password");
    	
    	//2. DB 연동 처리
    	UserVO vo = new UserVO();
    	vo.setId(id);	//사용자가 전달하고 request에 담긴 id값을 생성한 vo 객체에 할당
    	
    	UserDAO dao = new UserDAO();
    	UserVO user = dao.getUser(vo);	//찾고자 하는 id값이 담긴 vo객체를 dao의 특정 유저를 검색하는 메소드 getUser에 인자로 전달. 그 결과값을 user 에 할당
    	
    	//3. 응답 화면 구성
    	//응답 메시지에 대한 인코딩 설정
    	response.setContentType("text/html;charset=UTF-8");
    	//HTTP 응답 프로토콜 message-body와 연결된 출력 스트림 획득
    	PrintWriter out = response.getWriter();
    	
    	//메시지 출력
    	if(user != null) {
    		if(user.getPassword().equals(password)) {
    			out.println(user.getName() + "님 로그인 환영!<br>");
    			out.println("<a href='/getBoardList.do'>글 목록 이동</a>");
    		}else {
    			out.println("비밀번호 오류입니다. <br>");
    			out.println("<a href='/'>다시 로그인</a>");
    		}
    	}else {
    		out.println("아이디 오류입니다. <br>");
    		out.println("<a href='/'>다시 로그인</a>");
    	}
    	
    	
//    	System.out.println("-----------------------Start Line------------------------");
//    	String method = request.getMethod();
//    	String uri = request.getRequestURI();
//    	String protocol = request.getProtocol();
//    	System.out.println(method+ " " + uri + " " + protocol);
//    	
//    	System.out.println("-----------------------Message Header--------------------");
//    	System.out.println("Host : " + request.getHeader("host"));
//    	System.out.println("Connection : " + request.getHeader("connection"));
//    	System.out.println("User-Agent : " + request.getHeader("user-agent"));
//    	System.out.println("Accept : " + request.getHeader("accept"));
//    	System.out.println("Accept-Encoding : " + request.getHeader("accept-encoding"));
//    	System.out.println("Accept-Language : " + request.getHeader("accept-language"));
    } 

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("---> GET 방식의 요청 처리");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---> POST 방식의 요청 처리");
	}
	
	public void destroy() {
		System.out.println("---> destroy() 호출");
	}

}
