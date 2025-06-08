package com.kh.app.gallery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gallery/list")
public class GalleryListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			GalleryService galleryService = new GalleryService();
			List<GalleryVo> voList = galleryService.selectList();
			req.setAttribute("voList", voList);
			req.getRequestDispatcher("/WEB-INF/views/gallery/list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("/error");
		}
	
	}
}
