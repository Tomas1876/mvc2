package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_rewriteokService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String filename = request.getParameter("filename");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String cpage = request.getParameter("cp"); //current page
		String pagesize = request.getParameter("ps"); //pagesize
		
		Board dto = new Board();
		System.out.println(subject);
		System.out.println(writer);
		System.out.println(email);
		System.out.println(homepage);
		System.out.println(content);
		System.out.println(pwd);
		
		System.out.println(cpage);
		System.out.println(pagesize);
		
		dto.setIdx(idx);
		dto.setSubject(subject);
		dto.setWriter(writer);
		dto.setEmail(email);
		dto.setHomepage(homepage);
		dto.setContent(content);
		dto.setPwd(pwd);
		dto.setFilename(filename);
		
		BoardDao dao = new BoardDao();
		int result = dao.reWriteOk(dto);
		
		
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