<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>칭찬 글 작성하기</title>
</head>
<body>
	<%@include file="/WEB-INF/view/include/header.jsp"%>
    <h1>게시글 등록</h1>
    <form method="post" action="/board/regist">
        <label>작성자:</label>
        <select id="senderId" name="senderId" required>
            <c:forEach var="entry" items="${realnames}">
                <option value="${entry.key}">${entry.value}</option>
            </c:forEach>
        </select>
        <br>
        
        <label>이름2:</label>
        <select id="receiverId" name="receiverId" required>
            <c:forEach var="entry" items="${realnames}">
                <option value="${entry.key}">${entry.value}</option>
            </c:forEach>
        </select>
        <br>
        
		<label>글제목:<input type="text" name = "title"></label><br>
        <br>
        <textarea rows="10" cols="100" name="content"></textarea><br>
        
        <label>익명 여부:</label>
        <input type="radio" id="anonymous" name="anonymous" value="true">익명
        <input type="radio" id="anonymous" name="anonymous" value="false" checked>공개
        <br>
        
        <input type="submit" value="등록">
    </form>
</body>
</html>
