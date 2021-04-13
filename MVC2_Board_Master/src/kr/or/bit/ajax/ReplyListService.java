package kr.or.bit.ajax;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Reply;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReplyListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx= request.getParameter("idx");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		ActionForward forward = null;
    	
		try {
			
			BoardDao dao = new BoardDao();
			List<Reply> replylist = dao.replylist(idx);
			
			//Json 객체 하나 = 댓글 하나
			//배열
			JSONArray jsonarr = new JSONArray();
			
			for(int i = 0; i < replylist.size(); i++) {
				
				String date = dateFormat.format(replylist.get(i).getWritedate());
				
				//json 객체 즉 댓글 테이블을
				JSONObject jsonObj = new JSONObject();
				
				//Json객체에 repllist의 요소들을 넣어준다
				jsonObj.put("no", replylist.get(i).getNo());
				jsonObj.put("writer", replylist.get(i).getWriter());
				jsonObj.put("userid", replylist.get(i).getUserid());
				jsonObj.put("pwd", replylist.get(i).getPwd());
				jsonObj.put("content", replylist.get(i).getContent());
				jsonObj.put("writedate", date);
				jsonObj.put("idx_fk", replylist.get(i).getIdx_fk());
				
				jsonarr.add(jsonObj);
			}
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jsonarr);

		
		}catch (Exception e) {

		} finally {

		}
		forward  = new ActionForward();
		forward.setRedirect(false);
		
		return forward;
	}
}

