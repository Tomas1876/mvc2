package kr.or.bit.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Reply;

public class ReplyListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		ActionForward forward =  new ActionForward();
		try {

			BoardDao dao = new BoardDao();
			List<Reply> replylist = dao.replylist(idx);
			
			request.setAttribute("replyList", replylist);
			request.setAttribute("idx", idx);
			

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/reply.jsp");

			
		} catch (Exception e) {

		} finally {

		}
		return forward;
	}

}