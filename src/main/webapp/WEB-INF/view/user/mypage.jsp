<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
</head>
<body>
	<%@include file="/WEB-INF/view/include/header.jsp"%>
	<h1>마이페이지</h1>
	
	<div>
	    <h3>사용자 정보</h3>
	    <p><strong>로그인 ID:</strong> ${userInfo.username}</p>
	    <p><strong>실제 이름:</strong> ${userInfo.realname}</p>
	    <p><strong>반:</strong> ${userInfo.classname}</p>
	    <p><strong>닉네임:</strong> ${userInfo.nickname}</p>
	    <p><strong>포인트:</strong> ${userInfo.userPoint}</p>
	</div>
	<div>
	    <h3>비밀번호 변경</h3>
	    <form action="/changePassword" method="POST">
	        <div>
	            <label for="currentPassword">현재 비밀번호:</label>
	            <input type="password" id="currentPassword" name="currentPassword" required>
	        </div>
	        <div>
	            <label for="newPassword">새 비밀번호:</label>
	            <input type="password" id="newPassword" name="newPassword" required>
	        </div>
	        <div>
	            <label for="confirmNewPassword">새 비밀번호 확인:</label>
	            <input type="password" id="confirmNewPassword" name="confirmNewPassword" required>
	        </div>
	        <div>
	            <button type="submit">비밀번호 변경</button>
	        </div>
	    </form>
	</div>
</body>
</html>