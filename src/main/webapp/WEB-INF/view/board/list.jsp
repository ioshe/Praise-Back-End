<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/view/include/header.jsp"%>
	<h1>글목록</h1>
	<%-- Html, EL 태그는 주석 처리가 다름 --%>
	<%--  	${pageInfo.content }  	--%>


	<table>
		<tr><td>no</td><td>제목</td><td>글쓴이</td><td>등록일</td><td>좋아요수</td><td>조회수</td></tr>
		<c:forEach items="${pageInfo.content }" var="board">
			<tr>
				<td><a href="/board/detail?boardId=${board.boardId}">${board.boardId }</a></td>
				<td>${board.title}</td>
				<c:choose>
				    <c:when test="${board.anonymous == false}">
				        <td>${board.receiver.realname }</td>
				    </c:when>
				    <c:otherwise>
				        <td>익명</td>
				    </c:otherwise>
				</c:choose>
				<td><fmt:formatDate value="${board.registDate}" pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${board.likes}</td>
				<td>${board.views}</td>
			</tr>
		</c:forEach>

	</table>
	<form action="/board/list" method="get">
		<input type="text" name="page" value="${pageInfo.number +1 }">/${pageInfo.totalPages }
		<input type="submit" value="이동">
	</form>
	
</body>
</html>