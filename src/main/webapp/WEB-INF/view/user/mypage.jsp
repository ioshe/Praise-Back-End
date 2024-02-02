<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyPage</title>
    <style>
        .container {
            display: flex;
            justify-content: space-between;
        }
        .left-panel, .right-panel {
            flex: 1;
            margin: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <%@include file="/WEB-INF/view/include/header.jsp"%>
    <h1>마이페이지</h1>

    <div class="container">
        <div class="left-panel">
            <div>
                <h2>사용자 정보</h2>
                <p><strong>이름:</strong> ${userInfo.realname}</p>
                <p><strong>닉네임:</strong> ${userInfo.nickname}</p>
                <p><strong>과정명:</strong> ${userInfo.classname}</p>
                <p><strong>포인트:</strong> ${userInfo.userPoint}</p>
            </div>
            <div>
                <h2>비밀번호 변경</h2>
                <form action="/user/password" method="POST">
                    <div>
                        <label for="currentPassword">현재 비밀번호:</label>
                        <input type="password" id="currentPassword" name="curPassword" required>
                    </div>
                    <div>
                        <label for="newPassword">새 비밀번호:</label>
                        <input type="password" id="newPassword" name="newPassword" required>
                    </div>
                    <div>
                        <button type="submit">비밀번호 변경</button>
                    </div>
                </form>
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <% if (request.getAttribute("successMessage") != null) { %>
                    <p style="color:blue;"><%= request.getAttribute("successMessage") %></p>
                <% } %>
            </div>
           	<div>
           		<c:if test = "${empty loginUser }">
           			${signoutmsg }
           		</c:if>
		       	<form action="/auth/signout" method="post">
		       	<input type="submit" value = "회원탈퇴">
		       	</form>
                <% if (request.getAttribute("signoutmsg1") != null) { %>
                    <p style="color:blue;"><%= request.getAttribute("signoutmsg1") %></p>
                <% } %>
                <% if (request.getAttribute("signoutmsg2") != null) { %>
                    <p style="color:red;"><%= request.getAttribute("signoutmsg2") %></p>
                <% } %>	       	
		    </div>
        </div>

        <div class="right-panel">
            <h2>내가 쓴 글</h2>
            <table>
                <tr>
                    <th>제목</th>
                    <th>글작성자</th>
                    <th>언급된자</th>
                    <th>조회수</th>
                </tr>
                <c:forEach var="board" items="${boardPage.content}">
                    <tr onclick="location.href='/boards/${board.boardId}'" style="cursor:pointer;">
                        <td>${board.title}</td>
                        <td>${board.sender.username}</td>
                        <td>${board.receiver.username}</td>
                        <td>${board.views}</td>
                    </tr>
                </c:forEach>
            </table>
            <!-- 페이지네이션 컨트롤 -->
            <div class="pagination">
			    <%-- 이전 페이지 링크 --%>
			    <c:if test="${boardPage.hasPrevious()}">
			        <a href="?page=${boardPage.number - 1}">&laquo; 이전</a>
			    </c:if>
			    <%-- 페이지 번호 링크 --%>
			    <c:forEach begin="0" end="${boardPage.totalPages}" var="pageNum">
			        <c:if test="${boardPage.number == pageNum}">
			            <span><strong>${pageNum + 1}</strong></span>
			        </c:if>
			        <c:if test="${boardPage.number != pageNum}">
					<a href="?page=${pageNum}">${pageNum + 1}</a>
					</c:if>
					</c:forEach>
				<%-- 다음 페이지 링크 --%>
				<c:if test="${boardPage.hasNext()}">
				    <a href="?page=${boardPage.number + 1}">다음 &raquo;</a>
				</c:if>
			</div>
        </div>
    </div>
</body>
</html>
