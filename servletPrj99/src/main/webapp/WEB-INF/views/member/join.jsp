<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/static/css/common.css">
	</head>

	<body>
		<%@ include file="/WEB-INF/views/common/header.jsp" %>

			<main>
				<h1 class="content-h1">회원가입</h1>
				<form action="/member/join" method="post" id="join-area">
					<div class="inner">
						<input type="text" name="userId" placeholder="아이디">
						<input type="password" name="userPwd" placeholder="비밀번호">
						<input type="text" name="userNick" placeholder="닉네임">
						<input type="submit" value="회원가입">
					</div>
				</form>
			</main>

			<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</body>

	</html>