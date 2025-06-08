<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>게시글 작성</title>
        <link rel="stylesheet" href="/static/css/common.css">
    </head>

    <body>

        <%@ include file="/WEB-INF/views/common/header.jsp" %>

            <main>
                <h1 class="content-h1">게시글 작성하기</h1>

                <!-- 게시글 작성 폼 -->
                <form action="/board/insert" method="post">
                    <input type="text" name="title" placeholder="제목" required>
                    <br>
                    <textarea name="content" placeholder="내용" required></textarea>
                    <br>
                    <input type="submit" value="게시글 작성">
                </form>

                <hr>

                <!-- 게시글 조회 폼 (정렬, 검색 기능 포함) -->
                <h2>게시글 조회하기</h2>
                <form action="/board/list" method="get">
                    <!-- 검색 영역 -->
                    <select name="searchType">
                        <option value="all">전체</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writerNo">작성자</option>
                        <option value="no">번호</option>
                    </select>
                    <input type="text" name="keyword" placeholder="검색어 입력">

                    <!-- 정렬 영역 -->
                    <select name="sort">
                        <option value="dateDesc">최신순</option>
                        <option value="titleAsc">제목 오름차순</option>
                        <option value="titleDesc">제목 내림차순</option>
                        <option value="viewDesc">조회수 높은순</option>
                    </select>

                    <!-- 조회 버튼 -->
                    <button type="submit">조회하기</button>
                </form>

            </main>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </body>

    </html>