package kr.or.bit.ajaxcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ajax.ReplyDeleteOk;
import kr.or.bit.ajax.ReplyListService;
import kr.or.bit.ajax.ReplyWriteService;
import kr.or.bit.service.BoardAddService;
import kr.or.bit.service.BoardContentService;
import kr.or.bit.service.BoardDeleteOk;
import kr.or.bit.service.BoardDeleteService;
import kr.or.bit.service.BoardEditOk;
import kr.or.bit.service.BoardEditService;
import kr.or.bit.service.BoardListService;
import kr.or.bit.service.BoardRewriteOk;
import kr.or.bit.service.BoardRewriteService;

@WebServlet("*.ajax")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();

    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	System.out.println("url_Command : " + url_Command);
    	Action action=null;
    	ActionForward forward = null;
    	
    	
    	if(url_Command.equals("/ReplyList.ajax")) {
    		action = new ReplyListService();
    		forward = action.execute(request, response);
    		System.out.println("댓글 등록");
    		
    	} else if(url_Command.equals("/ReplyOk.ajax")) {

    		action = new ReplyWriteService();
    		forward = action.execute(request, response);
    		System.out.println("댓글 작성");
    		
    	} else if(url_Command.equals("/ReplyDeleteOk.ajax")) { //만약 있다면 상세보기
    		//UI 제공 ...
    		//예) /WEB-INF/views/memoview.jsp 가정
    		action = new ReplyDeleteOk();
    		forward = action.execute(request, response);
    		System.out.println("댓글 삭제 컨트롤러 진입");
    	}   	
    	   	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
