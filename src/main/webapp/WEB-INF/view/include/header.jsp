	<%@page import="com.example.praise.model.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
	<h1>My Board </h1> 
	<!--  login & logout 처리 -->
	<div>
	<!-- 로그인 창 생성 -->
	  <c:if test = "${empty loginUser }">
	    <c:if test="${empty loginmsg }"></c:if>
	      ${loginmsg }
	    <form method = "post" action="/auth/login">
	      <input type = "text" name = "username"> 
	      <input type = "password" name = "password">
	      <input type = "submit" value="로그인">
	    </form>
	  
	 <!-- 회원가입 버튼 -->
	    <a href="/auth/signup"><button>회원가입</button></a>
	
	  </c:if>
	  
	<!-- 로그아웃 창 생성 -->
	  <c:if test="${!empty loginUser }">
	  	${loginUser.nickname }님 환영합니다. <br>
	    <!-- 로그아웃 넣을 예정 -->
	    <a href= "/auth/logout" >로그아웃</a>
	  	<a href="/board/regist">[게시글 등록]</a> 
	  	<!-- My page 창 생성 -->
	  	<a href= "/user/mypage" >[마이페이지]</a>
	  </c:if>
	  <a href="/board/list">[게시글 목록]</a>
	</div>
	<hr>