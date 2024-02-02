<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>칭찬 글 작성하기</title>
	<script>
    function showConfirmation() {
	        alert('게시글을 작성하여 포인트 50점이 적립되었습니다!');
	    }
	</script>
	<link rel="stylesheet" type="text/css" href="/css/registboard.css" />
</head>
<body>
	<%@include file="/WEB-INF/view/include/header.jsp"%>
    <h1>게시글 등록</h1>
    <form method="post" action="/board/regist" onsubmit="showConfirmation()">
        <label>작성자:<input type="text" value="${loginUser.realname}" readonly= "readonly" name = "user_Id"></label><br>
        <input type="hidden" id="senderId" name="senderId" value="${loginUser.id}" readonly= "readonly" >
        <label>칭찬받는 사람:</label>
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
