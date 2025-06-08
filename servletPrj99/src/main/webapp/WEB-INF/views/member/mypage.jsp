<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>MYPAGE</title>
        <link rel="stylesheet" href="/static/css/common.css">
        <link rel="stylesheet" href="/static/css/mypage.css">
    </head>

    <body>

        <%@ include file="/WEB-INF/views/common/header.jsp" %>

            <main>

                <h1 class="content-h1">마이페이지</h1>

                <div class="content-wrap">
                    <aside>
                        <div id="profile-card">

                            <div id="fake-img"></div>
                            <!-- <img src="" alt=""> -->

                            <div id="profile-info">
                                <span>이름</span>
                                <p>자기소개</p>
                            </div>
                        </div>

                        <div id="category">
                            <ul>
                                <li>
                                    <a href="">
                                        <div class="to-do-list">
                                            <h4>To-do-list</h4>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <div class="post-list">
                                            <h4>게시글 목록</h4>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <ul class="mini-game">
                                            <h4>미니게임</h4>
                                            <li>
                                                개발중
                                            </li>
                                        </ul>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </aside>
                    <section>
                        <p>개발중입니다~</p>
                        <div id="to-do-list">
                            <p>개발중입니다~</p>
                        </div>
                        <div id="post-list">
                            <p>개발중입니다~</p>
                        </div>
                        <div id="mini-game">
                            <p>개발중입니다~</p>
                        </div>
                    </section>
                </div>


            </main>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </body>

    </html>