package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_writeokService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String subject = request.getParameter("subject");
		String writer = request.getParameter("subject");
		String email = request.getParameter("subject");
		String homepage = request.getParameter("subject");
		String content = request.getParameter("subject");
		String pwd = request.getParameter("subject");
		String filename = request.getParameter("subject");
		
		Board dto = new Board();
		
		dto.setSubject(subject);
		dto.setWriter(writer);
		dto.setEmail(email);
		dto.setHomepage(homepage);
		dto.setContent(content);
		dto.setPwd(pwd);
		dto.setFilename(filename);
		
		BoardDao dao = new BoardDao();
		int result = dao.writeok(dto);
		
		
		 String msg="";
		    String url="";
		    if(result > 0){
		    	msg ="insert success";
		    	url ="Board_List.do";
		    }else{
		    	msg="insert fail";
		    	url="Board_Write.do";
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);
		 //뷰 지정
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/redirect.jsp");
		    
		
		return forward;
	}
	
}
