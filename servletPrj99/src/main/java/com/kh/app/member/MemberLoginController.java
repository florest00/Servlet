package com.kh.app.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	// 로그인
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//data
			String userId = req.getParameter("userId");
			String userPwd = req.getParameter("userPwd");
			
			//data -> vo
			MemberVo vo = new MemberVo();
			vo.setUserId(userId);
			vo.setUserPwd(userPwd);
			
			//service
			MemberService memberService = new MemberService();
			MemberVo loginMember = memberService.login(vo);
			
			if(loginMember == null) {
				throw new Exception();
			}
			
			//result
			req.getSession().setAttribute("alertMsg", "로그인 성공 !!!");
			req.getSession().setAttribute("loginMember", loginMember);
			resp.sendRedirect("/home");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("/error");
		}
	}
}
