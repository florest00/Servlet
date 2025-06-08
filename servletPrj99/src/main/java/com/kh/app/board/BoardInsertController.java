package com.kh.app.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.member.MemberVo;

@WebServlet("/board/insert")
public class BoardInsertController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 글 작성 폼 페이지 보여주기
        req.getRequestDispatcher("/WEB-INF/views/board/insert.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");

            String title = req.getParameter("title");
            String content = req.getParameter("content");

            // 로그인한 회원 번호를 세션에서 가져오기 (예: "5")
            String writerNo = null;
            if (req.getSession().getAttribute("loginMember") != null) {
                // MemberVo로 캐스팅 후 getNo() 가정 (번호가 String이라면 바로 사용)
                MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
                writerNo = loginMember.getNo(); // 여기 getNo()가 String 타입이어야 함
            } else {
                // 로그인 안 된 경우 처리 (예: 에러 페이지 or 로그인 페이지로 이동)
                resp.sendRedirect("/member/login");
                return;
            }

            BoardVo vo = new BoardVo();
            vo.setTitle(title);
            vo.setContent(content);
            vo.setWriterNo(writerNo);  // String 타입

            int result = new BoardService().insert(vo);

            if (result == 1) {
                resp.sendRedirect("/board/list");
            } else {
                req.setAttribute("errorMsg", "작성 실패");
                req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "예외 발생");
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
        }
    }
    

}
