package kr.or.bit.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class Board_editokService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		try {
			 
			String idx= request.getParameter("idx");
			
			  if(idx == null || idx.trim().equals("")){
				  out.print("<script>");
				  	out.print("alert('글번호 입력 오류')");
				  	out.print("location.href='board_list.jsp'");
				  out.print("</script>");
			  }
			  
			  BoardService service = BoardService.getInBoardService();
			  int result = service.board_Edit(request);
			  
				String msg="";
				String url="";
				
				if(result > 0){
					msg="edit success";
					url="Board_List.do";
				}else{
					msg="edit fail";
					url="board_edit.do?idx="+idx;
				}
				
	
			  if(msg != null && url != null){

				  out.print("<script>");
				  out.print("alert('"+msg+"');");	
				  out.print("location.href='"+url+"';");
				  out.print("</script>");				  	

			 }
			  
			  forward.setRedirect(false);
			  forward.setPath(url);
			 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}
}
