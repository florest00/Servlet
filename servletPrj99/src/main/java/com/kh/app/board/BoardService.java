package com.kh.app.board;

import java.sql.Connection;
import java.util.List;

import com.kh.app.db.JDBCTemplate;

public class BoardService {

    private final BoardDao dao = new BoardDao();
    
    public int insert(BoardVo vo) throws Exception {
        Connection conn = JDBCTemplate.getConn();
        int result = dao.insert(conn, vo);
        if (result == 1) {
            conn.commit();
        } else {
            conn.rollback();
        }
        conn.close();
        return result;
    }

    // 게시글 목록 조회 (검색 X, 정렬 O)
    public List<BoardVo> selectList(int startRow, int endRow, String sort) throws Exception {
        Connection conn = JDBCTemplate.getConn();
        List<BoardVo> list = dao.selectList(conn, null, startRow, endRow, sort);
        conn.close();
        return list;
    }

    // 게시글 목록 조회 (검색 O, 정렬 O)
    public List<BoardVo> selectList(BoardVo searchVo, int startRow, int endRow, String sort) throws Exception {
        Connection conn = JDBCTemplate.getConn();
        List<BoardVo> list = dao.selectList(conn, searchVo, startRow, endRow, sort);
        conn.close();
        return list;
    }

    // 전체 게시글 수 조회 (검색 포함 여부에 따라)
    public int getTotalCount(String keyword, String searchType) throws Exception {
        BoardVo searchVo = null;

        if (keyword != null && !keyword.trim().isEmpty() && searchType != null && !"all".equals(searchType)) {
            searchVo = new BoardVo();
            switch (searchType) {
                case "title": 
                    searchVo.setTitle(keyword); 
                    break;
                case "content": 
                    searchVo.setContent(keyword); 
                    break;
                case "writerNo": 
                    searchVo.setWriterNo(keyword); // String 타입 그대로 세팅
                    break;
                case "no": 
                    searchVo.setNo(keyword); // String 타입 그대로 세팅
                    break;
            }
        }

        Connection conn = JDBCTemplate.getConn();
        int count = dao.getTotalCount(conn, searchVo);
        conn.close();
        return count;
    }


    // 기존 사용하던 글 1개 조회 (상세보기 용)
    public BoardVo getBoardByNo(String no) throws Exception {
        Connection conn = JDBCTemplate.getConn();
        BoardVo vo = dao.selectOneByNo(conn, no);  // no는 String 타입으로 넘김
        conn.close();
        return vo;
    }

    public int getTotalCount(BoardVo searchVo) throws Exception {
        Connection conn = JDBCTemplate.getConn();
        try {
            return dao.getTotalCount(conn, searchVo);
        } finally {
            conn.close();
        }
    }
}
