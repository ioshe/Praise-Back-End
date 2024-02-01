<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <%@include file="/WEB-INF/view/include/header.jsp"%>
        <h1>회원 가입</h1>
        <!-- 사용자로 부터 정보를 받아야함 form 필요 -->
        <form action="/auth/signup" method="post">
                <input type="text" name="username" placeholder="사용자 ID">
                <c:if test="${!empty msg }">
                        ${msg }
                </c:if>
                <br>
                <input type="text" name="realname" placeholder="사용자 이름">
                <br>
                <input type="text" name="nickname" placeholder="닉네임">
                <br>
                <input type="text" name="classname" placeholder="우리FISA 반(클래스)">
                <br>                
                <input type="password" name="password" placeholder="사용자 비번">
                <br>
                <input type="submit" value="회원 가입">
                <!-- 이 값들이 서버로 날아오면 url이 유저의 조인이고 포스트인곳으로 날아간다 -->
        </form>
</body>
</html>
<!--  다음부턴 사용자의 정보를 받아서 회원가입 처리를 해줘야함 jon.jsp로 ㄱㄱ -->