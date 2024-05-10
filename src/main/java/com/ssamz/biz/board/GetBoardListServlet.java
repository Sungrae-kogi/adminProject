package com.ssamz.biz.board;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class GetBoardListServlet
 */
@WebServlet("/getBoardList.do")
public class GetBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
		
		//0. 상태 정보 체크 
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");		//LoginServlet에서 setAttribute(name,value)로 값을 설정했었다.
		
		if(userId == null) {
			response.sendRedirect("/login.html");
		}
		
		/*
		 * Session에 데이터가 등록될 때 Object 타입으로 변환되어 저장되기 때문에 기본형과 참조형을 구분하지 않고 모든 데이터 타입을 저장할 수 있다.
		 * getAttribute()로 세션에 저장된 데이터를 추출할 때는 value에 해당하는 데이터가 Object 타입으로 리턴되기 때문에 반드시 명시적 타입 변환을 해야 한다. 위의 경우에는 "userId" name의 value값 String타입이 필요하므로
		 * (String) 으로 명시적 타입 변환을 진행했다.
		 */
		
		
		
		
		//1. 사용자 입력 정보 추출
		ServletContext context = request.getServletContext();
		String encoding = context.getInitParameter("boardEncoding");
		request.setCharacterEncoding(encoding);
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		
		//Null Check	- 사용자가 <검색> 버튼을 누르지 않고 글 목록 화면을 직접 요청하는 상황을 고려
		if(searchCondition == null) searchCondition = "TITLE";
		if(searchKeyword == null) searchKeyword = "";
		
		//세션에 검색 관련 정보를 저장한다.
		session.setAttribute("condition", searchCondition);
		session.setAttribute("keyword", searchKeyword);
		
		//2. DB 연동 처리	- 사용자 입력 검색정보 전달
		BoardVO vo = new BoardVO();
		vo.setSearchCondition(searchCondition);
		vo.setSearchKeyword(searchKeyword);
		
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//3. 응답 화면 구성
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>글 목록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>게시글 목록</h1>");
		String userName = (String) session.getAttribute("userName");
		out.println("<h3>" + userName + "님 로그인 환영합니다.....");
		out.println("<a href='logout.do'>Log-out</a></h3>");
		out.println("<!-- 검색 시작 -->");
		out.println("<form action='getBoardList.do' method='post'>");
		out.println("<table border='1 cellpadding='0' cellspacing='0' width='700'");
		out.println("<tr>");
		out.println("<td align='right'>");
		out.println("<select name='searchCondition'>");
		String condition = (String) session.getAttribute("condition");
		if(condition.equals("TITLE")) {
			out.println("<option value='TITLE' selected>제목");
		}else {
			out.println("<option value='TITLE'>제목");
		}
		if(condition.equals("CONTENT")) {
			out.println("<option value='CONTENT' selected>내용");
		}else {
			out.println("<option value='CONTENT'>내용");
		}
		out.println("</select>");
		out.println("<input name='searchKeyword' type='text' value='" + session.getAttribute("keyword") + "'/>");
		out.println("<input type='submit' value='검색'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<!-- 검색 종료 -->");
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
		out.println("<tr>");
		out.println("<th bgcolor='orange' width='100'>번호</th>");
		out.println("<th bgcolor='orange' width='200'>제목</th>");
		out.println("<th bgcolor='orange' width='150'>작성자</th>");
		out.println("<th bgcolor='orange' width='150'>등록일</th>");
		out.println("<th bgcolor='orange' width='100'>조회수</th>");
		out.println("</tr>");
		
		
		for(BoardVO board : boardList) {
			out.println("<tr>");
			out.println("<td>" + board.getSeq() + "</td>");
			out.println("<td align='left'><a href='getBoard.do?seq=" + board.getSeq() +"'>" + board.getTitle() + "</a></td>");
			out.println("<td>" + board.getWriter() + "</td>");
			out.println("<td>" + board.getRegDate() + "</td>");
			out.println("<td>" + board.getCnt() + "</td>");
			out.println("</tr>");
		}
		
		out.println("</table>");
		out.println("<br>");
		out.println("<a href='insertBoard.html'>새글 등록</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
