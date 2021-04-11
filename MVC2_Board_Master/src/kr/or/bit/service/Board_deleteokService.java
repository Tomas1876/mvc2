package kr.or.bit.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class Board_deleteokService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		try {
			 
			String idx = request.getParameter("idx");
			String pwd = request.getParameter("pwd");
			
			BoardService service = BoardService.getInBoardService();
			int result =service.board_Delete(idx, pwd);
			
			String msg="";
			String url="";
			if(result > 0){
				System.out.println("DELETE 성공");
				
				msg="delete success";
				url="Board_List.do";
			}else{
				System.out.println("DELETE 실패");
				
				msg="delete fail";
				url="Board_List.do";
			}
						
			
			request.setAttribute("board_msg",msg);
			request.setAttribute("board_url",url);
			System.out.println("msg : " + msg +" url : " + url);
			
			if(msg != null && url != null){

				  out.print("<script>");
				  out.print("alert('"+msg+"');");	
				  out.print("location.href='"+url+"';");
				  out.print("</script>");				  	

			 }
			
			  forward.setRedirect(true);
			  forward.setPath(url);
			 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}