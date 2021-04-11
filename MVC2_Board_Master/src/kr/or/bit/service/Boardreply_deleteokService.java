package kr.or.bit.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class Boardreply_deleteokService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		try {

			String idx_fk=request.getParameter("idx");//댓글의 원본 게시글 번호
			String no = request.getParameter("no");//댓글의 순번(PK)
			String pwd = request.getParameter("delPwd");//댓글의 암호
			System.out.println(idx_fk + "/" + no + "/" + pwd + "/");
			if(idx_fk == null || no == null || pwd == null || no.trim().equals("")){
			
				return forward; //더 이상 코드 실행하지 않아요
			}

			//parameter 정상인 경우
			BoardService service = BoardService.getInBoardService();
			Integer result =service.replyDelete(no, pwd);
			//처리하는 코드
			 	String msg="";
			    String url="";
			    String check="false";
			    
			    if(result > 0){			    
			    	msg ="댓글 삭제 성공";
			    	url ="/WEB-INF/Board/board_content.jsp?idx="+idx_fk;
			    	check = "true";
					
			    }else{
			    	msg="댓글 삭제 실패";
			    	url="/WEB-INF/Board/board_content.jsp?idx="+idx_fk;
			    }
			    
			    request.setAttribute("board_msg",msg);
			    request.setAttribute("board_url", url);
			    request.setAttribute("message", check);
			    			    
/*
				if(msg != null && url != null){
					  out.print("<script>");
					  out.print("alert('"+msg+"');");	
					  out.print("location.href='"+url+"';");
					  out.print("</script>");				  	
				 }
	*/		

		    	forward = new ActionForward();
			    forward.setRedirect(false);
			    forward.setPath(url);
			    
			    
			    
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return forward;
	}

}
