package com.kh.app.board;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;

@WebServlet("/board/listData")
public class BoardListDataController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // 1. 파라미터 수집
            int currentPage = 1;
            String pageParam = req.getParameter("page");
            if (pageParam != null && !pageParam.isBlank()) {
                currentPage = Integer.parseInt(pageParam);
            }

            String sort = req.getParameter("sort");

            // 2. 페이징 계산
            int rowPerPage = 5;
            int startRow = (currentPage - 1) * rowPerPage + 1;
            int endRow = currentPage * rowPerPage;

            // 3. 서비스 호출
            BoardService service = new BoardService();
            List<BoardVo> list = service.selectList(null, startRow, endRow, sort);
            int totalCount = service.getTotalCount(null);
            int totalPage = (int) Math.ceil((double) totalCount / rowPerPage);

            // 4. 결과 포장 및 응답
            Map<String, Object> result = new HashMap<>();
            result.put("list", list);
            result.put("totalPage", totalPage);
            result.put("currentPage", currentPage);
            result.put("sort", sort);

            resp.setContentType("application/json; charset=UTF-8");
            String json = new Gson().toJson(result);
            resp.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
