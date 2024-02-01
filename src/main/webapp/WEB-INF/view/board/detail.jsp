<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	 .button-group {
        display: flex;
        justify-content: space-between;
        align-items: center; /* 버튼들이 중앙에 위치하도록 조정 */
    }
    .right-buttons {
            text-align: right; /* 오른쪽 정렬 */
            width: 100%; /* 전체 너비 사용 */
        }
</style>
<script>
	function toggleLike(boardId) {
	    console.log("Board ID: ", boardId); // 여기에서 boardId 값을 확인
	    fetch("/board/like?boardId=${boardId}")
	        .then(response => response.json())
	        .then(data => {
	            document.getElementById('likeCount').textContent = data.likeCount;
	        })
	        .catch(error => console.error('Error:', error));
	}
</script>
</head>
<body>
		<%@include file="/WEB-INF/view/include/header.jsp"%>
		<h1>게시글 상세</h1>							<!-- 파일 수정을 위해 enctype으로 지정 -->	
		<form method="post" action="/board/update">
			<input type="hidden" name="user_Id" value="${board.receiver.id }" >
			<input type="text" name="boardId"value="${board.boardId }"readonly="readonly"><br>
			<input type="text" value="${board.receiver.realname }" disabled = "disabled"><br> 
			<input type="text" name="title" value="${board.title }"><br>
			<textarea rows="10" cols="100" name="content">${board.content }</textarea><br>
		</form>		
	    <div class="button-group">
		    <div class="left-buttons">
		        <!-- 좋아요 버튼: 로그인된 사용자만 표시 -->
		        <button id="likeButton" onclick="toggleLike(${board.boardId})">좋아요</button>
		        <a href="/board/like?boardId=${board.boardId}">눌러보셈</a>
		        ${board.boardId}
		        <c:if test="${!empty loginUser}">
		            <button id="likeButton" value="${board.boardId}" onclick="/board/like?boardId=${board.boardId}">좋아요</button>
		        </c:if>
		        <label id="likeLabel">좋아요 수</label>
	            <label id="likeCount">0</label>
		    </div>
		    <div class="right-buttons">
		        <!-- 삭제 버튼: 게시글 작성자만 표시 -->
		        <c:if test="${!empty loginUser && loginUser.id == board.sender.id }">
		        	<input type="button" value="삭제" onclick="location.href='/board/delete?boardId=${board.boardId}'">
		        </c:if>
		        <input type="button" value="목록" onclick="location.href='/board/list'">
		    </div>
		</div>

</body>
</html>