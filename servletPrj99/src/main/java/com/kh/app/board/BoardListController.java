package com.kh.app.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            BoardService boardService = new BoardService();

            // 1. 파라미터 수집
            int currentPage = 1;
            String pageParam = req.getParameter("page");
            if (pageParam != null && !pageParam.isBlank()) {
                currentPage = Integer.parseInt(pageParam);
            }

            String keyword = req.getParameter("keyword");
            String searchType = req.getParameter("searchType");
            String sort = req.getParameter("sort");

            int rowPerPage = 10;
            int startRow = (currentPage - 1) * rowPerPage + 1;
            int endRow = currentPage * rowPerPage;

            BoardVo searchVo = null;
            if (keyword != null && !keyword.trim().isEmpty() && searchType != null && !searchType.equals("all")) {
                searchVo = new BoardVo();
                switch (searchType) {
                    case "title":
                        searchVo.setTitle(keyword);
                        break;
                    case "content":
                        searchVo.setContent(keyword);
                        break;
                    case "writerNo":
                        // 숫자 변환 제거, 바로 String 세팅
                        searchVo.setWriterNo(keyword);
                        break;
                    case "no":
                        // 숫자 변환 제거, 바로 String 세팅
                        searchVo.setNo(keyword);
                        break;
                }
            }

            List<BoardVo> boardList = boardService.selectList(searchVo, startRow, endRow, sort);
            int totalCount = boardService.getTotalCount(searchVo);

            // Ajax 요청 여부 확인
            String requestedWith = req.getHeader("X-Requested-With");
            boolean isAjax = "XMLHttpRequest".equals(requestedWith);

            if (isAjax) {
                // Ajax 요청이면 JSON 응답
                resp.setContentType("application/json; charset=UTF-8");
                new Gson().toJson(boardList, resp.getWriter());
                return;
            }

            // JSP forward 처리
            int totalPage = (int) Math.ceil((double) totalCount / rowPerPage);
            int pageLimit = 5;
            int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
            int endPage = startPage + pageLimit - 1;
            if (endPage > totalPage) endPage = totalPage;

            req.setAttribute("boardList", boardList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("startPage", startPage);
            req.setAttribute("endPage", endPage);
            req.setAttribute("totalPage", totalPage);
            req.setAttribute("keyword", keyword);
            req.setAttribute("searchType", searchType);
            req.setAttribute("sort", sort);

            req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "게시글 목록 조회 중 에러 발생: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
        }
    }
}
