package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

public class Board_replyokService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		int idx_fk = Integer.parseInt(request.getParameter("idx"));
		String userid = "empty";

		Reply dto = new Reply();
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setPwd(pwd);
		dto.setIdx_fk(idx_fk);
		dto.setUserid(userid);
		
		BoardDao dao = new BoardDao();
		int result = dao.replywrite(dto);
		
		String msg="";
	    String url="";
		
		 if(result > 0){
		    	msg ="댓글 입력 성공";
		    	url ="baord/board_content.jsp?idx="+idx_fk;
		    }else{
		    	msg="댓글 입력 실패";
		    	url="baord/board_content.jsp?idx="+idx_fk;
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
