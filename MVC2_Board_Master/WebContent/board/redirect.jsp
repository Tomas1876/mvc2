<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  

<c:set var="msg" value="${requestScope.board_msg}" />
<c:set var="url" value="${requestScope.board_url}" />

<c:choose>
	<c:when test = "${null ne msg && null ne url}">
		<script>
		
		alert("${msg}");		
	    location.href="${pageContext.request.contextPath}"+"/"+"${url}";
	    //location.href="${url}";
	</script>
	</c:when>
</c:choose>
