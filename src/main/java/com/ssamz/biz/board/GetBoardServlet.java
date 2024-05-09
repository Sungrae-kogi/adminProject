package com.ssamz.biz.board;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetBoardServlet
 */
@WebServlet("/getBoard.do")
public class GetBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 상태 정보 체크
		Cookie[] cookieList = request.getCookies();
		if(cookieList == null) {
			response.sendRedirect("/login.html");
		}else {
			String userId = null;
			for(Cookie cookie : cookieList) {
				if(cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
			}
			if(userId == null) {
				response.sendRedirect("/login.html");
			}
		}
		
		//1. 사용자 입력 정보 추출
		String seq = request.getParameter("seq");
		
		//2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);			//일려번호를 부여한 BoardVO 객체를 인자로 그에 맞는 BoardVO 객체를 DB에서 가져옴
		
		//3. 응답 화면 구성(검색 결과 화면 출력)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>글 상세</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>글 상세</h1>");
		out.println("<a href='logout.do'>Log-out</a></h3>");
		out.println("<hr>");
		out.println("<form action='updateBoard.do' method='post'>");
		out.println("<input name='seq' type='hidden' value='" + board.getSeq() + "'/>");
		out.println("<table border='1' cellpadding='0' cellspacing='0'>");
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='70'>제목</td>");
		out.println("<td align='left'><input name='title' type='text' value='" + board.getTitle() + "'/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>작성자</td>");
		out.println("<td align='left'>" + board.getWriter() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>내용</td>");
		out.println("<td align='left'><textarea name='content' cols='40' rows='10'>" + board.getContent() + "</textarea></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td bgcolor='orange'>등록일</td>");
		out.println("<td align='left'>" + board.getRegDate() + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println("<input type='submit' value='글 수정'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<hr>");
		out.println("<a href='insertBoard.html'>글 등록</a>&nbsp;&nbsp;&nbsp;");
		out.println("<a href='deleteBoard.do?seq=" + board.getSeq() + "'>글삭제</a>&nbsp;&nbsp;&nbsp;");
		out.println("<a href='getBoardList.do'>글 목록</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
		
	}

    
    
}
