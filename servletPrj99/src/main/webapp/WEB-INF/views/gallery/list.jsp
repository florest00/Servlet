<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>GALLERY</title>
        <link rel="stylesheet" href="/static/css/common.css">
        <link rel="stylesheet" href="/static/css/gallery.css">
    </head>

    <body>

        <%@ include file="/WEB-INF/views/common/header.jsp" %>

            <main>
                <h1 class="content-h1">갤러리</h1>

                <form action="/gallery/list" method="get">

                    <select id="sortSelect">
                        <option value="">전체보기</option>
                        <option value="viewDesc">조회순</option>
                        <option value="titleAsc">이름순</option>
                        <option value="dateDesc">최신순</option>
                        <option value="dateAsc">오래된순</option>
                    </select>

                    <input type="text" name="keyword" placeholder="검색어를 입력하세요"
                        value="${keyword != null ? keyword : ''}">

                    <select name="searchType">
                        <option value="all" <c:if test="${searchType == null || searchType == 'all'}">selected</c:if>>전체
                        </option>
                        <option value="title" <c:if test="${searchType == 'title'}">selected</c:if>>제목</option>
                        <option value="content" <c:if test="${searchType == 'content'}">selected</c:if>>내용</option>
                        <option value="writerNo" <c:if test="${searchType == 'writerNo'}">selected</c:if>>작성자번호</option>
                        <option value="no" <c:if test="${searchType == 'no'}">selected</c:if>>번호</option>
                    </select>

                    <button type="submit">검색</button>

                </form>

                <div id="table-area">
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>내용</th>
                                <th>작성자 번호</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody id="boardTbody">
                            <c:choose>
                                <c:when test="${empty boardList}">
                                    <tr>
                                        <td colspan="4" style="text-align:center;">게시글이 없습니다.</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="vo" items="${boardList}">
                                        <tr>
                                            <td>${vo.no}</td>
                                            <td>${vo.title}</td>
                                            <td>${vo.content}</td>
                                            <td>${vo.writerNo}</td>
                                            <td>${vo.hit}</td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>

                <!-- 갤러리(사진첩) 형식보기 -->
                <div>
                    <ul id="gallery-area">
                        <li>
                            <a>
                                <!--  <img src="http://placehold.it/360x150" alt="Dummy Image"> -->
                                <div id="fake-img"></div>
                                <div class="img-info">
                                    <span>제목</span>
                                    <p>내용</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a>
                                <div id="fake-img"></div>
                                <div class="img-info">
                                    <span>제목</span>
                                    <p>내용</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a>
                                <div id="fake-img"></div>
                                <div class="img-info">
                                    <span>제목</span>
                                    <p>내용</p>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a>
                                <!--  <img src="http://placehold.it/360x150" alt="Dummy Image"> -->
                                <div id="fake-img"></div>
                                <div class="img-info">
                                    <span>제목</span>
                                    <p>내용</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a>
                                <div id="fake-img"></div>
                                <div class="img-info">
                                    <span>제목</span>
                                    <p>내용</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a>
                                <div id="fake-img"></div>
                                <div class="img-info">
                                    <span>제목</span>
                                    <p>내용</p>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>

                <!-- 리스트 형식보기, 갤러리(사진첩) 형식보기 필터추가 -->

                <div id="pagination">
                    <c:if test="${totalPage > 1}">
                        <!-- 맨앞(<<) -->
                        <a href="/board/list?page=1&sort=${sort}&keyword=${keyword}&searchType=${searchType}"
                            class="${currentPage == 1 ? 'disabled' : ''}">&lt;&lt;</a>

                        <!-- 이전(<) -->
                        <a href="/board/list?page=${currentPage > 1 ? currentPage - 1 : 1}&sort=${sort}&keyword=${keyword}&searchType=${searchType}"
                            class="${currentPage == 1 ? 'disabled' : ''}">&lt;</a>

                        <!-- 숫자 페이지 -->
                        <c:forEach var="i" begin="1" end="${totalPage}">
                            <a href="/board/list?page=${i}&sort=${sort}&keyword=${keyword}&searchType=${searchType}"
                                class="${i == currentPage ? 'active' : ''}">${i}</a>
                        </c:forEach>

                        <!-- 다음(>) -->
                        <a href="/board/list?page=${currentPage < totalPage ? currentPage + 1 : totalPage}&sort=${sort}&keyword=${keyword}&searchType=${searchType}"
                            class="${currentPage == totalPage ? 'disabled' : ''}">&gt;</a>

                        <!-- 맨뒤(>>) -->
                        <a href="/board/list?page=${totalPage}&sort=${sort}&keyword=${keyword}&searchType=${searchType}"
                            class="${currentPage == totalPage ? 'disabled' : ''}">&gt;&gt;</a>
                    </c:if>
                </div>

                <div class="button-area">
                    <button type="button" onclick="location.href='/board/insert'">글 작성</button>
                </div>

            </main>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </body>

    </html>