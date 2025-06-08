<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	
		<link rel="stylesheet" href="/static/css/common.css">
		
		<c:if test="${not empty alertMsg}">
			<script>
				alert('${alertMsg}');
			</script>
		</c:if>
		
		<%
			session.removeAttribute("alertMsg");
		%>
	</head>

	<body>

		<header>
			<div>
				<div>
					빈칸
				</div>
				<div>
					<a href="/home">모두의 커뮤니티</a>
				</div>
				<div>
					<div id="login-area">
						<c:if test="${empty loginMember}">
							<form action="/member/login" method="post">
								<input type="text" name="userId" placeholder="아이디">
								<input type="button" value="회원가입" onclick="location.href='/member/join'">
								<input type="password" name="userPwd" placeholder="비밀번호">
								<input type="submit" value="로그인">
							</form>
						</c:if>
						<c:if test="${not empty loginMember}">
							<h3>${loginMember.userNick}</h3>
							<a href="/member/logout">로그아웃</a>
						</c:if>
					</div>
				</div>
			</div>

			<nav>
				<a href="/board/list">게시글</a>
				<a href="/notice/list">공지사항</a>
				<a href="/gallery/list">갤러리</a>
				<a href="/member/mypage">마이페이지</a>
			</nav>
		</header>