package kr.or.bit.ajaxService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

public class ReplyWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		// board_content에서 전송한 데이터들
		String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		String idx = request.getParameter("idx");
		// 비회원 게시판이라 empty
		String userid = "empty";
		

		
		ActionForward forward = null;
		try {
			BoardDao dao = new BoardDao();
			//reply 테이블 insert
			int result = dao.replywrite(Integer.parseInt(idx), writer, userid, content, pwd);


			if (result > 0) {
			request.setAttribute("idx", idx);
		
			forward  = new ActionForward();
			forward.setRedirect(false);

			//이거 지정 안 해도 이미 리스트 서비스에서는 idx번호를 받는다 그렇다고 setPath를 지정하지 않으면 에러 발생
			//아래 두 경로 중 어느 것으로 지정해도 결과가 동일하게 실행된다
			//forward.setPath("ReplyList.do?idx = "+idx);
			//forward.setPath("/WEB-INF/views/board/board_content.jsp");
			

			}	
		} catch (Exception e) {

		} finally {

		}
		return forward;
	}

}