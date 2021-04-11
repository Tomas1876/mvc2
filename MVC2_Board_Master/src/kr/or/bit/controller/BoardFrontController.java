package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.Board_deleteokService;
import kr.or.bit.service.Board_editokService;
import kr.or.bit.service.Board_rewriteokService;
import kr.or.bit.service.Board_writeokService;
import kr.or.bit.service.Boardreply_deleteokService;
import kr.or.bit.service.ReplyListService;
import kr.or.bit.service.ReplyWriteService;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardFrontController() {
		super();

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestURI.substring(contextPath.length());
		/*
		 * requestURI : /WebServlet_8_Member_Model2_Mvc_Url/Register.do contextPath :
		 * /WebServlet_8_Member_Model2_Mvc_Url
		 * 
		 * urlcommand : /Register.do
		 * 
		 */

		System.out.println("requestURI : " + requestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("urlcommand : " + urlcommand);

		// kr.or.bit.service : 서비스클래스
		Action action = null;
		ActionForward forward = null;

		try {

			if (urlcommand.equals("/board_edit.do")) { // 글 수정 페이지

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/Board/board_edit.jsp");

			} else if (urlcommand.equals("/board_editok.do")) { // 글 수정 서비스

				action = new Board_editokService();
				forward = action.execute(request, response);
				System.out.println("Board_editService 실행");

			} else if (urlcommand.equals("/board_delete.do")) { // 글 삭제 페이지

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/Board/board_delete.jsp");

			} else if(urlcommand.equals("/board_deleteok.do")) { // 글 삭제 서비스
	    		
	    		action = new Board_deleteokService();
	    		forward = action.execute(request, response);
	    		System.out.println("Board_deleteokService 실행");
	    	
	    	}else if(urlcommand.equals("/boardreply_deleteOk.do")) { // 댓글 삭제 서비스
	    		System.out.println(urlcommand);
	    		action = new Boardreply_deleteokService();
	    		forward = action.execute(request, response);
	    		System.out.println("Boardreply_deleteService 실행");
	    	
	    	} else if (urlcommand.equals("/Board_Write.do")) { // 글쓰기 페이지 로딩
				System.out.println("THIS is Board_Write.do");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/Board/board_write.jsp");

			} else if (urlcommand.equals("/Board_Writeok.do")) { // 글쓰기 서비스 실행
				System.out.println("THIS is Board_Writeok.do");
				forward = new ActionForward();
				action = new Board_writeokService();
				forward = action.execute(request, response);
			} 
			/*
			 * else if (urlcommand.equals("/Board_Replyok.do")) {
			 * System.out.println("THIS is Board_Replyok.do"); forward = new
			 * ActionForward(); action = new Board_replyokService(); forward =
			 * action.execute(request, response); }
			 */
			else if (urlcommand.equals("/Board_List.do")) { // 게시판 목록 페이지 로딩
				System.out.println("THIS is Board_List.do");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/Board/board_list.jsp");

			} else if (urlcommand.equals("/Board_Rewirte.do")) { // 게시판 답글 페이지 로딩
				System.out.println("THIS is Board_Rewrite.do");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/Board/board_rewrite.jsp");
				
			} else if (urlcommand.equals("/Board_Rewirteok.do")) { // 게시판 답글 서비스 실행
				System.out.println("THIS is Board_Rewriteok.do");
				forward = new ActionForward();
				action = new Board_rewriteokService();
				forward = action.execute(request, response);
				
			} else if(urlcommand.equals("/Board_Content.do")) {
				System.out.println("THIS is Board_Content.do");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/Board/board_content.jsp");
				
			}else if(urlcommand.equals("/ReplyList.do")) { // 댓글
	    		action = new ReplyListService();
	    		forward = action.execute(request, response);
	    		
	    	} else if(urlcommand.equals("/Board_Replyok.do")) { // 댓글 작성하기

	    		action = new ReplyWriteService();
	    		forward = action.execute(request, response);
	    		
	    	}else {
				System.out.println("This is Controller");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		// 여기서 이제 페이지렌더링
		if (forward != null) {
			if (forward.isRedirect()) {// true라면 페이지를 재요청
				response.sendRedirect(forward.getPath());
			} else {// false
					// 1. UI만 전달된 겨우
					// 2. UI + 로직
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
