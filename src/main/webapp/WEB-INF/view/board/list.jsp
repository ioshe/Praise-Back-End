<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글목록</title>
    <link rel="stylesheet" type="text/css" href="/css/list.css"/>
</head>
<body>
	<%@include file="/WEB-INF/view/include/header.jsp"%>
	<h1>글목록</h1>
	<%-- Html, EL 태그는 주석 처리가 다름 --%>
	<%--  	${pageInfo.content }  	--%>


	<table>
        <tr>
            <th>순번</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>등록일</th>
            <th>좋아요수</th>
            <th>조회수</th>
        </tr>
		<c:forEach items="${pageInfo.content }" var="board">
			<tr>
				<td><a href="/board/detail?boardId=${board.boardId}">${board.boardId }</a></td>
				<td><a href="/board/detail?boardId=${board.boardId}">${board.title}</a></td>
				<c:choose>
				    <c:when test="${board.anonymous == false}">
				        <td>${board.sender.realname }</td>
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
    <form class="list-form" action="/board/list" method="get">
        <input type="text" name="page" value="${pageInfo.number +1 }">/${pageInfo.totalPages }
        <input type="submit" value="이동">
    </form>
	
</body>
</html>