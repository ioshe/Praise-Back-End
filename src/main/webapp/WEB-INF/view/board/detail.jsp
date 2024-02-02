<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/detail.css" />
</head>
<body>
		<%@include file="/WEB-INF/view/include/header.jsp"%>
		<h1>게시글 상세</h1>							<!-- 파일 수정을 위해 enctype으로 지정 -->	
		<form method="post" action="/board/update">
			<input type="hidden" name="user_Id" value="${board.receiver.id }" >
			보드번호 : <input type="text" name="boardId"value="${board.boardId }" readonly="readonly">번<br>
			받은사람 : <input type="text" value="${board.receiver.realname }" disabled = "disabled" ><br> 
			제목 : <input type="text" name="title" value="${board.title }" readonly="readonly"><br>
			<textarea rows="10" cols="100" name="content" readonly="readonly">${board.content }</textarea><br>
		</form>		
		
	    <div class="button-group">
	   		<div class="left-buttons">
		        <!-- 좋아요 버튼: 로그인된 사용자만 표시 -->
		        <c:if test="${!empty loginUser}">
		            <button id="likeButton" onclick="toggleLike(${board.boardId})">좋아요</button>
		        </c:if>
		        <div id="likeCountContainer">
		            <span id="likeLabel">좋아요 수: </span>
		            <span id="likeCount" style="white-space: nowrap;">${board.likes}</span>
		        </div>
		    </div>
		    <div class="right-buttons">
		        <!-- 삭제 버튼: 게시글 작성자만 표시 -->
		        <c:if test="${!empty loginUser && loginUser.id == board.sender.id }">
		            <input type="button" value="삭제" onclick="location.href='/board/delete?boardId=${board.boardId}'">
		        </c:if>
		        <input type="button" value="목록" onclick="location.href='/board/list'">
		    </div>
		</div>

		<script>
			function toggleLike(boardId) {
			    boardId = Number(boardId);
			    console.log("Board ID: ", boardId); // 여기에서 boardId 값을 확인

			    var senderId = "${board.sender.id}";
			    var currentUserId = "${loginUser.id}";
				console.log("Sender : ",senderId);
				console.log("currentUserId : ",currentUserId);
			    var requestBody = {
			        boardId: boardId,
			        senderId: senderId,
			        currentUserId: currentUserId
			    };
			    
			    fetch(`/board/like`, {
			        method: 'POST',
			        headers: {
			            'Content-Type': 'application/json'
			        },
			        body: JSON.stringify(requestBody)
			    })
			    .then(response => response.json())
			    .then(data => {
			        document.getElementById('likeCount').textContent = data.likeCount;
			    })
			    .catch(error => console.error('Error:', error));
			}
		</script>
</body>
</html>