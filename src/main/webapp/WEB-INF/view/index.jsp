<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Redirecting...</title>
    <script>
        window.location.href = "/board/list";
    </script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" />
	<p>If you are not redirected, <a href="/board/list">click here</a>.</p>
</body>
</html>