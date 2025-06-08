package com.kh.app.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/mypage")
public class MyPageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MyPageService myPageService = new MyPageService();
			List<MemberVo> voList = myPageService.selectList();
			req.setAttribute("voList", voList);
			req.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("/error");
		}
	
	}
}
