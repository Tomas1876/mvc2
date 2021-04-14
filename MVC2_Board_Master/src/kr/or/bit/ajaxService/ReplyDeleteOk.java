package kr.or.bit.ajaxService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class ReplyDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idx_fk = request.getParameter("idx");// 댓글의 원본 게시글 번호
		String no = request.getParameter("no");// 댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");// 댓글의 암호
		
		String msg = "";
		
		PrintWriter out = null;

			try {
				
				out = response.getWriter();
				BoardDao dao = new BoardDao();

				int result = dao.replyDelete(no, pwd);
				
				if (result > 0) {
					msg = "true";

				} else {
					msg = "false";

				}

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		//request.setAttribute("board_msg", msg);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		out.print(msg);
		return forward;
	}

}

