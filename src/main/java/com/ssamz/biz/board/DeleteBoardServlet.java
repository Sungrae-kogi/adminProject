package com.ssamz.biz.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class DeleteBoardServlet
 */
@WebServlet("/deleteBoard.do")
public class DeleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 상태 정보 체크
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if(userId == null) {
			response.sendRedirect("/login.html");
		}
		
		
//		Cookie[] cookieList = request.getCookies();
//		if(cookieList == null) {
//			response.sendRedirect("/login.html");
//		}else {
//			String userId = null;
//			
//			for(Cookie cookie : cookieList) {
//				if(cookie.getName().equals("userId")) {
//					userId = cookie.getValue();
//				}
//			}
//			if(userId ==null) {
//				response.sendRedirect("/login.html");
//			}
//		}
		
		
		//1. 사용자 입력 정보 추출
		String seq = request.getParameter("seq");
		
		//2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));		//VO타입 객체를 만들어서 가져온 seq(기본키) 값을 할당해주고 DAO에 해당 객체를 전달해주어 delete처리
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);
		
		//3.화면 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
		dispatcher.forward(request, response);
		
	}
}
