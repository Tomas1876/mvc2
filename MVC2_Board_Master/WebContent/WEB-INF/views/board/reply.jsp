<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<!-- 비동기로 댓글 등록해서 insert했으니 새로 테이블을 불러와야 한다
			테이블 생성해서 board_conten로 보낼 것 -->
			<!-- 꼬리글 목록 테이블 -->
			<c:set var="replyList" value="${requestScope.replyList}" />
				<c:if test="${not empty replyList}">
					<c:forEach var="reply" items="${replyList}">

						<table width="80%" border="1" id="comment${reply.no}">
							<tr >
								<th colspan="2">REPLY LIST</th>
							</tr>
							<tr align="left">
								<td width="80%">
								[${reply.writer}] : ${reply.content}
								<br> 작성일:${reply.writedate}
								</td>
								<td width="20%">
								<form name="replyDel">
									<input type="hidden" name="no" value="${reply.no}" id="no${reply.no}"> 
									<input type="hidden" name="idx" value="${idx}" class="${idx}"> 
									password :<input type="password" name="delPwd" size="4" id="delPwd${reply.no}"> 
									<input type="button" value="삭제" onclick="reply_del(this.form, ${reply.no}, ${idx})" id="deletebtn${reply.no}">
								</form>
								</td>
							</tr>
						</table>
					</c:forEach>
				</c:if>
				
</body>
</html>