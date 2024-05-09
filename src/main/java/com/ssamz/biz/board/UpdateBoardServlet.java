package com.ssamz.biz.board;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UpdateBoardServlet
 */
@WebServlet("/updateBoard.do")
public class UpdateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String encoding;
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * UpdateBoardServlet은 브라우저로부터 "updateBoard.do" 요청에 대해 동작하도록 설정,
	 * 세 개의 파라미터를 request에서 추출한 뒤 값을 이용하여 Board 테이블의 특정 게시글을 수정한다, 이 과정에서 게시물 선정은 기본키인 seq을 이용하여 판별.
	 * 일련번호 seq의 처리는 Integer를 이용하여 정수로 변환하는과정이 필요하다.
	 * Update 완료 후 /getBoardList.do로 리다이렉트
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//인코딩 설정
		ServletContext context = getServletContext();
		this.encoding = context.getInitParameter("boardEncoding");
		
		//1. 사용자 입력 정보 추출
		request.setCharacterEncoding(encoding);
		String title = request.getParameter("title");
		String seq = request.getParameter("seq");
		String content = request.getParameter("content");
		
		//2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setSeq(Integer.parseInt(seq));		
		vo.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.updateBoard(vo);
		
		//3. 화면 이동
		response.sendRedirect("getBoardList.do");
		
	}

}
