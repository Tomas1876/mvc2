<%@page import="kr.or.bit.dto.Reply"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.bit.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_content</title>
<link rel="Stylesheet"
	href="${pageContext.request.contextPath}/style/default.css" />

</head>
<body>
	<c:set var="board" value="${requestScope.board}" />
	<c:set var="idx" value="${requestScope.idx }" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="replyList" value="${requestScope.replyList}" />
	<c:set var="uploadpath" value="${requestScope.uploadpath}"/>
	
	
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	
	<div id="pageContainer">
		<div style="padding-top: 30px; text-align: center">
			<center>
				<b>게시판 글내용</b>
				<table width="80%" border="1">
					<tr>
						<td width="20%" align="center"><b> 글번호 </b></td>
						<td width="30%">${idx}</td>
						<td width="20%" align="center"><b>작성일</b></td>
						<td>${board.writedate}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>글쓴이</b></td>
						<td width="30%">${board.writer}</td>
						<td width="20%" align="center"><b>조회수</b></td>
						<td>${board.readnum}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>홈페이지</b></td>
						<td>${board.homepage}</td>
						<td width="20%" align="center"><b>첨부파일</b></td>
						<!-- board.filename : 파일이름이 포함된 전체 실제경로 -->
						<!-- uploadpath는 upload폴더의 실제경로 -->
						<!-- substringAfter : board.filename에서 uploadpath 이후의 문자열만 잘라서 출력해줌 -->
						<!--  <td>${fn:substringAfter(board.filename,uploadpath)}</td>-->
					</tr>
					<tr>
						<td width="20%" align="center"><b>제목</b></td>
						<td colspan="3">${board.subject}</td>
					</tr>
					<tr height="100">
						<td width="20%" align="center"><b>글내용</b></td>
						<td colspan="3">${fn:replace(board.content, newLineChar,"<br>")}</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><a
							href="BoardList.do?cp=${cpage}&ps=${pagesize}">목록가기</a> |<a
							href="BoardEdit.do?idx=${idx}&cp=${cpage}&ps=${pagesize}">편집</a> |<a
							href="BoardDelete.do?idx=${idx}&cp=${cpage}&ps=${pagesize}">삭제</a>
							|<a
							href="BoardRewrite.do?idx=${idx}&cp=${cpage}&ps=${pagesize}&subject=${board.subject}">답글</a>
						</td>
					</tr>
				</table>
				<!--  꼬리글 달기 테이블 -->
				<form name="reply" method="POST">
						<!-- hidden 태그  값을 숨겨서 처리  -->
						<input type="hidden" name="idx" value="${idx}"> 
						<input type="hidden" name="userid" value=""><!-- 추후 필요에 따라  -->
						<!-- hidden data -->
						<table width="80%" border="1">
							<tr>
								<th colspan="2">덧글 쓰기</th>
							</tr>
							<tr>
								<td align="left">작성자 :
								 	<input type="text" name="reply_writer" id="reply_writer"><br /> 
								 	내&nbsp;&nbsp;용 : 
								 	<textarea name="reply_content" rows="2" cols="50" id="reply_content"></textarea>
								</td>
								<td align="left">
									비밀번호:
									<input type="password" name="reply_pwd" size="4" id="reply_pwd"> 
									<input type="button" value="등록" onclick="reply_check()">
								</td>
							</tr>
						</table>
				</form>
				<!-- 유효성 체크	 -->
				<script type="text/javascript">
					function reply_check() {
						var frm = document.reply;
						if (frm.reply_writer.value == "" || frm.reply_content.value == ""
							|| frm.reply_pwd.value == "") {
									alert("리플 내용, 작성자, 비밀번호를 모두 입력해야합니다.");
							return false;
						}

					ajadd();
					
					}					
					function reply_del(frm, no, idx) {

						if (frm.delPwd.value == "") {
							alert("비밀번호를 입력하세요");
							frm.delPwd.focus();
							return false;
						}
						
						//댓글이 여러 개일 때 선택한 댓글을 식별할 수 있도록 파라미터를 받는다
						ajdel(no, idx);
					}
				</script>
				<br>
				<!-- 꼬리글 목록 테이블 댓글영역 시작-->
				<div id="showreply">
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
				<!-- showreply div 즉 댓글 영역 끝 -->
				</div>
				
			</center>
		</div>
	</div>
</body>
<script type="text/javascript">
/*
댓글 등록, 등록 후 보여주기, 삭제
세 기능으로 나눠서 각각 비동기처리를 해줘야한다
원래는 댓글 등록서비스에서 보여주기 서비스로 넘어가고
보여주기 서비스는 글 정보 출력하는 서비스로 넘어간다
근데 글 정보 불러오는 서비스가 결국 board_content.jsp로 setPath를 해주기 때문에
조회수도 증가하고 새로고침이 일어난다
그러니까 댓글 불러오기는 비동기 처리한 함수로 빼서 등록 함수 밑에서 호출해야하는 것
결국 댓글은 등록, 등록된 댓글 보여주기, 삭제 이렇게 세 개의 서비스와 비동기 함수가 필요하다

*/
//댓글 비동기 등록
function ajadd(){
	let adddata = {
			idx : ${idx},
			reply_writer : $("#reply_writer").val(),
			reply_content : $("#reply_content").val(),
			reply_pwd : $("#reply_pwd").val()
	}
	console.log("등록함수 실행");
	console.log(${idx});
	$.ajax(
		{	
			url : "ReplyOk.ajax",
			data : adddata,
			dataType : "html",
				success : function(responsedata) {
				//console.log(responsedata);

					alert("댓글 입력 성공");
					//$('center').append(responsedata);
					
					reply_writer : $("#reply_writer").val("");
					reply_content : $("#reply_content").val("");
					reply_pwd : $("#reply_pwd").val("");
					ajlist();
				}
			
			
		}
	);

}
//댓글 비동기 불러오기
function ajlist(){
	console.log("불러오기 함수 실행");
	
	let replydata = {
			idx : ${idx},
	}		

	$.ajax(
		{
			url:"ReplyList.ajax",
			data:replydata,
			dataType:"json",
			success:function(responsedata){
				console.log(responsedata);
				$("#showreply").empty();
				$('#showreply').append('<tr align="left"><td width="80%">[' +
                        obj.writer +'] : ' +obj.content +
                        '<br> 작성일 :'+obj.writedate +'</td><td width="20%">' +
                        '<form method="POST" name="replyDel">' +
                        '<input type="hidden" name="no" value="' +obj.no +'" class="reply_no">' +
                        '<input type="hidden" name="idx" value="' +obj.idx_fk +'" class="reply_idx">' +
                        'password : <input type="password" name="delPwd" size="4" class="reply_pwd">' +
                        ' <input type="button" value="삭제" onclick="reply_del(this.form)">' +
                        '</form></td></tr>');
	

		},
			error:function(xhr){
				console.log(xhr);	
			}
		}
	);
	
}

//댓글 비동기 삭제
function ajdel(getNo, idx){
	
	 console.log(getNo);
	
	let params = {
			//댓글 번호
			no : $("#no"+getNo).val(),
			//글 번호
			idx : $("."+idx).val(),
			//패스워드 식별
			delPwd : $("#delPwd"+getNo).val()
			
	}	
	
	console.log($("#no"+getNo).val());
	console.log($("#"+idx).val());
	console.log($("#delPwd"+getNo).val());

	$.ajax(
	 {
		url:"ReplyDeleteOk.ajax",
		data:params,
		dataType:"text",

		success:function(responsedata){						
			
			console.log(responsedata);
			console.log( $("#delPwd"+getNo).val());
			
			
			let check = responsedata.trim();
			
			 if(check == "true"){
				  alert("삭제 성공");
				  let el = document.getElementById('comment'+params.no);
				  el.remove();							   
				   
			 }else{
				   alert("삭제 실패");
				   $("#delPwd"+getNo).val("");
			 }					 
			 
		},
		error:function(error){
			console.log(error);
		}
	 }
  );

}

</script>

</html>





